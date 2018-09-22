package com.andersonmarques.controllers;
import javax.servlet.Filter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.andersonmarques.configs.AppWebConfiguration;
import com.andersonmarques.configs.DataSourceConfigurationTeste;
import com.andersonmarques.configs.JPAConfiguration;
import com.andersonmarques.configs.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes= {JPAConfiguration.class, AppWebConfiguration.class,
		DataSourceConfigurationTeste.class, SecurityConfiguration.class})
@ActiveProfiles("teste")
public class ProdutosControllersTeste {
	
	@Autowired
	private WebApplicationContext webappcontext;
	private MockMvc mockMvc;
	
	/* Para teste com o spring security */
	@Autowired
	private Filter[] springSecurity;
	
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webappcontext)
				.addFilters(springSecurity).build();
	}
	
	@Test
	public void verificaRetornoParaHomeComLivros() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
			.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))//verifica existência do atributo
			.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
	}
	
	@Test
	public void verificaCredenciaisDeAdminParaAcessorProdutosForm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
				.with(SecurityMockMvcRequestPostProcessors
						.user("user@qualquer.com")
						.password("123")
						.roles("USUARIO")))
		.andExpect(MockMvcResultMatchers.status().is(403));
	}
}
