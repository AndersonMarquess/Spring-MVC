package com.andersonmarques.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.andersonmarques.daos.ProdutoDao;
import com.andersonmarques.models.CarrinhoCompras;
import com.andersonmarques.models.CarrinhoItem;
import com.andersonmarques.models.Produto;
import com.andersonmarques.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
//Escopo de um request, cada usuário tem um carrinhoController diferente.
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CarrinhoComprasController {

	@Autowired
	private ProdutoDao produtoDao;
	@Autowired
	private CarrinhoCompras carrinho;

	@PostMapping("/add")
	public ModelAndView insert(Integer produtoId, TipoPreco tipoPreco) {
		ModelAndView model = new ModelAndView("redirect:/carrinho");//Get com redirect no endpoint /carrinho
		
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipoPreco);
		carrinho.add(carrinhoItem);
		return model;
	}

	/**
	 * Procura o produto pelo ID e monta o item do carrinho de compras,
	 * Unindo produto e Tipo Preço.
	 * 
	 * @param produtoId
	 * @param tipoPreco
	 * @return
	 */
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipoPreco) {
		Produto p = produtoDao.findById(produtoId);
		return new CarrinhoItem(p, tipoPreco);
	}
	
	@GetMapping
	public ModelAndView itens() {
		return new ModelAndView("carrinho/itens");
	}
}
