package com.silvalves.armazem.controller;


import com.silvalves.armazem.model.Produto;
import com.silvalves.armazem.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class controller {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/inicio")
    public String inicio(){
        return "index";
    }

    @GetMapping("/inserir")
    public String cadastro(Model model){
        model.addAttribute("produto", new Produto());
        return "cadastrar";
    }

    @GetMapping("/alterar")
    public String alterarProduto(Model model, @RequestParam Long id){
        Produto produtoEncontrado = produtoService.buscarProdutoPorId(id);

        model.addAttribute("produto", produtoEncontrado);
        return "cadastrar";
    }

    @PostMapping("/gravar")
    public String processarFormulario(@ModelAttribute Produto produto,Model model){

        if (produto.getId()!=null) {
            produtoService.atualizarProduto(produto.getId(), produto);
        } else {
            produtoService.cadastrarProduto(produto);
        }
        return "redirect:/listar";
    }


    @PostMapping("/apagar")
    public String excluirProduto(Model model, @RequestParam String id, @RequestParam String radio){
        if (radio.equals("sim")) {
            Long idProduto = Long.parseLong(id);
            produtoService.excluirProduto(idProduto);
            return "redirect:/listar";
        }   else {
            return "redirect:/listar";
        }
    }

    @GetMapping("/listar")
    public String listagem(Model model){
        model.addAttribute("lista",produtoService.listarTodosProdutos());
        return "produtos";
    }

    @GetMapping("/excluir")
    public String exibirExcluir(@RequestParam String id, Model model){
        Long idProduto = Long.parseLong(id);

        Produto produtoEncontrado = new Produto();
        produtoEncontrado = produtoService.buscarProdutoPorId(idProduto);

        model.addAttribute("produto", produtoEncontrado);

        return "excluir";
    }
}
