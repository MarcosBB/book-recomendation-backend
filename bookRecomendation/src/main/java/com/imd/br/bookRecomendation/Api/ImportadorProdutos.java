package com.imd.br.bookRecomendation.Api;

import com.imd.br.bookRecomendation.Service.GameAPIService;
import com.imd.br.bookRecomendation.Service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ImportadorProdutos {

    @Autowired
    private GameAPIService gameAPIService;
    @Autowired
    private JogoService service;

    public void importarProdutos(String query) {
        int page = 1;
        boolean hasMoreData = true;

        while (hasMoreData) {
            String response = gameAPIService.buscarProdutos(query, page);

            try {
                service.salvarProdutos(response);
                page++;
            } catch (Exception e) {
                e.printStackTrace();
                hasMoreData = false; // Para em caso de erro
            }
        }
    }

}
