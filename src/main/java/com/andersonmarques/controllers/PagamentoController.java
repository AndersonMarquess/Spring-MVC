package com.andersonmarques.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andersonmarques.models.CarrinhoCompras;
import com.andersonmarques.models.DadosPagamento;
import com.andersonmarques.models.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	private CarrinhoCompras carrinho;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private MailSender sender;

	@PostMapping("/finalizar")
	// @AuthenticationPrincipal para pegar o usuário autenticado.
	// Callable para requisição assíncrono.
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes redirectAttr) {

		return () -> {
			ModelAndView model = new ModelAndView("redirect:/produtos");
			final String URI = "http://book-payment.herokuapp.com/payment";

			try {
				// Rest para fazer a requisição -> URI, Dados do JSON, tipo da resposta.
				String resposta = restTemplate.postForObject(URI, new DadosPagamento(carrinho.getTotal()),
						String.class);

				enviarEmailConfirmacaoDeCompra(usuario);

				// sucesso é um atributo da lista.jsp
				redirectAttr.addFlashAttribute("sucesso", resposta);
			} catch (HttpClientErrorException e) {
				redirectAttr.addFlashAttribute("falha", "O Valor excede o limite permitido.");
				e.printStackTrace();
			}
			return model;
		};
	}

	/**
	 * Envia um e-mail para o usuário informado.
	 * 
	 * @param usuario
	 */
	private void enviarEmailConfirmacaoDeCompra(Usuario usuario) {
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject("Compra finalizada com sucesso");
		email.setTo(usuario.getEmail());
		email.setText("Sua compra no valor de: R$ "+carrinho.getTotal()+" Foi finalizada com sucesso.");
		email.setFrom("email_de_origem@email.com");
		sender.send(email);
	}
}
