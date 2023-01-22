package com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.repository;

import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
