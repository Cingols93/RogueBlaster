package it.consoft.rogueblaster.model;

import it.consoft.rogueblaster.model.enumeration.CharEnum;
import it.consoft.rogueblaster.model.interfaces.Entity;
import it.consoft.rogueblaster.util.Constant;

public class EnemyModel implements Entity {
	private int str;
	private int agi;
	private int vit;
	private int lck;

	private final String indetifyJSON = Constant.ENEMY;

	public EnemyModel(CharEnum charEnum) {
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
		if (this.vit <= 0)
			return true;
		return false;
	}

	@Override
	public void takeDamage(int d) {
		int dmg = this.vit - d;
		setVit(dmg);
	}

	@Override
	public int attack() {
		if (hitSuccess())
			return (int) (Math.random() * str);
		return 0;
	}

	private boolean hitSuccess() {
		int s = (int) (Math.random() * 100) + lck;
		if (s >= 20)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return Constant.ENEMY;
	}

	public String getIndetifyJSON() {
		return indetifyJSON;
	}

}
