package com.example.restservice.process.model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestService {

	 @GetMapping("/")
	  public String index() {
	    return "Greetings from Spring Boot!";
	  }
}
