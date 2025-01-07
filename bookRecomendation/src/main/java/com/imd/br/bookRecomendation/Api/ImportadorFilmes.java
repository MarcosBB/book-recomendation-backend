package com.imd.br.bookRecomendation.Api;

import com.imd.br.bookRecomendation.Service.TMDbService;
import com.imd.br.bookRecomendation.Service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportadorFilmes {

    @Autowired
    private TMDbService tmdbService;
    @Autowired
    private FilmeService filmeService;

    public void importarFilmes(String query) {
        int page = 1;
        boolean hasMoreData = true;

        while (hasMoreData) {
            String response = tmdbService.buscarFilmes(query, page);

            try {
                filmeService.salvarFilmes(response);
                page++;
            } catch (Exception e) {
                e.printStackTrace();
                hasMoreData = false;
            }
        }
    }
}
