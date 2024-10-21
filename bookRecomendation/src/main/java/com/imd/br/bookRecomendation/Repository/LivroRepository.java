package com.imd.br.bookRecomendation.Repository;

import com.imd.br.bookRecomendation.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {}