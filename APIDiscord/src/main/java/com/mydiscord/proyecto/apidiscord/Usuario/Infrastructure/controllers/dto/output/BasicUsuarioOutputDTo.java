package com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output;


import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicUsuarioOutputDTo {
    private Long id;
    private String name;

    public BasicUsuarioOutputDTo(Usuario usuario) {
        setId(usuario.getId());
        setName(usuario.getName());
    }
}
