package it.consoft.rogueblaster.model;

import it.consoft.rogueblaster.model.enumeration.CharEnum;

public class EnemyModel {
	private int Str;
	private int Agi;
	private int Vit;
	private int Lck;

	public EnemyModel (CharEnum charEnum) {
		this.setStr(charEnum.getStr());
		this.setAgi(charEnum.getAgi());
		this.setVit(charEnum.getVit());
		this.setLck(charEnum.getLck());
	}

	public int getStr() {
		return Str;
	}

	public void setStr(int str) {
		Str = str;
	}

	public int getAgi() {
		return Agi;
	}

	public void setAgi(int agi) {
		Agi = agi;
	}

	public int getVit() {
		return Vit;
	}

	public void setVit(int vit) {
		Vit = vit;
	}

	public int getLck() {
		return Lck;
	}

	public void setLck(int lck) {
		Lck = lck;
	}
	
}
