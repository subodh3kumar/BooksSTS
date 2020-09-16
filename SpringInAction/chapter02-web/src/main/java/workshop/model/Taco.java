package workshop.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Taco {

	@NotNull
	@Size(min = 5, message = "Name must be at least 5 character long")
	private String name;

	@NotEmpty(message = "Must choose at least 1 ingredient")
	private List<String> ingredients;
}
