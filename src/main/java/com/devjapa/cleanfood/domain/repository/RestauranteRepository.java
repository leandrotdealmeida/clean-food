package com.devjapa.cleanfood.domain.repository;

import java.util.List;

import com.devjapa.cleanfood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> listar();
	Restaurante buscar(Long id);
	Restaurante salvar(Restaurante cozinha);
	void remover(Long cozinha);
}
