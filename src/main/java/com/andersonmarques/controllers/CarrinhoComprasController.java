package com.andersonmarques.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.andersonmarques.daos.ProdutoDao;
import com.andersonmarques.models.CarrinhoCompras;
import com.andersonmarques.models.CarrinhoItem;
import com.andersonmarques.models.Produto;
import com.andersonmarques.models.TipoPreco;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoComprasController {
	
	@Autowired
	private ProdutoDao produtoDao;
	@Autowired
	private CarrinhoCompras carrinho;

	@PostMapping("/add")
	public ModelAndView insert(Integer produtoId, TipoPreco tipo) {
		ModelAndView model = new ModelAndView("redirect:/produtos");//Get com redirect no endpoint /produtos
		
		CarrinhoItem carrinhoItem = criaItem(produtoId, tipo);
		carrinho.add(carrinhoItem);
		return model;
	}

	/**
	 * Procura o produto pelo ID e monta o item do carrinho de compras,
	 * Unindo produto e Tipo Preço.
	 * 
	 * @param produtoId
	 * @param tipo
	 * @return
	 */
	private CarrinhoItem criaItem(Integer produtoId, TipoPreco tipo) {
		Produto p = produtoDao.findById(produtoId);
		return new CarrinhoItem(p, tipo);
	}
}
