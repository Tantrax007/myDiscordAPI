package com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.repository.jpa;

import com.mydiscord.proyecto.apidiscord.Mensaje.Domain.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
    List<Mensaje> findAllByUsuarioId(Long usuarioId);

    List<Mensaje> findAllByChatId(Long chatId);

    boolean deleteByUsuarioId(Long usuarioId);
}
