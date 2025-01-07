package com.imd.br.bookRecomendation.Controller;

import com.imd.br.bookRecomendation.Model.Historico;
import com.imd.br.bookRecomendation.Service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService hs;

    @PostMapping("/adicionar")
    public ResponseEntity<Historico> adicionarAoHistorico(
            @RequestParam Long usuarioId, @RequestParam Long livroId) {
        Historico historico = hs.adicionar(usuarioId, livroId);
        return ResponseEntity.ok(historico);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Historico>> buscarPorUsuario(@PathVariable Long usuarioId) {
        List<Historico> historico = hs.buscarPorUsuario(usuarioId);
        return ResponseEntity.ok(historico);
    }
}
