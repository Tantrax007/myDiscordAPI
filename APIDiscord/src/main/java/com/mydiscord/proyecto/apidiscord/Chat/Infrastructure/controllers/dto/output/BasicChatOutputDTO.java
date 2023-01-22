package com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output;

import com.mydiscord.proyecto.apidiscord.Chat.Domain.Chat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicChatOutputDTO {
    private long id;
    private String nombreChat;
    private int nroUsuarios;

    public BasicChatOutputDTO(Chat chat) {
        setId(chat.getId());
        setNombreChat(chat.getNombreChat());
        setNroUsuarios(chat.getNroUsuarios());
    }
}
