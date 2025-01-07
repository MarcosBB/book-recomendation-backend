package com.imd.br.bookRecomendation.Repository;

import com.imd.br.bookRecomendation.Model.ListaDeLeitura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListaDeLeituraRepository extends JpaRepository<ListaDeLeitura, Long> {
}