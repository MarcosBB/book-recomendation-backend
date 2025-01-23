package com.imd.br.bookRecomendation.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imd.br.bookRecomendation.Model.Jogo;
import com.imd.br.bookRecomendation.Model.Produto;
import com.imd.br.bookRecomendation.Repository.JogoRepository;
import com.imd.br.bookRecomendation.Repository.ProdutoSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService implements ProdutoService<Jogo> {

    @Autowired
    private JogoRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<Jogo> listarTodos() {
        return repository.findAll();
    }

    @Override
    public Optional<Jogo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Jogo salvar(Jogo produto) {
        return repository.save(produto);
    }

    public Jogo atualizar(Long id, Jogo produtoAtualizado) {
        Jogo produtoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoExistente.setTitulo(produtoAtualizado.getTitulo());
        produtoExistente.setGenero(produtoAtualizado.getGenero());
        produtoExistente.setSinopse(produtoAtualizado.getSinopse());
        produtoExistente.setAvaliacaoMedia(produtoAtualizado.getAvaliacaoMedia());

        return repository.save(produtoExistente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public void salvarProdutos(String jsonResponse) throws Exception {
        JsonNode docs = objectMapper.readTree(jsonResponse);

        for (JsonNode doc : docs) {
            Jogo jogo = new Jogo();
            jogo.setTitulo(doc.path("name").asText());
            jogo.setAvaliacaoMedia(doc.path("rating").asDouble());
            jogo.setSinopse(doc.path("summary").asText());

            repository.save(jogo);
        }
    }

    public List<Jogo> filtrarProdutos(String titulo, String autor, String genero, Double avaliacaoMediaMin) {
        Specification<Produto> spec = Specification.where(
                ProdutoSpecification.filtroPorTitulo(titulo))
                .and(ProdutoSpecification.filtroPorGenero(genero))
                .and(ProdutoSpecification.filtroPorAvaliacaoMedia(avaliacaoMediaMin));

        return repository.findAll((Sort) spec);
    }
}