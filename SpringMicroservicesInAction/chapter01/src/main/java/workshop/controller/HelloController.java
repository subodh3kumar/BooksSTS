package workshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

	@GetMapping("/{firstName}/{lastName}")
	public ResponseEntity<String> greeting(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		log.info("greeting() method called");
		String msg = "Hello, Mr. " + firstName + " " + lastName;
		log.info("msg: {}", msg);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
