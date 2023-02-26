package com.mydiscord.proyecto.apidiscord.FotoPerfil.Application;

import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.input.FotoPerfilInputDTO;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.output.FotoPerfilOutputDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface FotoPerfilService {
    FotoPerfilOutputDTO getFotoPerfilByUsuarioId(long idUsuario);
    FotoPerfilOutputDTO createFotoPerfil(FotoPerfilInputDTO fotoPerfilInputDTO) throws FileNotFoundException;
    FotoPerfilOutputDTO updateFotoPerfil(FotoPerfilInputDTO fotoPerfilInputDTO) throws FileNotFoundException;
    boolean deleteFotoPerfil(long idFotoPerfil);
}
