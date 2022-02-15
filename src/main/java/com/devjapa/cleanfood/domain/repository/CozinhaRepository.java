package com.devjapa.cleanfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjapa.cleanfood.domain.model.Cozinha;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
}
