package com.andersonmarques.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION)
public class CarrinhoCompras implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Map<CarrinhoItem, Integer> itens = new LinkedHashMap<>();

	public void add(CarrinhoItem item) {
		itens.put(item, getQuantidade(item) + 1);
	}

	/**
	 * Se o item não existir, colocamos sua referência com a quantidade 0,
	 * o método add é responsável por incrementar a quantidade.
	 * 
	 * Se o item já existe, então ele só retorna seu valor(Quantidade),
	 * para o add incrementar.
	 * 
	 * @param item
	 * @return
	 */
	public Integer getQuantidade(CarrinhoItem item) {
		if(!itens.containsKey(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		//Soma a quantidade total de itens
		return itens.values().stream().reduce(0, (p, a) -> p + a);
	}
	
	public Collection<CarrinhoItem> getItens() {
		return itens.keySet();
	}
	
	public BigDecimal getTotal(CarrinhoItem item) {
		return item.getTotal(getQuantidade(item));
	}
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for(CarrinhoItem item : itens.keySet()) {
			total = total.add(getTotal(item));
		}
		return total;
	}
}
