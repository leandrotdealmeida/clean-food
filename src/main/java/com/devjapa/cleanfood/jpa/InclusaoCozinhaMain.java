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
//public class InclusaoCozinhaMain {
//	
//	public static void main(String[] args) {
//		ApplicationContext applicationContext = new SpringApplicationBuilder(CleanfoodApiApplication.class)
//				.web(WebApplicationType.NONE)
//				.run(args);
//		
//		CozinhaRepository cadastroCozinha = applicationContext.getBean(CozinhaRepository.class);
//		
//		Cozinha cozinha1 = new Cozinha();
//		cozinha1.setNome("Brasileira");
//
//		Cozinha cozinha2 = new Cozinha();
//		cozinha2.setNome("Koreana");
//		
//		cozinha1 = cadastroCozinha.salvar(cozinha1);
//		cozinha2 = cadastroCozinha.salvar(cozinha2);
//		
//		System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
//		System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());
//
//	}
//
//}
