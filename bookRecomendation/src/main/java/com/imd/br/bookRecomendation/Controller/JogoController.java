package com.imd.br.bookRecomendation.Controller;

import com.imd.br.bookRecomendation.Api.ImportadorProdutos;
import com.imd.br.bookRecomendation.Model.Jogo;
import com.imd.br.bookRecomendation.Service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService service;
    @Autowired
    private ImportadorProdutos importador;

    // @GetMapping("/filtro")
    // public List<Jogo> filtrarProdutos(
    // @RequestParam(required = false) String titulo,
    // @RequestParam(required = false) String autor,
    // @RequestParam(required = false) String genero,
    // @RequestParam(required = false) Double avaliacaoMediaMin) {
    // return service.filtrarProdutos(titulo, autor, genero, avaliacaoMediaMin);
    // }

    @GetMapping
    public List<Jogo> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Jogo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Jogo salvar(@RequestBody Jogo produto) {
        return service.salvar(produto);
    }

    @PutMapping("/{id}")
    public Jogo atualizar(@PathVariable Long id, @RequestBody Jogo produtoAtualizado) {
        return service.atualizar(id, produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PostMapping("/importar")
    public ResponseEntity<String> importarProdutos() {
        importador.importarProdutos("books");
        return ResponseEntity.ok("Importação de produtos iniciada!");
    }
}
