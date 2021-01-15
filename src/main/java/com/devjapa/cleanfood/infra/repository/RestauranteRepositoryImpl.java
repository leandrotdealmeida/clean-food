package com.devjapa.cleanfood.infra.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devjapa.cleanfood.domain.model.Restaurante;
import com.devjapa.cleanfood.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
	}
	
	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Override
	@Transactional
	public Restaurante salvar(Restaurante cozinha) {
		return manager.merge(cozinha);		
	}
	
	@Override
	@Transactional
	public void remover(Restaurante cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}

}
