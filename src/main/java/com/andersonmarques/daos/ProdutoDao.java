package com.andersonmarques.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.andersonmarques.models.Produto;

@Repository
@Transactional
public class ProdutoDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void insert(Produto produto) {
		entityManager.persist(produto);
	}
}
