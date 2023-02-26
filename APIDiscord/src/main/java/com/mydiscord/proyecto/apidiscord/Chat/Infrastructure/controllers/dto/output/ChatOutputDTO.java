package com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output;

import com.mydiscord.proyecto.apidiscord.Chat.Domain.Chat;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.output.MensajeOutputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output.BasicUsuarioOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatOutputDTO {
    private long id;
    private List<BasicUsuarioOutputDTO> usuarios;
    private List<MensajeOutputDTO> mensajes;
    private String nombreChat;
    private int nroUsuarios;

    public ChatOutputDTO(Chat chat) {
        setId(chat.getId());
        setUsuarios(chat.getUsuarios().stream().map(BasicUsuarioOutputDTO::new).toList());
        setMensajes(chat.getMensajes().stream().map(MensajeOutputDTO::new).toList());
        setNombreChat(chat.getNombreChat());
        setNroUsuarios(chat.getNroUsuarios());
    }
}
