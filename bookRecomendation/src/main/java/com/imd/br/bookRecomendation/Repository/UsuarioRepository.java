package com.imd.br.bookRecomendation.Repository;

import com.imd.br.bookRecomendation.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {}