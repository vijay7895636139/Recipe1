package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.CategoryCommand;
import com.vijay.Recipe.model.Category;

import lombok.Synchronized;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category>{

	@Override
	@Nullable
	@Synchronized
	public Category convert(CategoryCommand source) {
		
		if(source==null)
		{
			return null;
		}
		
		final Category category=new Category();
		category.setId(source.getId());
		category.setDescription(source.getDescription());
		return category;
		
	}


 
}
