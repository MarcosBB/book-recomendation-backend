package com.imd.br.bookRecomendation.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;
    private String generoPreferido;

    @ManyToMany
    @JoinTable(name = "usuario_livros_favoritos",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> livrosFavoritos;

    @ManyToMany
    @JoinTable(name = "usuario_historico_leitura",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> historicoLeitura;

    @ManyToMany
    @JoinTable(name = "usuario_livros_desejados",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> livrosDesejados;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Livro> getLivrosFavoritos() {
        return livrosFavoritos;
    }

    public void setLivrosFavoritos(List<Livro> livrosFavoritos) {
        this.livrosFavoritos = livrosFavoritos;
    }

    public List<Livro> getLivrosDesejados() {
        return livrosDesejados;
    }

    public void setLivrosDesejados(List<Livro> livrosDesejados) {
        this.livrosDesejados = livrosDesejados;
    }

    public List<Livro> getHistoricoLeitura() {
        return historicoLeitura;
    }

    public void setHistoricoLeitura(List<Livro> historicoLeitura) {
        this.historicoLeitura = historicoLeitura;
    }

    public String getGeneroPreferido() {
        return generoPreferido;
    }

    public void setGeneroPreferido(String generoPreferido) {
        this.generoPreferido = generoPreferido;
    }
}