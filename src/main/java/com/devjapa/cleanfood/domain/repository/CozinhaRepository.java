package com.devjapa.cleanfood.domain.repository;

import java.util.List;

import com.devjapa.cleanfood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listar();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Long id);
}
