package it.consoft.rogueblaster.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/game")
public class GameController {
	
	@GetMapping(value="/start")
	public String start(HttpServletRequest request) {
		
		return "";
	}
}
