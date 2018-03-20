package it.consoft.rogueblaster.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.consoft.rogueblaster.model.MapModel;
import it.consoft.rogueblaster.model.enumeration.MapSizeEnum;

@RestController
public class MapController {

	@GetMapping(value = "/getmap")
	public String greeting() {
		MapModel mp = new MapModel(MapSizeEnum.MEDIUM);
		return mp.toJSON();
	}

}
