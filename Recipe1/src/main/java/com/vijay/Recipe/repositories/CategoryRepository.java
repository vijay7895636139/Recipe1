package com.vijay.Recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.vijay.Recipe.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	Optional<Category> findByDescription(String description);
}
