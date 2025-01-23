package com.imd.br.bookRecomendation.Model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "produto_id")
public class Jogo extends Produto {
    private String studio;
    private String plataforma;

    // Getters e Setters
    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }
}
