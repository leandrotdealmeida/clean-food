package com.devjapa.cleanfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devjapa.cleanfood.domain.exception.EntidadeEmUsoException;
import com.devjapa.cleanfood.domain.exception.EstadoNaoEncontradaException;
import com.devjapa.cleanfood.domain.model.Estado;
import com.devjapa.cleanfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String MSG_ESTADO_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;


	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void exluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradaException(estadoId);
		}

		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}

	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EstadoNaoEncontradaException(String.format(MSG_ESTADO_EM_USO, estadoId)));
	}

}
