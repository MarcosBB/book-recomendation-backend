package com.imd.br.bookRecomendation.Controller;

import com.imd.br.bookRecomendation.Service.RecomendationService;
import com.imd.br.bookRecomendation.Service.RecomendationServiceGame;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recomendation")
public class RecomendationController {

    private final ChatClient chatClient;

    @Autowired
    private RecomendationServiceGame rs;

    private String system = "Responda de forma curta, clara e objetiva. Você é um recomendador de jogos de videogame.";

    public RecomendationController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(ChatOptionsBuilder
                        .builder()
                        .withModel("gpt-4o-mini")
                        .build())
                .build();
    }

    @GetMapping
    public String recomendation(String message) {
        return this.chatClient.prompt()
                .system(system)
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/genero/{usuarioId}")
    public String recomendationByGenero(@PathVariable Long usuarioId) {

        String message = rs.getMessageByGenero(usuarioId);
        return this.chatClient.prompt()
                .system(system)
                .user(message)
                .call()
                .content();
    }

    @GetMapping("/produto/{usuarioId}")
    public String recomendationByProduto(@PathVariable Long usuarioId) {

        String message = rs.getMessageByUltimoProduto(usuarioId);
        return this.chatClient.prompt()
                .system(system)
                .user(message)
                .call()
                .content();
    }

}
