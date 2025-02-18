package com.vijay.Recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vijay.Recipe.model.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long>{

	 Optional<UnitOfMeasure> findByDescription(String description);
}