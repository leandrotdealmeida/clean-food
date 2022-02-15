//package com.devjapa.cleanfood.jpa;
//
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.context.ApplicationContext;
//
//import com.devjapa.cleanfood.CleanfoodApiApplication;
//import com.devjapa.cleanfood.domain.model.Cozinha;
//import com.devjapa.cleanfood.domain.repository.CozinhaRepository;
//
//public class AlteracaoCozinhaMain {
//	
//	public static void main(String[] args) {
//		ApplicationContext applicationContext = new SpringApplicationBuilder(CleanfoodApiApplication.class)
//				.web(WebApplicationType.NONE)
//				.run(args);
//		
//		CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
//		
//		Cozinha cozinha = new Cozinha();
//		cozinha.setId(1L);
//		cozinha.setNome("Cubana");
//		
//		cadastroCozinha.salvar(cozinha);
//
//	}
//
//}
