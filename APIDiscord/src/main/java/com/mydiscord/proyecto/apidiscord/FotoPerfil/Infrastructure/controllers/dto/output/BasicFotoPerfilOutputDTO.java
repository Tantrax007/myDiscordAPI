package com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.output;

import com.mydiscord.proyecto.apidiscord.FotoPerfil.Domain.FotoPerfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicFotoPerfilOutputDTO {
    private Long id;
    private String fotoURL;

    public BasicFotoPerfilOutputDTO(FotoPerfil fotoPerfil) {
        setId(fotoPerfil.getId());
        setFotoURL(fotoPerfil.getFotoURL());
    }
}
