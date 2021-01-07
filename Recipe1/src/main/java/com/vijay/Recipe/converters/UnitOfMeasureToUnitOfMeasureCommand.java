package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.UnitOfMeasureCommand;
import com.vijay.Recipe.model.UnitOfMeasure;

import lombok.Synchronized;
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Override
	@Nullable
	@Synchronized
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
		if(source==null)
		{
		return null;
		}
		
		final UnitOfMeasureCommand uomc=new UnitOfMeasureCommand();
		uomc.setId(source.getId());
		uomc.setDescription(source.getDescription());
		return uomc;
	}

}
