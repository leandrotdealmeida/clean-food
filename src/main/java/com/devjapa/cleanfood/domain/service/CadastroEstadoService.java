package com.devjapa.cleanfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devjapa.cleanfood.domain.exception.EntidadeEmUsoException;
import com.devjapa.cleanfood.domain.exception.EntidadeNaoEncontradaException;
import com.devjapa.cleanfood.domain.model.Estado;
import com.devjapa.cleanfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String MSG_ESTADO_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	private static final String MSG_ESTADO_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_EM_USO, estadoId)));
	}

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void exluir(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_EM_USO, estadoId));
		}

		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_NAO_ENCONTRADA, estadoId));
		}
	}

}
