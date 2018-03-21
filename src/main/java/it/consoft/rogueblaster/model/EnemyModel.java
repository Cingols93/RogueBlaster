package it.consoft.rogueblaster.model;

import it.consoft.rogueblaster.model.interfaces.Entity;
import it.consoft.rogueblaster.util.Constant;

public class EnemyModel implements Entity {
	private int str;
	private int agi;
	private int vit;
	private int lck;

	private final String indetifyJSON = Constant.ENEMY;

	public EnemyModel() {
		this.randomizeClassAttr();
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

	private void randomizeClassAttr() {
		this.str = this.agi = this.vit = this.lck = 0;
		while (this.str <= 0) {
			this.generateAttr(1);
		}
		while (this.agi <= 0) {
			this.generateAttr(2);
		}
		while (this.vit <= 0) {
			this.generateAttr(3);
		}
		while (this.lck <= 0) {
			this.generateAttr(4);
		}
	}

	@Override
	public boolean isDead() {
		if (this.vit <= 0)
			return true;
		return false;
	}

	@Override
	public void takeDamage(int d) {
		this.vit -= d;
	}

	@Override
	public int attack() {
		if (hitSuccess())
			return (int) ((Math.random() * str) + 1);
		return 0;
	}

	private boolean hitSuccess() {
		int s = (int) ((Math.random() * 100) + lck);
		System.out.println(s);
		if (s >= 50)
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

	private void generateAttr(int i) {
		switch (i) {
		case 1: {
			this.str = (int) (Math.random() * 5) + 1;
			if (this.str == 0) {
				this.generateAttr(1);
			}
		}
		case 2: {
			this.agi = (int) (Math.random() * 5) + 1;
			if (this.agi == 0) {
				this.generateAttr(2);
			}
		}
		case 3: {
			this.vit = (int) (Math.random() * 5) + 1;
			if (this.vit == 0) {
				this.generateAttr(3);
			}
		}
		case 4: {
			this.lck = (int) (Math.random() * 5) + 1;
			if (this.lck == 0) {
				this.generateAttr(4);
			}
		}
		default: {
			break;
		}
		}
	}

}
