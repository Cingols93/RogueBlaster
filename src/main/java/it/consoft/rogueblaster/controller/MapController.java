package it.consoft.rogueblaster.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.consoft.rogueblaster.model.MapModel;
import it.consoft.rogueblaster.model.enumeration.MapSizeEnum;

@RestController
public class MapController {

	 @RequestMapping("/greeting")
	    public MapModel greeting() {
	        return new MapModel(MapSizeEnum.MEDIUM);
	    }
	
}
