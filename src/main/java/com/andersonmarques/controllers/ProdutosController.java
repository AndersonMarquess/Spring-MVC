package com.andersonmarques.controllers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.andersonmarques.daos.ProdutoDao;
import com.andersonmarques.infra.FileSaver;
import com.andersonmarques.models.Produto;
import com.andersonmarques.models.TipoPreco;
import com.andersonmarques.validations.ProdutoValidation;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
	
	@Autowired
	private ProdutoDao produtoDao;
	@Autowired
	private FileSaver fileSaver;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.addValidators(new ProdutoValidation());
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Produto produto) {
		ModelAndView modelAndView = new ModelAndView("produtos/form");
		modelAndView.addObject("tipos", TipoPreco.values());
		
		return modelAndView;
	}
	
	@PostMapping()
	@CacheEvict(value="produtosHome", allEntries=true)//Limpa o cache quando faz o insert de um novo livro
	public ModelAndView insert(MultipartFile sumario, @Valid Produto produto,
						BindingResult result, RedirectAttributes redirectAttr) throws Exception {

		//Validação feita na classe ProdutoValidation...
		if(result.hasErrors()) {
			return form(produto);//Com o <form:input/> recuperamos os dados já informados
		}

		String path = fileSaver.write("arquivos-sumario", sumario);
		produto.setSumarioPath(path);
		
		
		produtoDao.insert(produto);
		//Após o post, faz o redirect para o endpoint produtos
		//corrigindo o problema de repetir o post ao usar o F5
		ModelAndView model = new ModelAndView("redirect:produtos");
		
		//Mantém o atributo no escopo de 1 request
		redirectAttr.addFlashAttribute("sucesso","Produto cadastrado com sucesso!");
		
		return model;
	}
	
	@GetMapping()
	public ModelAndView listar() {
		List<Produto> produtos = produtoDao.findAll();
		ModelAndView model = new ModelAndView("produtos/lista");
		model.addObject("produtos", produtos);
		
		return model;
	}
	
	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhe(@PathVariable("id") Integer id) {
		ModelAndView model = new ModelAndView("produtos/detalhe");
		model.addObject("produto", produtoDao.findById(id));
		return model;
	}
}
