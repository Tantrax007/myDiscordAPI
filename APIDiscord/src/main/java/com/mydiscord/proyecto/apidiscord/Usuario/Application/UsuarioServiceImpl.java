package com.mydiscord.proyecto.apidiscord.Usuario.Application;

import com.mydiscord.proyecto.apidiscord.Chat.Domain.Chat;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.repository.jpa.ChatRepository;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.repository.jpa.FotoPerfilRepository;
import com.mydiscord.proyecto.apidiscord.Mensaje.Domain.Mensaje;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.input.MensajeInputDTO;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.repository.jpa.MensajeRepository;
import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.input.UsuarioInputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output.BasicUsuarioOutputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output.UsuarioOutputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FotoPerfilRepository fotoPerfilRepository;

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<UsuarioOutputDTO> getAllUsuarios() {
        log.info("Obtenidos todos los usuarios");
        return usuarioRepository.findAll().stream().map(UsuarioOutputDTO::new).collect(Collectors.toList());
    }

    @Override
    public UsuarioOutputDTO getUsuarioById(long idUsuario){
        log.info("Obtenido usuario con id: {}", idUsuario);
        return usuarioRepository.findById(idUsuario).map(UsuarioOutputDTO::new).orElse(new UsuarioOutputDTO().Empty());
    }

    @Override
    public BasicUsuarioOutputDTO getBasicUsuarioById(long idUsuario) throws FileNotFoundException {
        log.info("Obtenido usuario basico con id: {}", idUsuario);
        return usuarioRepository.findById(idUsuario).map(BasicUsuarioOutputDTO::new).orElseThrow(() -> new FileNotFoundException("Usuario no encontrado"));
    }


    @Override
    public BasicUsuarioOutputDTO crearUsuario(UsuarioInputDTO usuarioInputDTO) throws FileNotFoundException {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setName(usuarioInputDTO.getName());
        if (usuarioInputDTO.getFotoPerfilId() > 0) {
            nuevoUsuario.setFotoPerfil(fotoPerfilRepository.findById(usuarioInputDTO.getFotoPerfilId()).orElseThrow(() -> new FileNotFoundException("No existe ninguna foto de perfil con id: " + usuarioInputDTO.getFotoPerfilId())));
        }

        //Un usuario recien creado no pertenece a ningun grupo, a ningun chat privado ni tiene mensajes mandados
        log.info("Usuario creado con id: {}", nuevoUsuario.getId());
        return new BasicUsuarioOutputDTO(usuarioRepository.save(nuevoUsuario));
    }

    @Override
    public UsuarioOutputDTO updateUsuario(long idUsuario, UsuarioInputDTO usuarioInputDTO) throws FileNotFoundException {
        if (usuarioRepository.existsById(idUsuario)) {
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            usuario.setName(usuarioInputDTO.getName());
            usuario.setFotoPerfil(fotoPerfilRepository.findById(usuarioInputDTO.getFotoPerfilId())
                    .orElseThrow(() -> new FileNotFoundException("No existe ninguna foto de perfil con id: " + usuarioInputDTO.getFotoPerfilId())));

            log.info("Usuario actualizado correctamente");
            usuarioRepository.save(usuario);
            return new UsuarioOutputDTO(usuario);
        }

        log.error("No existe ningun usuario con id: {}", idUsuario);
        return null;
    }

    @Override
    public UsuarioOutputDTO addUsuarioToChat(long idUsuario, long idChat) throws FileNotFoundException {
        if (usuarioRepository.existsById(idUsuario)){
            Usuario usuario = usuarioRepository.findById(idUsuario).get();
            usuario.getChats().add(chatRepository.findById(idChat).orElseThrow(() -> new FileNotFoundException("No existe ningun chat con id: " + idChat)));

            log.info("Usuario con id: {} añadido al chat con id: {}", idUsuario, idChat);
            usuarioRepository.save(usuario);
            return new UsuarioOutputDTO(usuario);
        }

        log.error("No se ha podido agregar el usuario: {}, al chat: {} porque ese chat no existe", idUsuario, idChat);
        return null;
    }

    @Override
    public UsuarioOutputDTO addMensajeToUsuario(MensajeInputDTO nuevoMensaje) throws FileNotFoundException{
        if (usuarioRepository.existsById(nuevoMensaje.getUsuario())){
            Mensaje mensaje = new Mensaje();
            mensaje.setUsuario(usuarioRepository.findById(nuevoMensaje.getUsuario()).orElseThrow(() -> new FileNotFoundException("No existe ningun usuario con id: " + nuevoMensaje.getUsuario())));
            mensaje.setChat(chatRepository.findById(nuevoMensaje.getChat()).orElseThrow(() -> new FileNotFoundException("No existe ningun chat con id: " + nuevoMensaje.getChat())));
            mensaje.setContenido(nuevoMensaje.getContenidoMensaje());

            Usuario usuario = usuarioRepository.findById(nuevoMensaje.getUsuario()).get();
            usuario.getMensajes().add(mensaje);

            log.info("Mensaje agregado al usuario: {}", nuevoMensaje.getUsuario());
            usuarioRepository.save(usuario);
            return new UsuarioOutputDTO(usuario);
        }

        log.error("No se ha podido agregar el mensaje: {}, al usuario: {} porque el mensaje no existe", nuevoMensaje.getUsuario(), nuevoMensaje.getContenidoMensaje());
        return null;
    }

    @Override
    public boolean abandonarChat(long idChat, long idUsuario) throws FileNotFoundException{
        if (chatRepository.findById(idChat).isPresent()){
            if (usuarioRepository.findById(idUsuario).isPresent()){
                List<Chat> chatsDelUsuario = chatRepository.findAllByUsuariosId(idUsuario);
                Optional<Chat> chatAEliminar = chatsDelUsuario.stream().filter(chat -> chat.getId() == idChat).findFirst();
                if (chatAEliminar.isPresent()){
                    Usuario usuario = usuarioRepository.findById(idUsuario).get();
                    usuario.getChats().remove(chatAEliminar.get());

                    log.info("El usuario con id: {} ha abandonado el chat con id: {}", idUsuario, idChat);
                    usuarioRepository.save(usuario);
                    return true;
                }
            }
            log.error("No se ha podido abandonar el chat por que el usuario {} no existe", idUsuario);
            return false;
        }
        log.error("No se ha podido abandonar el chat: {}, por el usuario: {} porque el chat no existe", idChat, idUsuario);
        return false;
    }

    @Override
    public boolean deleteUsuario(long idUsuario) throws FileNotFoundException {
        if (usuarioRepository.existsById(idUsuario)) {
            mensajeRepository.deleteByUsuarioId(idUsuario);

            //TODO: Borrar el usuario de los chats

            //Por el momento borramos solo el usuario
            usuarioRepository.deleteById(idUsuario);

            log.info("Usuario con id: {} eliminado correctamente", idUsuario);
            return true;
        }

        log.error("El usuario con id: {} no existe", idUsuario);
        return false;
    }
}
