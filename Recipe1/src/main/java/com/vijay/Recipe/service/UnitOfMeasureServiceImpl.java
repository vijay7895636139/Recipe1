package com.vijay.Recipe.service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.vijay.Recipe.commands.UnitOfMeasureCommand;
import com.vijay.Recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.vijay.Recipe.repositories.UnitOfMeasureRepository;
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService{

	private final UnitOfMeasureRepository unitOfMeasureRepository;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		super();
		this.unitOfMeasureRepository = unitOfMeasureRepository;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}



	@Override
	public Set<UnitOfMeasureCommand> ListAllUoms() {
		
		return StreamSupport.stream(unitOfMeasureRepository.findAll()
				.spliterator(), false)
				.map(unitOfMeasureToUnitOfMeasureCommand::convert)
				.collect(Collectors.toSet());
	}

}
