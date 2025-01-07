package com.imd.br.bookRecomendation.Service;

import com.imd.br.bookRecomendation.Model.Historico;
import com.imd.br.bookRecomendation.Model.Livro;
import com.imd.br.bookRecomendation.Model.Usuario;
import com.imd.br.bookRecomendation.Repository.HistoricoLeituraRepository;
import com.imd.br.bookRecomendation.Repository.LivroRepository;
import com.imd.br.bookRecomendation.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoLeituraRepository hr;

    @Autowired
    private LivroRepository lr;

    @Autowired
    private UsuarioRepository ur;

    public Historico adicionar(Long usuarioId, Long livroId) {

        Usuario usuario = ur.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Livro livro = lr.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        // Adiciona o registro ao histórico
        Historico historico = new Historico();
        historico.setUsuario(usuario);
        historico.setLivro(livro);

        return hr.save(historico);
    }

    public List<Historico> buscarPorUsuario(Long usuarioId) {
        return hr.findByUsuarioId(usuarioId);
    }
}
