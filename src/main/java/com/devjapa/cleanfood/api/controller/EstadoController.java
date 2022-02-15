package com.devjapa.cleanfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devjapa.cleanfood.domain.exception.EntidadeNaoEncontradaException;
import com.devjapa.cleanfood.domain.model.Cozinha;
import com.devjapa.cleanfood.domain.model.Estado;
import com.devjapa.cleanfood.domain.repository.EstadoRepository;
import com.devjapa.cleanfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Estado> buscar(@PathVariable Long id) {
		Optional<Estado> estado = estadoRepository.findById(id);

		if (estado.isPresent()) {
			return ResponseEntity.ok(estado.get());

		}

		return ResponseEntity.noContent().build();

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {		
		return cadastroEstadoService.salvar(estado);
	}

	@PutMapping("/{estadoId}")
	public ResponseEntity<?> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		
			Estado estadoAtual = estadoRepository.findById(estadoId).orElse(null);

			if (estadoAtual != null) {
				BeanUtils.copyProperties(estado, estadoAtual, "id");

				estadoAtual = cadastroEstadoService.salvar(estadoAtual);

				return ResponseEntity.ok(estadoAtual);
			}

			return ResponseEntity.notFound().build();

		
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstadoService.exluir(estadoId);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

}
