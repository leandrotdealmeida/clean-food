package com.devjapa.cleanfood.infra.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.devjapa.cleanfood.domain.model.FormaPagamento;
import com.devjapa.cleanfood.domain.repository.FormaPagamentoRepository;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<FormaPagamento> listar() {
		return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
	}
	
	@Override
	public FormaPagamento buscar(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	@Override
	@Transactional
	public FormaPagamento salvar(FormaPagamento pagamento) {
		return manager.merge(pagamento);		
	}
	
	@Override
	@Transactional
	public void remover(FormaPagamento pagamento) {
		pagamento = buscar(pagamento.getId());
		manager.remove(pagamento);
	}

}
