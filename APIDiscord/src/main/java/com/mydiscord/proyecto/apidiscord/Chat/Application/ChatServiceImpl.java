package com.mydiscord.proyecto.apidiscord.Chat.Application;

import com.mydiscord.proyecto.apidiscord.Chat.Domain.Chat;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.input.ChatInputDTO;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output.BasicChatOutputDTO;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output.ChatOutputDTO;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.repository.jpa.ChatRepository;
import com.mydiscord.proyecto.apidiscord.Mensaje.Domain.Mensaje;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.repository.jpa.MensajeRepository;
import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MensajeRepository mensajeRepository;

    @Override
    public List<ChatOutputDTO> getAllChats() {
        log.info("Obtenemos todos los chats");
        return chatRepository.findAll().stream().map(ChatOutputDTO::new).toList();
    }

    @Override
    public List<ChatOutputDTO> getAllUserChats(long idUsuario) {
        log.info("Obtenemos todos los chats del usuario con id: {}", idUsuario);
        return chatRepository.findAllByUsuariosId(idUsuario).stream().map(ChatOutputDTO::new).toList();
    }

    @Override
    public ChatOutputDTO getChat(long idChat) {
        log.info("Obtenemos chat con id: {}", idChat);
        return new ChatOutputDTO(chatRepository.findById(idChat).orElse(new Chat()));
    }

    @Override
    public ChatOutputDTO crearChat(ChatInputDTO chatInputDTO) {
        Chat nuevoChat = new Chat();

//        Primero creamos el nuevo chat
        nuevoChat.setUsuarios(chatInputDTO.getUsuariosIds().stream().map(id -> usuarioRepository.findById(id).orElse(new Usuario())).collect(Collectors.toList())); //setUsuarios();

        nuevoChat.setMensajes(chatInputDTO.getMensajesIds().stream().map(id -> mensajeRepository.findById(id).orElse(new Mensaje())).collect(Collectors.toList())); //setMensajes();

        nuevoChat.setNombreChat(chatInputDTO.getNombreChat());
        nuevoChat.setNroUsuarios(chatInputDTO.getNroUsuarios());

        log.info("Nuevo chat creado: {}", nuevoChat.getNombreChat());
        ChatOutputDTO chatCreado = new ChatOutputDTO(chatRepository.save(nuevoChat));

//        Despues de crear el chat tenemos que asignarlo a los usuarios
        chatInputDTO.getUsuariosIds().forEach(usuarioId -> {
            Usuario usuarioAAsignar = new Usuario();
            if (usuarioRepository.findById(usuarioId).isPresent()) {
                usuarioAAsignar = usuarioRepository.findById(usuarioId).get();
                usuarioAAsignar.getChats().add(nuevoChat);
                usuarioRepository.save(usuarioAAsignar);
                log.info("Chat asignado al usuario {}", usuarioAAsignar.getId());
            }
            else{
                log.error("El chat no se ha podido asignar al usuario {} porque no se ha encontrado", usuarioAAsignar.getId());
            }
        });
        return chatCreado;
    }

    @Override
    public boolean deleteChat(long idChat) {
        if (chatRepository.existsById(idChat)) {
            log.info("Chat con id: {} eliminado", idChat);
            chatRepository.deleteById(idChat);
            return true;
        }

        log.error("El chat con id: {} no se ha podido eliminar", idChat);
        return false;
    }
}
