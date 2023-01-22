package com.mydiscord.proyecto.apidiscord.Usuario.Application;

import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.input.MensajeInputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.input.UsuarioInputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output.BasicUsuarioOutputDTo;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output.UsuarioOutputDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface UsuarioService {
    List<UsuarioOutputDTO> getAllUsuarios();

    UsuarioOutputDTO getUsuarioById(long idUsuario);

    BasicUsuarioOutputDTo getBasicUsuarioById(long idUsuario) throws FileNotFoundException;

    BasicUsuarioOutputDTo crearUsuario(UsuarioInputDTO usuarioInputDTO) throws FileNotFoundException;

    UsuarioOutputDTO updateUsuario(long idUsuario, UsuarioInputDTO usuarioInputDTO) throws FileNotFoundException;

    UsuarioOutputDTO addUsuarioToChat(long idUsuario, long idChat) throws FileNotFoundException;

    UsuarioOutputDTO addMensajeToUsuario(MensajeInputDTO nuevoMensaje) throws FileNotFoundException;

    boolean deleteUsuario(long idUsuario) throws FileNotFoundException;
}
