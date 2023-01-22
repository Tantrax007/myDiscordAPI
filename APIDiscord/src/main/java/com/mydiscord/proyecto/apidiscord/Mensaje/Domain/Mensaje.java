package com.mydiscord.proyecto.apidiscord.Mensaje.Domain;

import com.mydiscord.proyecto.apidiscord.Chat.Domain.Chat;
import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "chatId")
    private Chat chat;

    private String contenido;

    @Override
    public String toString() {
        return "Mensaje{" +
                "id=" + id +
                ", contenido='" + contenido + '\'' +
                '}';
    }
}
