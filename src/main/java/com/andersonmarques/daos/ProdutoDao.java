package com.andersonmarques.daos;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andersonmarques.models.Produto;
import com.andersonmarques.models.TipoPreco;

@Repository
@Transactional
public class ProdutoDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Produto produto) {
		entityManager.persist(produto);
	}

	public List<Produto> findAll() {
		String jpql = "SELECT p FROM Produto p";
		return entityManager.createQuery(jpql, Produto.class).getResultList();
	}

	public Produto findById(Integer id) {
		//JOIN FETCH trás o conteúdo da lista de preços
		String jpql = "SELECT p FROM Produto p JOIN FETCH p.precos WHERE p.id = :pID";
		TypedQuery<Produto> p = entityManager.createQuery(jpql, Produto.class)
											 .setParameter("pID", id);
		
		return p.getSingleResult();
	}
	
	public BigDecimal getSomaPorTipoPreco(TipoPreco tipoPreco) {
		String jpql = "SELECT SUM (preco.valor) FROM Produto p JOIN p.precos preco WHERE preco.tipo = :pTipo";
		
		return entityManager.createQuery(jpql, BigDecimal.class)
							.setParameter("pTipo", tipoPreco)
							.getSingleResult();
	}
}
