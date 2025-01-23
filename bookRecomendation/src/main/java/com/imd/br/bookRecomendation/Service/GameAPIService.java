package com.imd.br.bookRecomendation.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GameAPIService {

    private final WebClient webClient;
    private final WebClient authWebClient;

    private final String CLIENT_ID = "o3omb8ond9dsa37rdw2z4m0mlyuimn";
    private final String CLIENT_SECRET = "3guav8zg4xwd71rpl96ewtgaom5nyo";

    private String accessToken;

    public GameAPIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.igdb.com/v4")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(50 * 1024 * 1024))
                .build();
        this.authWebClient = webClientBuilder.baseUrl("https://id.twitch.tv")
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(50 * 1024 * 1024))
                .build();
        this.accessToken = authenticate();
    }

    public String buscarProdutos(int offset) {

        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/games")
                            .queryParam("fields", "name,genres.name,platforms.name,rating,summary")
                            .queryParam("offset", offset)
                            .queryParam("limit", 500)
                            .build())
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Client-ID", CLIENT_ID)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error while fetching game data: " + e.getMessage(), e);
        }
    }

    private String authenticate() {
        try {
            String response = authWebClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .scheme("https")
                            .host("id.twitch.tv")
                            .path("/oauth2/token")
                            .queryParam("client_id", CLIENT_ID)
                            .queryParam("client_secret", CLIENT_SECRET)
                            .queryParam("grant_type", "client_credentials")
                            .build())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // Parse access token from response JSON
            return extractAccessToken(response);
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }

    private String extractAccessToken(String response) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(response).get("access_token").asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse access token from response", e);
        }
    }
}
