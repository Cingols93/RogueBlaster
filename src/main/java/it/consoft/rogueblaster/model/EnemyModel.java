package it.consoft.rogueblaster.model;

import it.consoft.rogueblaster.model.enumeration.CharEnum;
import it.consoft.rogueblaster.model.interfaces.Entity;
import it.consoft.rogueblaster.util.Constant;

public class EnemyModel implements Entity{
	private int str;
	private int agi;
	private int vit;
	private int lck;
	

	private final String indetifyJSON = Constant.ENEMY;

	public EnemyModel (CharEnum charEnum) {
		this.setStr(charEnum.getStr());
		this.setAgi(charEnum.getAgi());
		this.setVit(charEnum.getVit());
		this.setLck(charEnum.getLck());
	}

	public int getStr() {
		return str;
	}

	public void setStr(int str) {
		this.str = str;
	}

	public int getAgi() {
		return agi;
	}

	public void setAgi(int agi) {
		this.agi = agi;
	}

	public int getVit() {
		return vit;
	}

	public void setVit(int vit) {
		this.vit = vit;
	}

	public int getLck() {
		return lck;
	}

	public void setLck(int lck) {
		this.lck = lck;
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
	
	@Override
	public String toString() {
		return Constant.ENEMY;
	}

	public String getIndetifyJSON() {
		return indetifyJSON;
	}
	
}
