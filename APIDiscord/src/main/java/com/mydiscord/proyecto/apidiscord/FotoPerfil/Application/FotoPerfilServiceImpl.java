package com.mydiscord.proyecto.apidiscord.FotoPerfil.Application;

import com.mydiscord.proyecto.apidiscord.FotoPerfil.Domain.FotoPerfil;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.input.FotoPerfilInputDTO;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.output.FotoPerfilOutputDTO;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.repository.jpa.FotoPerfilRepository;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class FotoPerfilServiceImpl implements FotoPerfilService {
    private final FotoPerfilRepository fotoPerfilRepository;
    private final UsuarioRepository usuarioRepository;

    //Lo hago con el constructor por utilizar otra forma que no sea la inyección de dependencias
    public FotoPerfilServiceImpl(FotoPerfilRepository fotoPerfilRepository, UsuarioRepository usuarioRepository) {
        this.fotoPerfilRepository = fotoPerfilRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public FotoPerfilOutputDTO getFotoPerfilByUsuarioId(long idUsuario) {
        return new FotoPerfilOutputDTO(fotoPerfilRepository.findByUsuarioId(idUsuario));
    }

    @Override
    public FotoPerfilOutputDTO createFotoPerfil(FotoPerfilInputDTO fotoPerfilInputDTO) throws FileNotFoundException{
        FotoPerfil nuevaFotoPerfil = new FotoPerfil();
        nuevaFotoPerfil.setFotoURL(fotoPerfilInputDTO.getFotoURL());
        nuevaFotoPerfil.setUsuario(usuarioRepository
                .findById(fotoPerfilInputDTO.getUsuarioId()).orElseThrow(() -> new FileNotFoundException("El usuario no existe")));
        return new FotoPerfilOutputDTO(fotoPerfilRepository.save(nuevaFotoPerfil));
    }

    @Override
    public FotoPerfilOutputDTO updateFotoPerfil(FotoPerfilInputDTO fotoPerfilInputDTO) {
        FotoPerfil fotoPerfil = fotoPerfilRepository.findByUsuarioId(fotoPerfilInputDTO.getUsuarioId());
        fotoPerfil.setFotoURL(fotoPerfilInputDTO.getFotoURL());
        return new FotoPerfilOutputDTO(fotoPerfilRepository.save(fotoPerfil));
    }

    @Override
    public boolean deleteFotoPerfil(long idFotoPerfil) {
        if (fotoPerfilRepository.existsById(idFotoPerfil)) {
            fotoPerfilRepository.deleteById(idFotoPerfil);
            return true;
        }
        return false;
    }
}
