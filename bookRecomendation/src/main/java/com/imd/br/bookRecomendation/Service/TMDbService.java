package com.imd.br.bookRecomendation.Service;

import org.springframework.web.client.RestTemplate;

public class TMDbService {
    private static final String API_KEY = "489ddabf430c2f88ff7d933cb6cf0ae9";
    private static final String BASE_URL = "https://api.themoviedb.org/3";

    public String buscarFilmes(String query, int page) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("%s/search/movie?api_key=%s&query=%s&page=%d",
                BASE_URL, API_KEY, query, page);
        return restTemplate.getForObject(url, String.class);
    }
}
