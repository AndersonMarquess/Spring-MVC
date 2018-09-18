package com.andersonmarques.models;

public class CarrinhoItem {

	private Produto produto;
	private TipoPreco tipoPreco;

	public CarrinhoItem(Produto p, TipoPreco tipo) {
		this.produto = p;
		this.tipoPreco = tipo;
	}

	public Produto getP() {
		return produto;
	}

	public void setP(Produto p) {
		this.produto = p;
	}

	public TipoPreco getTipo() {
		return tipoPreco;
	}

	public void setTipo(TipoPreco tipo) {
		this.tipoPreco = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((tipoPreco == null) ? 0 : tipoPreco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarrinhoItem other = (CarrinhoItem) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (tipoPreco != other.tipoPreco)
			return false;
		return true;
	}
}
