package com.devjapa.cleanfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) // reason = "Entidade não encontrada")
public abstract class EntidadeNaoEncontradaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public EntidadeNaoEncontradaException(String reason) {
		super(reason);
	}


}
