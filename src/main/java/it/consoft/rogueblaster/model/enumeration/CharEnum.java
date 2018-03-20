package it.consoft.rogueblaster.model.enumeration;

import it.consoft.rogueblaster.util.Constant;

public enum CharEnum {
	KNIGHT(1, Constant.KNIGHT_STR, Constant.KNIGHT_AGI, Constant.KNIGHT_VIT, Constant.KNIGHT_LCK), ROGUE(2,
			Constant.ROGUE_STR, Constant.ROGUE_AGI, Constant.ROGUE_VIT, Constant.ROGUE_LCK), NOVICE(3,
					Constant.NOVICE_STR, Constant.NOVICE_AGI, Constant.NOVICE_VIT, Constant.NOVICE_LCK);

	private int str;
	private int agi;
	private int vit;
	private int lck;

	private int id;

	private CharEnum(int i, int str, int agi, int vit, int lck) {
		this.id = i;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public void setAgi(int agi) {
		this.agi = agi;
	}

	public void setVit(int vit) {
		this.vit = vit;
	}

	public void setLck(int lck) {
		this.lck = lck;
	}

	public static CharEnum getById(int i) {
		switch (i) {
		case 1:
			return CharEnum.KNIGHT;
		case 2:
			return CharEnum.ROGUE;
		case 3:
			return CharEnum.NOVICE;
		default:
			return null;
		}
	}

}
