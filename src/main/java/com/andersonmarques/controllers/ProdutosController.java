package com.andersonmarques.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.andersonmarques.daos.ProdutoDao;
import com.andersonmarques.models.Produto;
import com.andersonmarques.models.TipoPreco;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("/form")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@PostMapping()
	public String insert(Produto produto) {
		produtoDao.insert(produto);
		return "produtos/ok";
	}
	
	@GetMapping()
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.findAll();
		ModelAndView model = new ModelAndView("produtos/lista");
		model.addObject("produtos", produtos);
		return model;
	}
}
