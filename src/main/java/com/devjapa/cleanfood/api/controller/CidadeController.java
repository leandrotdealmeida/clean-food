package com.devjapa.cleanfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjapa.cleanfood.domain.exception.EntidadeNaoEncontradaException;
import com.devjapa.cleanfood.domain.exception.EstadoNaoEncontradaException;
import com.devjapa.cleanfood.domain.exception.NegocioException;
import com.devjapa.cleanfood.domain.model.Cidade;
import com.devjapa.cleanfood.domain.repository.CidadeRepository;
import com.devjapa.cleanfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@GetMapping
	public List<Cidade> listar() {
		return cidadeRepository.findAll();
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable Long cidadeId) {
		 return cadastroCidadeService.buscarOuFalhar(cidadeId);
		
	}

	@PostMapping
	public Cidade adicionar(@RequestBody Cidade cidade) {
		try {

			return cadastroCidadeService.salvar(cidade);

		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		// dentro do Optional, ou null, caso ele esteja vazio,
		// mas nesse caso, temos a responsabilidade de tomar cuidado com
		// NullPointerException
		Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);

		BeanUtils.copyProperties(cidade, cidadeAtual, "id");

		try {

			return ResponseEntity.ok(cadastroCidadeService.salvar(cidadeAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}
	
	@DeleteMapping("/{cidadeId}")
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidadeService.exluir(cidadeId);

	}

}
