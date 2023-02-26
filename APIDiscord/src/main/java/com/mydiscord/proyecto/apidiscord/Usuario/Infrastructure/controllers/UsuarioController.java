package com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers;

import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.input.MensajeInputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Application.UsuarioService;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.input.UsuarioInputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output.BasicUsuarioOutputDTO;
import com.mydiscord.proyecto.apidiscord.Usuario.Infrastructure.controllers.dto.output.UsuarioOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value= "/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/obtener/todos")
    public List<UsuarioOutputDTO> obtenerTodosUsuarios(){
        return usuarioService.getAllUsuarios();
    }

    @GetMapping(value = "/obtener/porId/{idUsuario}")
    public UsuarioOutputDTO obtenerPorId(@PathVariable long idUsuario){
        return usuarioService.getUsuarioById(idUsuario);
    }

    @GetMapping(value = "/obtener/basico/porId/{idUsuario}")
    public BasicUsuarioOutputDTO obtenerBasicoPorId(@PathVariable long idUsuario) throws FileNotFoundException{
        return usuarioService.getBasicUsuarioById(idUsuario);
    }

    @PostMapping(value = "/crear/usuario")
    public BasicUsuarioOutputDTO crearUsuario(@RequestBody UsuarioInputDTO usuarioInputDTO) throws FileNotFoundException {
        return usuarioService.crearUsuario(usuarioInputDTO);
    }

    @PutMapping(value = "/actualizar/usuario/{id}")
    public UsuarioOutputDTO actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioInputDTO usuarioInputDTO) throws FileNotFoundException{
        return usuarioService.updateUsuario(id, usuarioInputDTO);
    }

    @PostMapping(value = "/agregar/chat/{idChat}/usuario/{idUsuario}")
    public UsuarioOutputDTO agregarChatAUsuario(@PathVariable long idChat, @PathVariable long idUsuario) throws FileNotFoundException{
        return usuarioService.addUsuarioToChat(idChat, idUsuario);
    }

    @PostMapping(value = "/agregar/mensaje/usuario")
    public UsuarioOutputDTO agregarChatAUsuario(@RequestBody MensajeInputDTO nuevoMensaje) throws FileNotFoundException{
        return usuarioService.addMensajeToUsuario(nuevoMensaje);
    }

    @DeleteMapping(value = "/abandonarChat/{idChat}/{idUsuario}")
    public boolean abandonarChat(@PathVariable long idChat, @PathVariable long idUsuario) throws FileNotFoundException{
        return usuarioService.abandonarChat(idChat, idUsuario);
    }

    @DeleteMapping(value = "/eliminar/usuario/{id}")
    public boolean eliminarUsuario(@PathVariable Integer id) throws FileNotFoundException{
        return usuarioService.deleteUsuario(id);
    }
}
