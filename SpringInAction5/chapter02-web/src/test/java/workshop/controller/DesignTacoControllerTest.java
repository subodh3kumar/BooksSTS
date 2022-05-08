package workshop.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import workshop.model.Ingredient;
import workshop.model.Ingredient.Type;
import workshop.model.Taco;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
public class DesignTacoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	List<Ingredient> ingredients;

	private Taco design;
	
	@BeforeAll
	public void init() {
		ingredients = List.of(
			new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
			new Ingredient("COTO", "Corn Tortilla", Type.WRAP), 
			new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
			new Ingredient("CARN", "Carnitas", Type.PROTEIN),
			new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), 
			new Ingredient("LETC", "Lettuce", Type.VEGGIES),
			new Ingredient("CHED", "Cheddar", Type.CHEESE), 
			new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
			new Ingredient("SLSA", "Salsa", Type.SAUCE), 
			new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
		);
		
		design = new Taco();
		design.setName("Test Taco");
		design.setIngredients(List.of("FLTO", "GRBF", "CHED"));
	}

	@Test
	public void testShowDesignPage() throws Exception {
		mockMvc.perform(get("/design"))
			.andExpect(status().isOk())
			.andExpect(model().attribute("wrap", ingredients.subList(0, 2)))
			.andExpect(model().attribute("protein", ingredients.subList(2, 4)))
			.andExpect(model().attribute("veggies", ingredients.subList(4, 6)))
			.andExpect(model().attribute("cheese", ingredients.subList(6, 8)))
			.andExpect(model().attribute("sauce", ingredients.subList(8, 10)));
			
	}
}
