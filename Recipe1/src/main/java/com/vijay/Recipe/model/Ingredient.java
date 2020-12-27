package com.vijay.Recipe.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Entity
@Data
@EqualsAndHashCode(exclude = {"recipe"})

public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String description;
	private BigDecimal amount;
	@ManyToOne
	private Recipe recipe;
	
	@OneToOne(fetch=FetchType.EAGER)
	private UnitOfMeasure uom;
	
	
	  
	  public Ingredient(String description, BigDecimal amount, UnitOfMeasure uom) {
	  super(); this.description = description; this.amount = amount; this.uom =
	  uom; }
	 
	  public Ingredient(String description, BigDecimal amount, UnitOfMeasure
	  uom,Recipe recipe) { super(); this.description = description; this.amount =
      amount; this.uom = uom; this.recipe=recipe; }

	public Ingredient() {
		super();
	}
	 
}
