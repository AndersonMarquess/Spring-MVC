package com.andersonmarques.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CarrinhoCompras {
	
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
	private int getQuantidade(CarrinhoItem item) {
		if(!itens.containsValue(item)) {
			itens.put(item, 0);
		}
		return itens.get(item);
	}
	
	public int getQuantidade() {
		//Soma a quantidade total de itens
		return itens.values().stream().reduce(0, (p, a) -> p + a);
	}
}
