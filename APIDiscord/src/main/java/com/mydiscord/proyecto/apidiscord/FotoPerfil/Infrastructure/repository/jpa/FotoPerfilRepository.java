package com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.repository.jpa;

import com.mydiscord.proyecto.apidiscord.FotoPerfil.Domain.FotoPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoPerfilRepository extends JpaRepository<FotoPerfil, Long> {
    FotoPerfil findByUsuarioId(long idUsuario);
}
