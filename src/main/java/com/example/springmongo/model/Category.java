package com.example.springmongo.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 13/09/21
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = { "recipes" })
public class Category {
	private String id;

	private String description;

	private Set<Recipe> recipes;

}
