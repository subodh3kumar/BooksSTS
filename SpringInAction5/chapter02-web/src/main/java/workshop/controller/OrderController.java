package workshop.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import workshop.model.Order;

@Slf4j
@Controller
@RequestMapping("/orders")
public class OrderController {
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@GetMapping("/current")
	public String orderForm(Model model) {
		log.info("orderForm() method called");
		model.addAttribute("order", new Order());
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors) {
		log.info("processOrder() method called");
		if (errors.hasErrors()) {
			return "orderForm";
		}
		log.info("order submitted: " + order);
		return "redirect:/";
	}
}
