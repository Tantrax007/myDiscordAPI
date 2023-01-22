package com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output;

import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output.BasicChatOutputDTO;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.output.BasicFotoPerfilOutputDTO;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.output.BasicMensajeOutputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioOutputDTO {
    private Long id;
    private String name;
    private BasicFotoPerfilOutputDTO fotoPerfil;
    private List<BasicMensajeOutputDTO> mensajes;
    private List<BasicChatOutputDTO> chats;

    public UsuarioOutputDTO(Usuario usuario) {
        setId(usuario.getId());
        setName(usuario.getName());
        setFotoPerfil(new BasicFotoPerfilOutputDTO(usuario.getFotoPerfil()));
        setMensajes(usuario.getMensajes().stream().map(BasicMensajeOutputDTO::new).collect(Collectors.toList()));
        setChats(usuario.getChats().stream().map(BasicChatOutputDTO::new).collect(Collectors.toList()));
    }

    public UsuarioOutputDTO Empty() {
        UsuarioOutputDTO usuarioVacio = new UsuarioOutputDTO();
        usuarioVacio.setId(0L);
        usuarioVacio.setName("");
        usuarioVacio.setFotoPerfil(null);
        usuarioVacio.setMensajes(List.of());
        usuarioVacio.setChats(List.of());

        return usuarioVacio;
    }
}
