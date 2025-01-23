package com.imd.br.bookRecomendation.Repository;

import com.imd.br.bookRecomendation.Model.Jogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
}