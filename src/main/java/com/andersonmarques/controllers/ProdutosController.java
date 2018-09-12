package com.andersonmarques.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.andersonmarques.daos.ProdutoDao;
import com.andersonmarques.models.Produto;
import com.andersonmarques.models.TipoPreco;

@Controller
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("produtos/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	//Como não foi especificado se é get ou post, ele aceita todos.
	@RequestMapping("/produtos")
	public String insert(Produto produto) {
		produtoDao.insert(produto);
		return "produtos/ok";
	}
}
