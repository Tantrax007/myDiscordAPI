package com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoPerfilInputDTO {
    @NotNull(message = "La foto no puede ser nula")
    private String fotoURL;

    @NotNull(message = "La foto pertenece obligatoriamente a un usuario")
    private Long usuarioId;
}
