package com.mydiscord.proyecto.apidiscord.FotoPerfil.Domain;


import com.mydiscord.proyecto.apidiscord.Usuario.Domain.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FotoPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fotoURL;

    @OneToOne(mappedBy = "fotoPerfil")
    private Usuario usuario;

    @Override
    public String toString() {
        return "FotoPerfil{" +
                "id=" + id +
                ", fotoURL='" + fotoURL + '\'' +
                '}';
    }
}
