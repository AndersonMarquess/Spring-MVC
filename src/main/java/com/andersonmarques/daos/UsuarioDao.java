package com.andersonmarques.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.andersonmarques.models.Usuario;

@Repository
public class UsuarioDao implements UserDetailsService {
	@PersistenceContext
	private EntityManager manager;
	
	public Usuario loadUserByUsername(String email) {
		String jpql = "SELECT u FROM Usuario u WHERE u.email = :pEmail";
		Usuario usuario = manager.createQuery(jpql, Usuario.class)
				.setParameter("pEmail", email)
				.getSingleResult();
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário com email: "+email+" não encontrado.");
		}
		return usuario;
	}
}
