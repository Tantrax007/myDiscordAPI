package com.mydiscord.proyecto.apidiscord.Mensaje.Application;

import com.mydiscord.proyecto.apidiscord.Chat.Domain.Chat;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output.BasicChatOutputDTO;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.repository.jpa.ChatRepository;
import com.mydiscord.proyecto.apidiscord.Mensaje.Domain.Mensaje;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.input.MensajeInputDTO;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.output.BasicMensajeOutputDTO;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.output.MensajeOutputDTO;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.repository.jpa.MensajeRepository;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.repository.UsuarioRepository;
import jakarta.persistence.Basic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Slf4j
@Service
public class MensajeServiceImpl implements MensajeService {

    @Autowired
    private MensajeRepository mensajeRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<MensajeOutputDTO> getAllMensajesByUsuario(long idUsuario) {
        log.info("Obteniendo todos los mensajes del usuario con id: {}", idUsuario);
        return mensajeRepository.findAllByUsuarioId(idUsuario).stream().map(MensajeOutputDTO::new).toList();
    }

    @Override
    public List<MensajeOutputDTO> getAllMensajesByChat(long idChat) {
        log.info("Obteniendo todos los mensajes del chat con id: {}", idChat);
        return mensajeRepository.findAllByChatId(idChat).stream().map(MensajeOutputDTO::new).toList();
    }

    @Override
    public MensajeOutputDTO getMensajeById(long idMensaje) throws FileNotFoundException {
        log.info("Buscando mensaje con id: {}", idMensaje);
        return new MensajeOutputDTO(mensajeRepository.findById(idMensaje).orElseThrow(() -> new FileNotFoundException("Mensaje no encontrado")));
    }

    @Override
    public MensajeOutputDTO crearMensaje(MensajeInputDTO mensajeInputDTO) throws FileNotFoundException {
        log.info("Creando mensaje");
        System.out.println(mensajeInputDTO.toString());
        Mensaje nuevoMensaje = new Mensaje();
        nuevoMensaje.setChat(chatRepository.findById(mensajeInputDTO.getChat()).orElseThrow(() -> new FileNotFoundException("Chat no encontrado")));
        nuevoMensaje.setUsuario(usuarioRepository.findById(mensajeInputDTO.getUsuario()).orElseThrow(() -> new FileNotFoundException("Usuario no encontrado")));
        nuevoMensaje.setContenido(mensajeInputDTO.getContenidoMensaje());
        return new MensajeOutputDTO(mensajeRepository.save(nuevoMensaje));
    }

    @Override
    public boolean deleteMensajeById(long idMensaje) {
        if (mensajeRepository.existsById(idMensaje)) {
            mensajeRepository.deleteById(idMensaje);

            return true;
        }

        log.error("Mensaje con id: {} no encontrado", idMensaje);
        return false;
    }

    @Override
    public boolean deleteMensajesByUsuario(long idUsuario) {
        mensajeRepository.deleteByUsuarioId(idUsuario);
        log.info("Mensajes del usuario con id: {} eliminados correctamente", idUsuario);
        return true;
    }
}
