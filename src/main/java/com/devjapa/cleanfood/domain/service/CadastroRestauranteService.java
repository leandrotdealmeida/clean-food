package com.devjapa.cleanfood.domain.service;

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
	
	private static final String MSG_RESTAURANTE_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format(MSG_RESTAURANTE_NAO_ENCONTRADA, cozinhaId)));
			
			restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
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
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADA, restauranteId));
		}

		catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
		}
	}

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_RESTAURANTE_NAO_ENCONTRADA, restauranteId)));
	}

	

}
