package com.devjapa.cleanfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devjapa.cleanfood.api.model.CozinhasXmlWrapper;
import com.devjapa.cleanfood.domain.model.Cozinha;
import com.devjapa.cleanfood.domain.repository.CozinhaRepository;
import com.devjapa.cleanfood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public List<Cozinha> listar2() {
//		System.out.println("Lista 2");
//		return cozinhaRepository.listar();
//	}	

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml() {
		return new CozinhasXmlWrapper(cozinhaRepository.findAll());
	}

	// @ResponseStatus(value = HttpStatus.CREATED)
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long cozinhaId) {
		return cadastroCozinhaService.buscarOuFalhar(cozinhaId);

	}

//	//@ResponseStatus(value = HttpStatus.CREATED)
//		@GetMapping("/{cozinhaId}")
//		public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long cozinhaId) {
//			//return ResponseEntity.status(HttpStatus.OK).body(cozinhaRepository.buscar(cozinhaId));
//			//return ResponseEntity.ok(cozinhaRepository.buscar(cozinhaId));
//			
//			HttpHeaders headers = new HttpHeaders();
//			headers.add(HttpHeaders.LOCATION, "http://localhost:8080/cozinhas");
//			
//			return ResponseEntity
//					.status(HttpStatus.FOUND)
//					.headers(headers)
//					.build();
//			
//		}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cadastroCozinhaService.salvar(cozinha);
	}

	@PutMapping("/{id}")
	public Cozinha atualizar(@PathVariable("id") Long cozinhaId, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

		return cadastroCozinhaService.salvar(cozinhaAtual);

	}

//	@DeleteMapping("/{cozinhaId}")
//	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
//		try {
//			cadastroCozinhaService.exluir(cozinhaId);
//			return ResponseEntity.noContent().build();
//		}
////		catch (EntidadeNaoEncontradaException e) {
////			return ResponseEntity.notFound().build();
////			
////		}
//		catch (EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//		}
//
//	}
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
			cadastroCozinhaService.exluir(cozinhaId);
	}

}
