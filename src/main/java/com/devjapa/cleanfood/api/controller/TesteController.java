package com.devjapa.cleanfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjapa.cleanfood.domain.model.Cozinha;
import com.devjapa.cleanfood.domain.model.Restaurante;
import com.devjapa.cleanfood.domain.repository.CozinhaRepository;
import com.devjapa.cleanfood.domain.repository.RestauranteRepository;
import com.devjapa.cleanfood.infra.repository.spec.RestauranteComFreteGratisSpec;
import com.devjapa.cleanfood.infra.repository.spec.RestauranteComNomeSemelhanteSpec;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping(path = "/cozinha-por-nome",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> buscaCozinhaPorNome(String nome) {
		return cozinhaRepository.findTodasByNomeContaining(nome);
	}
	
	@GetMapping(path = "/cozinha-por-nome-unico",produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Cozinha> buscaCozinhaPorNomeUnico(String nome) {
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping(path = "/cozinha-exists",produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean cozinhaExists(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}
	
	@GetMapping(path = "/restaurante-por-taixa-frete",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurante> restaurantePorTaixaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping(path = "/restaurante-por-nome",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurante> restaurantePorTaixaFrete(String nome, Long cozinhaId) {
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
	}
	
	@GetMapping(path = "/primeiro-por-nome",produces = MediaType.APPLICATION_JSON_VALUE)
	public Optional<Restaurante> restaurantePrimeiroPorNome(String nome) {
		return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
	}
	
	@GetMapping(path = "/top2-por-nome",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurante> restauranteTop2PorNome(String nome) {
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping(path = "/count-por-cozinha",produces = MediaType.APPLICATION_JSON_VALUE)
	public int restauranteTop2PorNome(Long cozinhaId) {
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	
	@GetMapping(path = "/restaurante-por-nome-frete")
	public List<Restaurante> restaurantePorNomeTaixaFrete(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}
	
	@GetMapping(path = "/restaurante-por-nome-frete-criteria")
	public List<Restaurante> restaurantePorNomeTaixaFreteCriteria(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findCriteria(nome, taxaInicial, taxaFinal);
	}
	
	@GetMapping(path = "/restaurante-com-frete-gratis")
	public List<Restaurante> restauranteComFreteGratis(String nome) {
		var comFreteGratis = new RestauranteComFreteGratisSpec();
		var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
		
		return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
	}

}
