package com.imd.br.bookRecomendation.Service;

import com.imd.br.bookRecomendation.Model.Produto;

import java.util.List;
import java.util.Optional;

public interface ProdutoService<ProdutoGenerico extends Produto> {

    public List<ProdutoGenerico> listarTodos();

    public Optional<ProdutoGenerico> buscarPorId(Long id);

    ProdutoGenerico salvar(ProdutoGenerico produto);

    public ProdutoGenerico atualizar(Long id, ProdutoGenerico produtoAtualizado);

    public void deletar(Long id);

    public void salvarProdutos(String jsonResponse) throws Exception;

    public List<ProdutoGenerico> filtrarProdutos(String titulo, String autor, String genero, Double avaliacaoMediaMin);
}