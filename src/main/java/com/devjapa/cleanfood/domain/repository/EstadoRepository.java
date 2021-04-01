package com.devjapa.cleanfood.domain.repository;

import java.util.List;

import com.devjapa.cleanfood.domain.model.Estado;

public interface EstadoRepository {

	List<Estado> listar();
	Estado buscar(Long id);
	Estado salvar(Estado estado);
	void remover(Long estadoId);
}
