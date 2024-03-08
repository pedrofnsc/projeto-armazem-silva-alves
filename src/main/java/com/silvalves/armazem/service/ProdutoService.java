package com.silvalves.armazem.service;

import com.silvalves.armazem.model.Produto;
import com.silvalves.armazem.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    //CRUD
    public Produto cadastrarProduto(Produto prod) {
        prod.setId(null);
        produtoRepository.save(prod);
        return prod;
    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public Produto buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

    public void excluirProduto(Long id) {
        Produto produtoEncontrado = buscarProdutoPorId(id);
        produtoRepository.deleteById(produtoEncontrado.getId());
    }

    public Produto atualizarProduto(Long id, Produto produtoEnviado) {

        Produto produtoEncontrado = buscarProdutoPorId(id);
        produtoEncontrado.setNome(produtoEnviado.getNome());
        produtoEncontrado.setDescricao(produtoEnviado.getDescricao());

        produtoRepository.save(produtoEncontrado);
        return produtoEncontrado;
    }

}
