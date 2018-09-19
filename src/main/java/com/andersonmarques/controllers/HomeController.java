package com.andersonmarques.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.andersonmarques.daos.ProdutoDao;

@Controller
public class HomeController {
	
	@Autowired
	private ProdutoDao produtoDao;
	
	@RequestMapping("/")
	@Cacheable(value="produtosHome")//O Spring torna nossa lista de findAll "cacheavel".
	public ModelAndView Index() {
		//Não precisa do /WEB-INF/views/ e do .jsp, ele já é o padrão escolhido na nossa AppWebConfiguration
		ModelAndView model = new ModelAndView("home");
		model.addObject("produtos", produtoDao.findAll());
		
		return model;
	}
}
