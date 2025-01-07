package com.imd.br.bookRecomendation.Service;

import com.imd.br.bookRecomendation.Model.Historico;
import com.imd.br.bookRecomendation.Model.Livro;
import com.imd.br.bookRecomendation.Model.Usuario;
import com.imd.br.bookRecomendation.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecomendationService {

    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private HistoricoService hs;

    public String getMessageByGenero(Long id){
        Optional<Usuario> usuario = ur.findById(id);

        String genero = usuario.get().getGeneroPreferido();

        if (genero.isEmpty()){
            genero = "Drama";
        }

        String message = "Recomende livros do mesmo genero preferido do usuario: " + genero;

        return message;
    }

    public String getMessageByUltimoLivro(Long id){

        List<Historico> historico = hs.buscarPorUsuario(id);

        int tam = historico.size();

        Livro livro = historico.get(tam-1).getLivro();

        String message = "Recomende livros semelhantes ao ultimo livro lido pelo usuario: " + livro.getTitulo();

        return message;
    }

}
