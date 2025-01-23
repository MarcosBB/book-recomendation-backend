package com.imd.br.bookRecomendation.Service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imd.br.bookRecomendation.Model.ListaDeProdutos;
import com.imd.br.bookRecomendation.Model.Produto;
import com.imd.br.bookRecomendation.Repository.ListaDeProdutosRepository;
import com.imd.br.bookRecomendation.Repository.ProdutoRepository;

@Service
public class ListaDeProdutosService {

    @Autowired
    private ListaDeProdutosRepository listaDeProdutosRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public void adicionarProduto(Long idLista, Long idProduto) {
        // Busca a lista pelo ID
        ListaDeProdutos listaDeProdutos = listaDeProdutosRepository.findById(idLista)
                .orElseThrow(() -> new IllegalArgumentException("Lista não encontrada"));

        // Busca o produto de qualquer tipo (Jogo, Livro, etc.)
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        // Adiciona o produto à lista
        Set<Produto> produtos = listaDeProdutos.getProdutos();
        produtos.add(produto);
        listaDeProdutos.setProdutos(produtos);

        // Salva a lista
        listaDeProdutosRepository.save(listaDeProdutos);
    }

    public void removerProduto(Long idLista, Long idProduto) {
        // Busca a lista pelo ID
        ListaDeProdutos listaDeProdutos = listaDeProdutosRepository.findById(idLista)
                .orElseThrow(() -> new IllegalArgumentException("Lista não encontrada"));

        // Remove o produto pelo ID
        listaDeProdutos.getProdutos().removeIf(produto -> produto.getId().equals(idProduto));

        // Salva a lista
        listaDeProdutosRepository.save(listaDeProdutos);
    }
}
