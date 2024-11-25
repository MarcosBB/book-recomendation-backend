package com.imd.br.bookRecomendation.Controller;

import com.imd.br.bookRecomendation.Api.ImportadorLivros;
import com.imd.br.bookRecomendation.Model.Livro;
import com.imd.br.bookRecomendation.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService ls;
    @Autowired
    private ImportadorLivros il;

    @GetMapping
    public List<Livro> listarTodos() {
        return ls.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Livro> buscarPorId(@PathVariable Long id) {
        return ls.buscarPorId(id);
    }

    @PostMapping
    public Livro salvar(@RequestBody Livro livro) {
        return ls.salvar(livro);
    }

    @PutMapping("/{id}")
    public Livro atualizar(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        return ls.atualizar(id, livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        ls.deletar(id);
    }

    @PostMapping("/importar")
    public ResponseEntity<String> importarLivros() {
        il.importarLivros("books");
        return ResponseEntity.ok("Importação de livros iniciada!");
    }
}
