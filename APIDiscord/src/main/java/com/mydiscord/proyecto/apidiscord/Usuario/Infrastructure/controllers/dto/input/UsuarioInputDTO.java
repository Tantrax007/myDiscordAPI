package com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class UsuarioInputDTO {
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;
    private Long fotoPerfilId;
    private List<Long> mensajesIds;
    private List<Long> chatsIds;
}
