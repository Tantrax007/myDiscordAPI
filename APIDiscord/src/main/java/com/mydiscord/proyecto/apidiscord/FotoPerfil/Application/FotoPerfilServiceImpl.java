package com.mydiscord.proyecto.apidiscord.FotoPerfil.Application;

import com.mydiscord.proyecto.apidiscord.FotoPerfil.Domain.FotoPerfil;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.input.FotoPerfilInputDTO;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.output.FotoPerfilOutputDTO;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.repository.jpa.FotoPerfilRepository;
import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
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
        Usuario usuario = usuarioRepository
                .findById(fotoPerfilInputDTO.getUsuarioId()).orElseThrow(() -> new FileNotFoundException("No se ha encontrado el usuario"));
        nuevaFotoPerfil.setFotoURL(fotoPerfilInputDTO.getFotoURL());
        nuevaFotoPerfil.setUsuario(usuario);
        usuario.setFotoPerfil(nuevaFotoPerfil);
        fotoPerfilRepository.save(nuevaFotoPerfil); //Guardamos la foto
        usuarioRepository.save(usuario); //Actualizamos el usuario con la nueva foto
        return new FotoPerfilOutputDTO(nuevaFotoPerfil);
    }

    @Override
    public FotoPerfilOutputDTO updateFotoPerfil(FotoPerfilInputDTO fotoPerfilInputDTO) throws FileNotFoundException {
        FotoPerfil fotoPerfil = fotoPerfilRepository.findByUsuarioId(fotoPerfilInputDTO.getUsuarioId());
        Usuario usuario = usuarioRepository
                .findById(fotoPerfilInputDTO.getUsuarioId()).orElseThrow(() -> new FileNotFoundException("No se ha encontrado el usuario"));
        fotoPerfil.setFotoURL(fotoPerfilInputDTO.getFotoURL());
        usuario.setFotoPerfil(fotoPerfil);
        fotoPerfilRepository.save(fotoPerfil);
        usuarioRepository.save(usuario);
        return new FotoPerfilOutputDTO(fotoPerfil);
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
