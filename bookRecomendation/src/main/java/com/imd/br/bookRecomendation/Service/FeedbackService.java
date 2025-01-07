package com.imd.br.bookRecomendation.Service;

import com.imd.br.bookRecomendation.Model.Feedback;
import com.imd.br.bookRecomendation.Model.Livro;
import com.imd.br.bookRecomendation.Model.Usuario;
import com.imd.br.bookRecomendation.Repository.FeedbackRepository;
import com.imd.br.bookRecomendation.Repository.LivroRepository;
import com.imd.br.bookRecomendation.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository fr;

    @Autowired
    private UsuarioRepository ur;

    @Autowired
    private LivroRepository lr;

    public List<Feedback> listarTodos() {
        return fr.findAll();
    }

    public Optional<Feedback> buscarPorId(Long id) {
        return fr.findById(id);
    }

    public Feedback salvar(Long usuarioId, Long livroId, int nota, String comentario) {
        Usuario usuario = ur.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Livro livro = lr.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Feedback feedback = new Feedback(usuario, livro, nota, comentario);
        return fr.save(feedback);
    }

    public Feedback atualizar(Long id, Feedback feedbackAtualizado) {
        Feedback feedbackExistente = fr.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback não encontrado"));

        feedbackExistente.setNota(feedbackAtualizado.getNota());
        feedbackExistente.setComentario(feedbackAtualizado.getComentario());

        return fr.save(feedbackExistente);
    }

    public void deletar(Long id) {
        fr.deleteById(id);
    }

}
