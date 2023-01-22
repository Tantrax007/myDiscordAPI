package com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.output;


import com.mydiscord.proyecto.apidiscord.Mensaje.Domain.Mensaje;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicMensajeOutputDTO {
    private long id;
    private String contenido;

    public BasicMensajeOutputDTO(Mensaje mensaje) {
        setId(mensaje.getId());
        setContenido(mensaje.getContenido());
    }
}
