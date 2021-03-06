package com.devjapa.cleanfood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.devjapa.cleanfood.domain.exception.EntidadeEmUsoException;
import com.devjapa.cleanfood.domain.exception.EntidadeNaoEncontradaException;
import com.devjapa.cleanfood.domain.model.Cozinha;
import com.devjapa.cleanfood.domain.model.Restaurante;
import com.devjapa.cleanfood.domain.repository.CozinhaRepository;
import com.devjapa.cleanfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinha == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de cozinha com código %d", cozinhaId));
		}
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}
	
//	public Restaurante atualizar(Long id, Restaurante restaurante) {
//         Restaurante restauranteAtual = restauranteRepository.buscar(id);
//         Cozinha cozinhaRest = cozinhaRepository.buscar(restauranteAtual.getCozinha().getId());
//         
//         if(cozinhaRest == null) {
//        	 throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", restauranteAtual.getCozinha().getId()));
//         }
//         
//         restauranteAtual.setCozinha(cozinhaRest);
//         BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
//         
//         return restauranteRepository.salvar(restauranteAtual);
//		
//	}

	public void exluir(Long restauranteId) {
		try {
			restauranteRepository.remover(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
		}

		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Restaurante de código %d não pode ser removida, pois está em uso", restauranteId));
		}
	}

	

}
