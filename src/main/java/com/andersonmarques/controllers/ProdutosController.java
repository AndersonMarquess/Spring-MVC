package com.andersonmarques.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andersonmarques.models.Produto;

@Controller
public class ProdutosController {
	
	@RequestMapping("produtos/form")
	public String form() {
		return "produtos/form";
	}
	
	//Como não foi especificado se é get ou post, ele aceita todos.
	@RequestMapping("/produtos")
	public String salvar(Produto produto) {
		System.out.println(produto);
		
		return "produtos/ok";
	}
}
