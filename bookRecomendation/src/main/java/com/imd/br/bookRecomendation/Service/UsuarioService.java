package com.imd.br.bookRecomendation.Service;

import com.imd.br.bookRecomendation.Model.Usuario;
import com.imd.br.bookRecomendation.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository ur;

    public List<Usuario> listarTodos() {
        return ur.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return ur.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        return ur.save(usuario);
    }

    public void deletar(Long id) {
        ur.deleteById(id);
    }
}
