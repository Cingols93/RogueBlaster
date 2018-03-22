package it.consoft.rogueblaster.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
public class GameController {

	@GetMapping("/getcharacter")
	public String getCharacter(HttpServletRequest request, HttpServletResponse response) {
		MainCharModel mc = (MainCharModel) request.getSession().getAttribute("mc");
		if (mc == null) {
			mc = new MainCharModel();
			request.getSession().setAttribute("mc", mc);
		}
		return mc.toJSON();
	}

	@GetMapping("/getmap")
	public String start(@RequestParam(value = "sz", required = false) String sz, HttpServletRequest request,
			HttpServletResponse response) {
		MapModel map = (MapModel) request.getSession().getAttribute("map");
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
			request.getSession().setAttribute("map", map);
		}
		return map.toJSON();
	}

	@GetMapping("/getgame")
	public String getGame(HttpServletRequest request, HttpServletResponse response) {
		GameModel game = (GameModel) request.getSession().getAttribute("game");
		MainCharModel mc = (MainCharModel) request.getSession().getAttribute("mc");
		MapModel map = (MapModel) request.getSession().getAttribute("map");
		if (game == null) {
			game = new GameModel();
			game.setup(map, mc);
			request.getSession().setAttribute("game", game);
		}
		return game.toJSON();
	}

}
