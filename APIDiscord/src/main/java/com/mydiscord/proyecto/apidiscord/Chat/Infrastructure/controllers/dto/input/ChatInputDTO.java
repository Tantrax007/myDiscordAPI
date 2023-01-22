package com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.input;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatInputDTO {
    @NotEmpty(message = "El chat tiene que tener algun usuario")
    private List<Long> usuariosIds;
    private List<Long> mensajesIds;

    @NotNull(message = "El chat tiene que tener un nombre obligatoriamente")
    private String nombreChat;

    @NotNull(message = "El chat tiene que tener un numero de usuarios limitado")
    private int nroUsuarios;
}
