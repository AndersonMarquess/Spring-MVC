package com.andersonmarques.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andersonmarques.models.CarrinhoCompras;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
	
	@Autowired
	private CarrinhoCompras carrinho;
	
	@PostMapping("/finalizar")
	public ModelAndView finalizar(RedirectAttributes redirectAttr) {
		ModelAndView model = new ModelAndView("redirect:/produtos");
		
		System.out.println(carrinho.getTotal());
		//sucesso é um atributo da lista.jsp
		redirectAttr.addFlashAttribute("sucesso", "Pagamento finalizado com sucesso!");
		
		return model;
	}
}
