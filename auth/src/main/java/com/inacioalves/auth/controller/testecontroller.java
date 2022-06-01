package com.inacioalves.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "teste")
public class testecontroller {
	
	@GetMapping
	public String teste() {
		return "teste aprovado";
	}

}
