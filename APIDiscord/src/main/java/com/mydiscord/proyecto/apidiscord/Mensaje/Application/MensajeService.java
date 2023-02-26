package com.mydiscord.proyecto.apidiscord.Mensaje.Application;

import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.input.MensajeInputDTO;
import com.mydiscord.proyecto.apidiscord.Mensaje.Infrastructure.controllers.dto.output.MensajeOutputDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface MensajeService {
    List<MensajeOutputDTO> getAllMensajesByUsuario(long idUsuario);

    List<MensajeOutputDTO> getAllMensajesByChat(long idChat);
    MensajeOutputDTO getMensajeById(long idMensaje) throws FileNotFoundException;

    MensajeOutputDTO crearMensaje(MensajeInputDTO mensajeInputDTO) throws FileNotFoundException;

    boolean deleteMensajeById(long idMensaje) throws FileNotFoundException;
    boolean deleteMensajesByUsuario(long idUsuario);
}
