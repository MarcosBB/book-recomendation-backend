package com.imd.br.bookRecomendation.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imd.br.bookRecomendation.Api.ImportadorLivros;
import com.imd.br.bookRecomendation.Model.Livro;
import com.imd.br.bookRecomendation.Repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.data.jpa.domain.Specification;
import com.imd.br.bookRecomendation.Repository.LivroSpecification;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    private LivroRepository lr;
    @Autowired
    private ObjectMapper objectMapper;

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

    public void salvarLivros(String jsonResponse) throws Exception {
        JsonNode root = objectMapper.readTree(jsonResponse);
        JsonNode docs = root.path("docs");

        for (JsonNode doc : docs) {
            Livro livro = new Livro();
            int coverId = doc.path("cover_i").asInt();
            livro.setTitulo(doc.path("title").asText());
            livro.setNumeroPaginas(doc.path("number_of_pages_median").asInt());
            livro.setAvaliacaoMedia(doc.path("ratings_average").asDouble());
            livro.setAutor(
                    doc.path("author_name").isArray() ? doc.path("author_name").get(0).asText() : "Desconhecido");
            livro.setGenero(doc.path("subject").isArray() ? doc.path("subject").get(0).asText() : "Desconhecido");
            livro.setSinopse(doc.path("description").asText());
            livro.setLinkImage("https://covers.openlibrary.org/b/id/" + coverId + "-L.jpg");

            lr.save(livro);
        }
    }

    public List<Livro> filtrarLivros(String titulo, String autor, String genero, Double avaliacaoMediaMin) {
        Specification<Livro> spec = Specification.where(LivroSpecification.filtroPorTitulo(titulo))
                .and(LivroSpecification.filtroPorAutor(autor))
                .and(LivroSpecification.filtroPorGenero(genero))
                .and(LivroSpecification.filtroPorAvaliacaoMedia(avaliacaoMediaMin));

        return lr.findAll(spec);
    }
}
