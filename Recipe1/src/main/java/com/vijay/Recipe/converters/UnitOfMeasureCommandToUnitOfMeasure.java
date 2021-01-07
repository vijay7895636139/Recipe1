package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.UnitOfMeasureCommand;
import com.vijay.Recipe.model.UnitOfMeasure;

import lombok.Synchronized;
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Override
	@Nullable
	@Synchronized
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
     if(source==null)
	  {
    	 return null;
	  }
     
     final UnitOfMeasure uom=new UnitOfMeasure();
     uom.setId(source.getId());
     uom.setDescription(source.getDescription());
     return uom;
    
	}

}
