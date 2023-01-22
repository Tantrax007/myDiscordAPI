package com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.output;

import com.mydiscord.proyecto.apidiscord.FotoPerfil.Domain.FotoPerfil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoPerfilOutputDTO {
    private Long id;
    private String fotoURL;
    private Long usuarioId;

    public FotoPerfilOutputDTO(FotoPerfil fotoPerfil){
        setId(fotoPerfil.getId());
        setFotoURL(fotoPerfil.getFotoURL());
        setUsuarioId(fotoPerfil.getUsuario().getId());
    }
}
