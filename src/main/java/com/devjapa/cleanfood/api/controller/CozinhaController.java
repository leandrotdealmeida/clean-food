package com.devjapa.cleanfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devjapa.cleanfood.api.model.CozinhasXmlWrapper;
import com.devjapa.cleanfood.domain.model.Cozinha;
import com.devjapa.cleanfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar() {
		System.out.println("Lista 1");
		return cozinhaRepository.listar();
	}
	
//	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
//	public List<Cozinha> listar2() {
//		System.out.println("Lista 2");
//		return cozinhaRepository.listar();
//	}	
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml() {		
		return new CozinhasXmlWrapper(cozinhaRepository.listar());
	}	
	
	//@ResponseStatus(value = HttpStatus.CREATED)
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long cozinhaId) {
		return cozinhaRepository.buscar(cozinhaId);
	}

}