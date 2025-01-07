package com.imd.br.bookRecomendation.Service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imd.br.bookRecomendation.Model.ListaDeLeitura;
import com.imd.br.bookRecomendation.Model.Livro;
import com.imd.br.bookRecomendation.Repository.ListaDeLeituraRepository;

@Service
public class ListaDeLeituraService {

    @Autowired
    private ListaDeLeituraRepository listaDeLeituraRepository;

    public void adicionarLivro(Long idLista, Long idLivro) {
        ListaDeLeitura listaDeLeitura = listaDeLeituraRepository.findById(idLista).get();
        Set<Livro> livros = listaDeLeitura.getLivros();
        Livro livro = new Livro();
        livro.setId(idLivro);
        livros.add(livro);
        listaDeLeitura.setLivros(livros);
        listaDeLeituraRepository.save(listaDeLeitura);
    }

    public void removerLivro(Long idLista, Long idLivro) {
        ListaDeLeitura listaDeLeitura = listaDeLeituraRepository.findById(idLista).get();
        listaDeLeitura.getLivros().removeIf(livro -> livro.getId().equals(idLivro));
        listaDeLeituraRepository.save(listaDeLeitura);
    }
}
