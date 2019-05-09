package com.biblioteca.resource;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/getversion")
@CrossOrigin(origins = "http://localhost:4200")
public class VersaoResource {

	@GetMapping
	public String getversion() {
		return "1.0";
	}
}
