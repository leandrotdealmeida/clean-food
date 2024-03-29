//package com.devjapa.cleanfood.infra.repository;
//
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.devjapa.cleanfood.domain.model.Cozinha;
//import com.devjapa.cleanfood.domain.repository.CozinhaRepository;
//
//@Repository
//public class CozinhaRepositoryImpl implements CozinhaRepository {
//
//	@PersistenceContext
//	private EntityManager manager;
//	
//	@Override
//	public List<Cozinha> listar() {
//		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
//	}
//	
//	@Override
//	public Cozinha buscar(Long id) {
//		return manager.find(Cozinha.class, id);
//	}
//	
//	@Override
//	@Transactional
//	public Cozinha salvar(Cozinha cozinha) {
//		return manager.merge(cozinha);		
//	}
//	
//	@Override
//	@Transactional
//	public void remover(Long id) {
//		Cozinha cozinha = buscar(id);
//		
//		if(cozinha == null) {
//			throw new EmptyResultDataAccessException(1);
//		}
//		manager.remove(cozinha);
//	}
//
//}
