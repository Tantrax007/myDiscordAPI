package com.mydiscord.proyecto.apidiscord.Chat.Application;

import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.input.ChatInputDTO;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output.BasicChatOutputDTO;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output.ChatOutputDTO;

import java.io.FileNotFoundException;
import java.util.List;

public interface ChatService {
    List<ChatOutputDTO> getAllChats();

    List<ChatOutputDTO> getAllUserChats(long idUsuario);

    ChatOutputDTO getChat(long idChat);

    ChatOutputDTO crearChat(ChatInputDTO chatInputDTO);

    boolean deleteChat(long idChat);
}
