package it.consoft.rogueblaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class RogueBlasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RogueBlasterApplication.class, args);
	}
}
