package com.imd.br.bookRecomendation.Controller;

import com.imd.br.bookRecomendation.Model.ListaDeProdutos;
import com.imd.br.bookRecomendation.Repository.ListaDeProdutosRepository;
import com.imd.br.bookRecomendation.Service.ListaDeProdutosService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lista-de-produtos")
public class ListaDeProdutosController {
    @Autowired
    private ListaDeProdutosService listaDeProdutosService;

    @Autowired
    private ListaDeProdutosRepository listaDeProdutosRepository;

    @GetMapping
    public List<ListaDeProdutos> listarTodos() {
        return listaDeProdutosRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ListaDeProdutos> buscarPorId(@PathVariable Long id) {
        return listaDeProdutosRepository.findById(id);
    }

    @PostMapping
    public void criarListaDeProdutos(@RequestBody ListaDeProdutos listaDeProdutos) {
        listaDeProdutosRepository.save(listaDeProdutos);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        listaDeProdutosRepository.deleteById(id);
    }

    @PatchMapping("/{idLista}/add/{idProduto}")
    public void adicionarProduto(@PathVariable Long idLista, @PathVariable Long idProduto) {
        listaDeProdutosService.adicionarProduto(idLista, idProduto);
    }

    @PatchMapping("/{idLista}/remove/{idProduto}")
    public void removerProduto(@PathVariable Long idLista, @PathVariable Long idProduto) {
        listaDeProdutosService.removerProduto(idLista, idProduto);
    }

}
