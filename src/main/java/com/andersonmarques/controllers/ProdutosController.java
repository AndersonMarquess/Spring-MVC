package com.andersonmarques.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.andersonmarques.daos.ProdutoDao;
import com.andersonmarques.models.Produto;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("produtos/form")
	public String form() {
		return "produtos/form";
	}
	
	//Como não foi especificado se é get ou post, ele aceita todos.
	@RequestMapping("/produtos")
	public String insert(Produto produto) {
		produtoDao.insert(produto);
		return "produtos/ok";
	}
}
