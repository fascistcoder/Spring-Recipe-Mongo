package com.example.springmongo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 11/09/21
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = { "recipe" })
public class Notes {
	private String id;
	private Recipe recipe;
	private String recipeNotes;
}
