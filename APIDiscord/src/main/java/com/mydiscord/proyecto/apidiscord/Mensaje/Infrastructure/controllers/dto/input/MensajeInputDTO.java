package com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeInputDTO {
    @NotNull(message = "El mensaje tiene que pertenecer a un usuario")
    private Long usuario;

    @NotNull(message = "El mensaje tiene que ser asigando a un unico chat")
    private Long chat;

    @NotNull(message = "El mensaje tiene que tener un contenido")
    private String contenidoMensaje;
}
