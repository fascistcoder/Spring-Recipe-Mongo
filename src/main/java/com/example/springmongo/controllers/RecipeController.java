package com.example.springmongo.controllers;

import com.example.springmongo.commands.RecipeCommand;
import com.example.springmongo.exceptions.NotFoundException;
import com.example.springmongo.services.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author <a href="pulkit.aggarwal@upgrad.com">Pulkit Aggarwal</a>
 * @version 1.0
 * @since 27/09/21
 */
@Slf4j
@Controller
@AllArgsConstructor
public class RecipeController {
	private static final String RECIPE_RECIPEFORM_URL = "recipe/recipeform";
	private final RecipeService recipeService;

	@GetMapping("/recipe//{id}/show")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findById(id));
		return "recipe/show";
	}

	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute("recipe", new RecipeCommand());
		return "recipe/recipeform";
	}

	@GetMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(id));
		return RECIPE_RECIPEFORM_URL;
	}

	@PostMapping("recipe")
	public String saveOrUpdate(@Valid @ModelAttribute("recipe") RecipeCommand command, Errors errors) {

		if (errors.hasErrors()) {
			return RECIPE_RECIPEFORM_URL;
		}

		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}

	@GetMapping("recipe/{id}/delete")
	public String deleteById(@PathVariable String id) {
		log.debug("Deleting id: " + id);
		recipeService.deleteById(id);
		return "redirect:/";
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ModelAndView handleNotFound(Exception exception) {
		log.error("Handling not found exception");
		log.error(exception.getMessage());

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.setViewName("404error");
		modelAndView.addObject("exception", exception);

		return modelAndView;
	}
}
