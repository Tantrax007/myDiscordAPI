package com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.repository.jpa;

import com.mydiscord.proyecto.apidiscord.Chat.Domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findAllByUsuariosId(long idUsuario);
}
