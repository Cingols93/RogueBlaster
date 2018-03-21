package it.consoft.rogueblaster.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.consoft.rogueblaster.model.User;

@RestController(value = "/game")
public class GameController {

	@GetMapping(value = "/start")
	public String start(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return "";
	}
}
