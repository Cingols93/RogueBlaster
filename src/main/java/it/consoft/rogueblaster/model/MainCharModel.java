package it.consoft.rogueblaster.model;

import it.consoft.rogueblaster.model.enumeration.MapSizeEnum;
import it.consoft.rogueblaster.util.Tile;

public class MainCharModel {

	private int Str;
	private int Agi;
	private int Vit;
	private int Lck;

	public MainCharModel (CharEnum charEnum) {
		this.Str = charEnum.getStr();
		this.Agi = charEnum.getAgi();
		this.Vit = charEnum.getVit();
		this.Lck = charEnum.getLck();
	}
	
}
