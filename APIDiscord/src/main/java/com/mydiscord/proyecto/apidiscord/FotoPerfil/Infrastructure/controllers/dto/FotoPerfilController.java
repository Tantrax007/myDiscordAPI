package com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto;

import com.mydiscord.proyecto.apidiscord.FotoPerfil.Application.FotoPerfilService;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.input.FotoPerfilInputDTO;
import com.mydiscord.proyecto.apidiscord.FotoPerfil.Infrastructure.controllers.dto.output.FotoPerfilOutputDTO;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/fotoperfil")
public class FotoPerfilController {
    private final FotoPerfilService fotoPerfilService;

    public FotoPerfilController(FotoPerfilService fotoPerfilService) {
        this.fotoPerfilService = fotoPerfilService;
    }

    @GetMapping("/obtener/porUsuario/{id}")
    public FotoPerfilOutputDTO obtenerFotoPerfilPorUsuario(@PathVariable("id") long idUsuario) {
        return fotoPerfilService.getFotoPerfilByUsuarioId(idUsuario);
    }

    @PostMapping("/crear")
    public FotoPerfilOutputDTO crearFotoPerfil(@RequestBody FotoPerfilInputDTO fotoPerfilInputDTO) throws FileNotFoundException {
        return fotoPerfilService.createFotoPerfil(fotoPerfilInputDTO);
    }

    @PutMapping("/actualizar")
    public FotoPerfilOutputDTO actualizarFotoPerfilPorUsuario(@RequestBody FotoPerfilInputDTO fotoPerfilInputDTO) throws FileNotFoundException {
        return fotoPerfilService.updateFotoPerfil(fotoPerfilInputDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public boolean eliminarFotoPerfilPorUsuario(@PathVariable("id") long idUsuario){
        return fotoPerfilService.deleteFotoPerfil(idUsuario);
    }
}
