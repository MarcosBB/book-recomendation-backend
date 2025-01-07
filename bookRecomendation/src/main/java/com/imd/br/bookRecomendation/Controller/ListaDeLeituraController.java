package com.imd.br.bookRecomendation.Controller;

import com.imd.br.bookRecomendation.Model.ListaDeLeitura;
import com.imd.br.bookRecomendation.Repository.ListaDeLeituraRepository;
import com.imd.br.bookRecomendation.Service.ListaDeLeituraService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lista-de-leitura")
public class ListaDeLeituraController {
    @Autowired
    private ListaDeLeituraService listaDeLeituraService;

    @Autowired
    private ListaDeLeituraRepository listaDeLeituraRepository;

    @GetMapping
    public List<ListaDeLeitura> listarTodos() {
        return listaDeLeituraRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ListaDeLeitura> buscarPorId(@PathVariable Long id) {
        return listaDeLeituraRepository.findById(id);
    }

    @PostMapping
    public void criarListaDeLeitura(@RequestBody ListaDeLeitura listaDeLeitura) {
        listaDeLeituraRepository.save(listaDeLeitura);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        listaDeLeituraRepository.deleteById(id);
    }

    @PatchMapping("/{idLista}/add/{idLivro}")
    public void adicionarLivro(@PathVariable Long idLista, @PathVariable Long idLivro) {
        listaDeLeituraService.adicionarLivro(idLista, idLivro);
    }

    @PatchMapping("/{idLista}/remove/{idLivro}")
    public void removerLivro(@PathVariable Long idLista, @PathVariable Long idLivro) {
        listaDeLeituraService.removerLivro(idLista, idLivro);
    }

}
