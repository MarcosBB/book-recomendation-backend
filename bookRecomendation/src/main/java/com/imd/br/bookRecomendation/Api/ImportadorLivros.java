package com.imd.br.bookRecomendation.Api;

import com.imd.br.bookRecomendation.Service.LivroService;
import com.imd.br.bookRecomendation.Service.OpenLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportadorLivros {

    @Autowired
    private OpenLibraryService openLibraryService;
    @Autowired
    private LivroService ls;

    public void importarLivros(String query) {
        int page = 1;
        boolean hasMoreData = true;

        while (hasMoreData) {
            String response = openLibraryService.buscarLivros(query, page);

            try {
                ls.salvarLivros(response);
                page++;
            } catch (Exception e) {
                e.printStackTrace();
                hasMoreData = false; // Para em caso de erro
            }
        }
    }

}
