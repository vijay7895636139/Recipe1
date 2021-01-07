package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.NotesCommand;
import com.vijay.Recipe.model.Notes;

import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes>{

	@Override
	@Nullable
	@Synchronized
	public Notes convert(NotesCommand source) {

		if(source==null)
		{
		return null;
		}
		
	     final Notes notes=new Notes();
	     notes.setId(source.getId());
	     notes.setRecipeNotes(source.getRecipeNotes());
		
		return notes;
	}

}
