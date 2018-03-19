package it.consoft.rogueblaster.model.enumeration;

import it.consoft.rogueblaster.util.Constant;

public enum CharEnum {
	ROGUE(Constant.ROGUE_STR, Constant.ROGUE_AGI,Constant.ROGUE_VIT,Constant.ROGUE_LCK),
	KNIGHT(Constant.KNIGHT_STR, Constant.KNIGHT_AGI,Constant.KNIGHT_VIT,Constant.KNIGHT_LCK),
	NOVICE(Constant.NOVICE_STR, Constant.NOVICE_AGI,Constant.NOVICE_VIT,Constant.NOVICE_LCK);
	
	private int str;
	private int agi;
	private int vit;
	private int lck;
	
	private CharEnum(int str, int agi, int vit, int lck) {
		this.str = str;
		this.agi = agi;
		this.vit = vit;
		this.lck = lck;
	}
	
	public int getStr() {
		return str;
	}
	public int getAgi() {
		return agi;
	}
	public int getVit() {
		return vit;
	}
	public int getLck() {
		return lck;
	}
	
	
}
