package com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers;


import com.mydiscord.proyecto.apidiscord.Mensaje.Application.MensajeService;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.input.MensajeInputDTO;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.output.BasicMensajeOutputDTO;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.output.MensajeOutputDTO;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/mensajes")
public class MensajeController {
    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService){
        this.mensajeService = mensajeService;
    }

    @GetMapping("/obtener/porUsuarioId/{id}")
    public List<BasicMensajeOutputDTO> obtenerMensajesDeUsuario(@PathVariable("id") int idUsuario) {
        return mensajeService.getAllMensajesByUsuario(idUsuario);
    }

    @GetMapping(value = "/obtener/porMensajeId/{id}")
    public MensajeOutputDTO obtenerMensajePorId(@PathVariable("id") int idMensaje) throws FileNotFoundException {
        return mensajeService.getMensajeById(idMensaje);
    }

    @PostMapping(value = "/crear")
    public MensajeOutputDTO crearMensaje(@RequestBody MensajeInputDTO mensajeInputDTO) throws FileNotFoundException {
        return mensajeService.crearMensaje(mensajeInputDTO);
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public boolean eliminarMensaje(@PathVariable("id") int idMensaje) throws FileNotFoundException {
        return mensajeService.deleteMensajeById(idMensaje);
    }
}
