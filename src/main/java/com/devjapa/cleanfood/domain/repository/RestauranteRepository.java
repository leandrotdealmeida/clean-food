package com.devjapa.cleanfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjapa.cleanfood.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

}
