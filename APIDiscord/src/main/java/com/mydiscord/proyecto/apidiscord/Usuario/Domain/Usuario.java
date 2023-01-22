package com.mydiscord.proyecto.apidiscord.Usuario.Domain;


import com.mydiscord.proyecto.apidiscord.Chat.Domain.Chat;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Domain.FotoPerfil;
import com.mydiscord.proyecto.apidiscord.Mensaje.Domain.Mensaje;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToOne
    @JoinColumn(name = "fotoPerfilId")
    private FotoPerfil fotoPerfil;

    @OneToMany(mappedBy = "usuario")
    private List<Mensaje> mensajes;

    @ManyToMany
    @JoinTable(
            name = "usuario_chat",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "chat_id")
    )
    private List<Chat> chats;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fotoPerfil=" + fotoPerfil +
                '}';
    }
}
