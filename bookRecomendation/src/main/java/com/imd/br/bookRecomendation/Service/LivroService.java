package com.imd.br.bookRecomendation.Service;

import com.imd.br.bookRecomendation.Model.Livro;
import com.imd.br.bookRecomendation.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository lr;

    public List<Livro> listarTodos() {
        return lr.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return lr.findById(id);
    }

    public Livro salvar(Livro livro) {
        return lr.save(livro);
    }

    public void deletar(Long id) {
        lr.deleteById(id);
    }
}
