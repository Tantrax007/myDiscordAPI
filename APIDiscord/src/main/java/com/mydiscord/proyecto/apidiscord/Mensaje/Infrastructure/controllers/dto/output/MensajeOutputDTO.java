package com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.output;

import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output.BasicChatOutputDTO;
import com.mydiscord.proyecto.apidiscord.Mensaje.Domain.Mensaje;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output.BasicUsuarioOutputDTo;
import lombok.Data;

@Data
public class MensajeOutputDTO {
    private long id;
    private BasicUsuarioOutputDTo usuario;
    private BasicChatOutputDTO chat;
    private String contenido;

    public MensajeOutputDTO(Mensaje mensaje) {
        setId(mensaje.getId());
        setUsuario(new BasicUsuarioOutputDTo(mensaje.getUsuario()));
        setChat(new BasicChatOutputDTO(mensaje.getChat()));
        setContenido(mensaje.getContenido());
    }
}
