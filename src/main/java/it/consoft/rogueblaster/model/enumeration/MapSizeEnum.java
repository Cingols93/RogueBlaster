package it.consoft.rogueblaster.model.enumeration;

import it.consoft.rogueblaster.util.Constant;;

public enum MapSizeEnum {
	SMALL(Constant.MAP_WIDTH_SMALL, Constant.MAP_HEIGHT_SMALL),
	MEDIUM(Constant.MAP_WIDTH_MEDIUM, Constant.MAP_HEIGHT_MEDIUM),
	BIG(Constant.MAP_WIDTH_BIG, Constant.MAP_HEIGHT_BIG);
	
	private int width;
	private int height;
	
	MapSizeEnum(int w, int h) {
		this.width = w;
		this.height = h;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
