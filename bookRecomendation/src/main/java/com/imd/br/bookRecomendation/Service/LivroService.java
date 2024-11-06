package com.imd.br.bookRecomendation.Service;

import com.imd.br.bookRecomendation.Model.Livro;
import com.imd.br.bookRecomendation.Repository.LivroRepository;
import com.imd.br.bookRecomendation.Repository.LivroSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

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

    public Livro atualizar(Long id, Livro livroAtualizado) {
        Livro livroExistente = lr.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livroExistente.setTitulo(livroAtualizado.getTitulo());
        livroExistente.setAutor(livroAtualizado.getAutor());
        livroExistente.setEditora(livroAtualizado.getEditora());
        livroExistente.setNumeroPaginas(livroAtualizado.getNumeroPaginas());
        livroExistente.setGenero(livroAtualizado.getGenero());
        livroExistente.setSinopse(livroAtualizado.getSinopse());
        livroExistente.setAvaliacaoMedia(livroAtualizado.getAvaliacaoMedia());

        return lr.save(livroExistente);
    }

    public void deletar(Long id) {
        lr.deleteById(id);
    }

    public List<Livro> filtrarLivros(String titulo, String autor, String genero, Double avaliacaoMediaMin) {
        Specification<Livro> spec = Specification.where(LivroSpecification.filtroPorTitulo(titulo))
                .and(LivroSpecification.filtroPorAutor(autor))
                .and(LivroSpecification.filtroPorGenero(genero))
                .and(LivroSpecification.filtroPorAvaliacaoMedia(avaliacaoMediaMin));

        return lr.findAll(spec);
    }
}
