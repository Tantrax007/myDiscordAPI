package com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto;

import com.mydiscord.proyecto.apidiscord.Chat.Application.ChatService;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.input.ChatInputDTO;
import com.mydiscord.proyecto.apidiscord.Chat.Infrastructure.controllers.dto.output.ChatOutputDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/chat")
@RestController
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/obtener/todos")
    public List<ChatOutputDTO> obtenerTodosLosChats() {
        return chatService.getAllChats();
    }

    @GetMapping("/obtener/todos/porUsuarioId/{usuarioId}")
    public List<ChatOutputDTO> obtenerTodosLosChatsPorUsusarioId(@PathVariable Long usuarioId){
        return chatService.getAllUserChats(usuarioId);
    }

    @GetMapping("/obtener/chat/{chatId}")
    public ChatOutputDTO obtenerChatDeUsuario(@PathVariable Long chatId){
        return chatService.getChat(chatId);
    }

    @PostMapping("/crear")
    public ChatOutputDTO crearNuevoChat(@RequestBody ChatInputDTO chatInputDTO){
        return chatService.crearChat(chatInputDTO);
    }

    @DeleteMapping("/eliminar/chat/porId/{chatId}")
    public boolean eliminarChatPorId(@PathVariable Long chatId){
        return chatService.deleteChat(chatId);
    }
}
