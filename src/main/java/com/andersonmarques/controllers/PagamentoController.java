package com.andersonmarques.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andersonmarques.models.CarrinhoCompras;
import com.andersonmarques.models.DadosPagamento;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/finalizar")
	// Callable para requisição assíncrono.
	public Callable<ModelAndView> finalizar(RedirectAttributes redirectAttr) {

		return () -> {
			ModelAndView model = new ModelAndView("redirect:/produtos");
			final String URI = "http://book-payment.herokuapp.com/payment";

			try {
				// Rest para fazer a requisição -> URI, Dados do JSON, tipo da resposta.
				String resposta = restTemplate.postForObject(URI, new DadosPagamento(carrinho.getTotal()),
						String.class);

				// sucesso é um atributo da lista.jsp
				redirectAttr.addFlashAttribute("sucesso", resposta);
			} catch (HttpClientErrorException e) {
				redirectAttr.addFlashAttribute("falha", "O Valor excede o limite permitido.");
				e.printStackTrace();
			}
			return model;
		};
	}
}
