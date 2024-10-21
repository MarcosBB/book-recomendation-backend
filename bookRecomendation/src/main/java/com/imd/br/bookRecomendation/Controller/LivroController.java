package com.imd.br.bookRecomendation.Controller;

import com.imd.br.bookRecomendation.Model.Livro;
import com.imd.br.bookRecomendation.Service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        ls.deletar(id);
    }

}
