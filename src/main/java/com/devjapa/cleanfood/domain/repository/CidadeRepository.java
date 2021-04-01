package com.devjapa.cleanfood.domain.repository;

import java.util.List;

import com.devjapa.cleanfood.domain.model.Cidade;

public interface CidadeRepository {

	List<Cidade> listar();
	Cidade buscar(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Long cidadeId);
}
