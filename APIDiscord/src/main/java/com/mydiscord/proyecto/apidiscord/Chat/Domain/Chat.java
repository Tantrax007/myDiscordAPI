package com.mydiscord.proyecto.apidiscord.Chat.Domain;


import com.mydiscord.proyecto.apidiscord.Mensaje.Domain.Mensaje;
import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(mappedBy = "chats")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "chat", fetch = FetchType.LAZY)
    private List<Mensaje> mensajes;

    @NotNull
    private String nombreChat;

    //Numero de usuarios que pueden estar en el chat
    private int nroUsuarios;

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", nombre='" + nombreChat + '\'' +
                ", nroUsuarios=" + nroUsuarios +
                '}';
    }
}
