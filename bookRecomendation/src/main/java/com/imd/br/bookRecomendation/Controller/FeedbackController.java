package com.imd.br.bookRecomendation.Controller;

import com.imd.br.bookRecomendation.Model.Feedback;
import com.imd.br.bookRecomendation.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService fs;

    @GetMapping
    public List<Feedback> listarTodos() {
        return fs.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Feedback> buscarPorId(@PathVariable Long id) {
        return fs.buscarPorId(id);
    }

    @PostMapping
    public Feedback salvar(@RequestParam Long usuarioId, @RequestParam Long livroId,
                           @RequestParam int nota, @RequestParam String comentario) {
        return fs.salvar(usuarioId, livroId, nota, comentario);
    }

    @PutMapping("/{id}")
    public Feedback atualizar(@PathVariable Long id, @RequestBody Feedback feedbackAtualizado) {
        return fs.atualizar(id, feedbackAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        fs.deletar(id);
    }


}
