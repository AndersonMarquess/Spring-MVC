package com.andersonmarques.models
;

import java.math.BigDecimal;

public class DadosPagamento {

	//A chave do JSON é o nome do atributo
	private BigDecimal value;

	public DadosPagamento(BigDecimal value) {
		this.value = value;
	}
	
	public DadosPagamento() { }
	
	public BigDecimal getValue() {
		return value;
	}

}
