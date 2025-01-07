package com.imd.br.bookRecomendation.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imd.br.bookRecomendation.Model.Filme;
import com.imd.br.bookRecomendation.Repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public void salvarFilmes(String jsonResponse) throws Exception {
        JsonNode root = objectMapper.readTree(jsonResponse);
        JsonNode results = root.path("results"); // TMDb retorna uma lista chamada "results"

        for (JsonNode result : results) {
            Filme filme = new Filme();
            filme.setTitulo(result.path("title").asText());
            filme.setSinopse(result.path("overview").asText());
            filme.setAvaliacao(result.path("vote_average").asDouble());
            filme.setDataLancamento(result.path("release_date").asText());
            filme.setLinkImage("https://image.tmdb.org/t/p/w500" + result.path("poster_path").asText());

            filmeRepository.save(filme); // Salva no banco
        }
    }
}
