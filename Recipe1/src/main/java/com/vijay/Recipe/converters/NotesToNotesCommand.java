package com.vijay.Recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.sun.istack.Nullable;
import com.vijay.Recipe.commands.NotesCommand;
import com.vijay.Recipe.model.Notes;

import lombok.Synchronized;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

	@Nullable
	@Synchronized
	@Override
	public NotesCommand convert(Notes source) {
	 
		if(source==null)
		{
		return null;
		}
		
		final NotesCommand notesCommand=new NotesCommand();
		notesCommand.setId(source.getId());
		notesCommand.setRecipeNotes(source.getRecipeNotes());
		return notesCommand;
	}

}
