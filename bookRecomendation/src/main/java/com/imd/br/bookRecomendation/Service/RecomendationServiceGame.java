package com.imd.br.bookRecomendation.Service;

import com.imd.br.bookRecomendation.Model.Historico;
import com.imd.br.bookRecomendation.Model.Produto;
import com.imd.br.bookRecomendation.Model.Usuario;
import com.imd.br.bookRecomendation.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecomendationServiceGame extends RecomendationService {

    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private HistoricoService hs;

    public String getMessageByGenero(Long id) {
        Optional<Usuario> usuario = ur.findById(id);

        String genero = usuario.get().getGeneroPreferido();

        if (genero.isEmpty()) {
            genero = "Drama";
        }

        String message = "Recomende jogo do mesmo genero preferido do usuario: " + genero;

        return message;
    }

    public String getMessageByUltimoProduto(Long id) {

        List<Historico> historico = hs.buscarPorUsuario(id);

        int tam = historico.size();

        Produto produto = historico.get(tam - 1).getProduto();

        String message = "Recomende jogo semelhantes ao ultimo produto lido pelo usuario: " + produto.getTitulo();

        return message;
    }

}
