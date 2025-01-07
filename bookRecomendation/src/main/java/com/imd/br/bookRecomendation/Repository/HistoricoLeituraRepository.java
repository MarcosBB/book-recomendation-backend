package com.imd.br.bookRecomendation.Repository;

import com.imd.br.bookRecomendation.Model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoLeituraRepository extends JpaRepository<Historico, Long> {
    List<Historico> findByUsuarioId(Long usuarioId);
}
