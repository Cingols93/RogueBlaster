package it.consoft.rogueblaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.consoft.rogueblaster.model.MainCharModel;
import it.consoft.rogueblaster.model.MapModel;
import it.consoft.rogueblaster.model.enumeration.CharEnum;
import it.consoft.rogueblaster.model.enumeration.ClassCharEnum;
import it.consoft.rogueblaster.model.enumeration.MapSizeEnum;

@Controller
public class GameController {

	private static MainCharModel mc = null;
	private static MapModel map = null;

	@RequestMapping(value = "/start")
	@ResponseBody
	public String startGameGet() {
		this.mc = new MainCharModel(CharEnum.KNIGHT, ClassCharEnum.KNIGHT);
		return mc.toJSON();
	}

	@PostMapping(value = "/start")
	public String startGamePost(@ModelAttribute MapModel start) {
		this.map = new MapModel(MapSizeEnum.SMALL);
		return start.toString();
	}

	@GetMapping(value = "/getmap")
	public String getMapStatus() {
		return "";
	}

	public void gameOver() {

	}

	public void update() {

	}
}
