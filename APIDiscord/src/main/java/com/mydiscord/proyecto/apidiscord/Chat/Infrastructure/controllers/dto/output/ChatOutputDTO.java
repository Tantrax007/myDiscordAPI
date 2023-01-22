package com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output;

import com.mydiscord.proyecto.apidiscord.Chat.Domain.Chat;
import com.mydiscord.proyecto.apidiscord.Mensaje.Domain.Mensaje;
import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatOutputDTO {
    private long id;
    private List<Usuario> usuarios;
    private List<Mensaje> mensajes;
    private String nombreChat;
    private int nroUsuarios;

    public ChatOutputDTO(Chat chat) {
        setId(chat.getId());
        setUsuarios(chat.getUsuarios());
        setNombreChat(chat.getNombreChat());
        setNroUsuarios(chat.getNroUsuarios());
    }
}
