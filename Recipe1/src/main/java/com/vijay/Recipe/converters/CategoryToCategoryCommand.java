package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.CategoryCommand;
import com.vijay.Recipe.model.Category;

import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

	@Override
	@Nullable
	@Synchronized
	public CategoryCommand convert(Category source) {
		
		if(source==null)
		{
			return null;
		}
		final CategoryCommand categoryCommand=new CategoryCommand();
		categoryCommand.setId(source.getId());
		categoryCommand.setDescription(source.getDescription());
		return categoryCommand;
	}

}
