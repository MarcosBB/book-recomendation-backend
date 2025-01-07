package com.imd.br.bookRecomendation.Repository;

import com.imd.br.bookRecomendation.Model.Livro;
import org.springframework.data.jpa.domain.Specification;

public class LivroSpecification {
    public static Specification<Livro> filtroPorTitulo(String titulo) {
        return (root, query, criteriaBuilder) ->
                titulo == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")), "%" + titulo.toLowerCase() + "%");
    }

    public static Specification<Livro> filtroPorAutor(String autor) {
        return (root, query, criteriaBuilder) ->
                autor == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("autor")), "%" + autor.toLowerCase() + "%");
    }

    public static Specification<Livro> filtroPorGenero(String genero) {
        return (root, query, criteriaBuilder) ->
                genero == null ? null : criteriaBuilder.like(criteriaBuilder.lower(root.get("genero")), "%" + genero.toLowerCase() + "%");
    }

    public static Specification<Livro> filtroPorAvaliacaoMedia(Double avaliacaoMediaMin) {
        return (root, query, criteriaBuilder) ->
                avaliacaoMediaMin == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("avaliacaoMedia"), avaliacaoMediaMin);
    }
}
