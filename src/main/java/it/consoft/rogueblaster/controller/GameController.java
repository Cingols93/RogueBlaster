package it.consoft.rogueblaster.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.consoft.rogueblaster.model.GameModel;
import it.consoft.rogueblaster.model.MainCharModel;
import it.consoft.rogueblaster.model.MapModel;
import it.consoft.rogueblaster.model.enumeration.MapSizeEnum;

@RestController
@RequestMapping("/game")
@Scope(value = "session")
public class GameController {

	public MainCharModel createCharacter() {
		MainCharModel mc = new MainCharModel();
		return mc;
	}

	public MapModel createMap(String sz) {
		MapModel map = null;
		if (map == null) {
			switch (sz) {
			case "1": {
				map = new MapModel(MapSizeEnum.SMALL);
				break;
			}
			case "2": {
				map = new MapModel(MapSizeEnum.MEDIUM);
				break;
			}
			case "3": {
				map = new MapModel(MapSizeEnum.BIG);
				break;
			}
			}
		}
		return map;
	}

	@GetMapping("/getgame")
	public String getGame(@RequestParam(value = "sz", required = false) String sz) {
		GameModel game = new GameModel();
		MainCharModel mc = this.createCharacter();
		MapModel map = this.createMap(sz);
		game.setup(map, mc);
		return game.toJSON();
	}

}
