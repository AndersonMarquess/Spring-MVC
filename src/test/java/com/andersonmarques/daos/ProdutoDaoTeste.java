package com.andersonmarques.daos;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.andersonmarques.builders.ProdutoBuilder;
import com.andersonmarques.configs.DataSourceConfigurationTeste;
import com.andersonmarques.configs.JPAConfiguration;
import com.andersonmarques.models.Produto;
import com.andersonmarques.models.TipoPreco;

/**
 * Classe de teste para os métodos do ProdutoDao.
 *
 * Anotações para rodar com o Spring Test, 
 * habilitando o PersistanceContext
 * 
 * @author Anderson Marques
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {JPAConfiguration.class, ProdutoDao.class,
		DataSourceConfigurationTeste.class})
@ActiveProfiles("teste")
public class ProdutoDaoTeste {
	@Autowired
	private ProdutoDao produtoDao;
	
	@Test
	@Transactional
	public void somarValorTotalPorTipoPreco() {
		
		List<Produto> livrosEbook = ProdutoBuilder
				.newProduto(TipoPreco.EBOOK, BigDecimal.TEN)
				.more(3).buildAll();
		
		List<Produto> livrosImpressos = ProdutoBuilder
				.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN)
				.more(3).buildAll();
		
		livrosEbook.stream().forEach(produtoDao::insert);
		livrosImpressos.stream().forEach(produtoDao::insert);
		
		BigDecimal resultEbook = produtoDao.getSomaPorTipoPreco(TipoPreco.EBOOK);
		//Deixa o 40 com apenas 2 casas decimais.
		Assert.assertEquals(new BigDecimal(40).setScale(2), resultEbook);
	}
}
