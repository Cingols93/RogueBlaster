package it.consoft.rogueblaster.model;

import it.consoft.rogueblaster.model.enumeration.CharEnum;
import it.consoft.rogueblaster.model.interfaces.Entity;

public class EnemyModel implements Entity{
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

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void takeDamage(int d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void powerUp() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
