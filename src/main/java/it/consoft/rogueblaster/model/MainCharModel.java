package it.consoft.rogueblaster.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.consoft.rogueblaster.model.enumeration.CharEnum;
import it.consoft.rogueblaster.model.interfaces.Entity;
import it.consoft.rogueblaster.util.Constant;

public class MainCharModel implements Entity {

	private int str;
	private int agi;
	private int vit;
	private int lck;

	private final String identifyJSON = Constant.MAINCHAR;
	private int identifyCharJSON;

	private String name;

	private CharEnum charClass;

	public MainCharModel() {
		this.charClass = (CharEnum.getById((int) (Math.random() * 3) + 1));
		this.randomizeClassAttr(this.charClass);
		this.identifyCharJSON = this.charClass.getId();
		this.name = this.nameChar();
	}

	public int getIdentifyCharJSON() {
		return identifyCharJSON;
	}

	public void setIdentifyCharJSON(int identifyCharJSON) {
		this.identifyCharJSON = identifyCharJSON;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CharEnum getCharClass() {
		return charClass;
	}

	public void setCharClass(CharEnum charClass) {
		this.charClass = charClass;
	}

	private void randomizeClassAttr(CharEnum charEnum) {
		this.setStr(charEnum.getStr() + ((int) (Math.random() * 2)) - 1);
		this.setAgi(charEnum.getAgi() + ((int) (Math.random() * 2)) - 1);
		this.setVit(charEnum.getVit() + ((int) (Math.random() * 2)) - 1);
		this.setLck(charEnum.getLck() + ((int) (Math.random() * 2)) - 1);
		System.out.println("VITA GENERATA: " + this.vit);
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
		if (hitSuccess()) {
			int mod = 0;
			int s = (int) (Math.random() * 100) + agi;
			if (s >= 50)
				mod = str + ((int) str / 2);
			return ((int) (Math.random() * str)) + mod;
		}

		return 0;
	}

	private boolean hitSuccess() {
		int s = (int) (Math.random() * 100) + lck;
		if (s >= 20)
			return true;
		return false;
	}

	public void powerUp(ChestModel chest) {
		switch (chest.getAttr().getId()) {
		case 1: {
			this.str = this.str + chest.getMod();
			if (this.str <= 0)
				this.str = 1;
			if (this.str >= Constant.MAX_STR)
				this.str = Constant.MAX_STR;
			return;
		}
		case 2: {
			this.agi = this.agi + chest.getMod();
			if (this.agi <= 0)
				this.agi = 1;
			if (this.agi >= Constant.MAX_AGI)
				this.str = Constant.MAX_AGI;
			return;
		}
		case 3: {
			this.vit = this.vit + chest.getMod();
			if (this.vit <= 0)
				this.vit = 0;
			if (this.vit >= Constant.MAX_VIT)
				this.vit = Constant.MAX_VIT;
			return;
		}
		case 4: {
			this.lck = this.lck + chest.getMod();
			if (this.lck <= 0)
				this.lck = 0;
			if (this.lck >= Constant.MAX_LCK)
				this.lck = Constant.MAX_LCK;
			return;
		}
		default:
			return;
		}

	}

	private String nameChar() {
		int i = (int) (Math.random() * 9);
		switch (this.charClass.getId()) {
		case 1:
			return Constant.KNIGHT_NAMES[i];
		case 2:
			return Constant.ROGUE_NAMES[i];
		case 3:
			return Constant.NOVICE_NAMES[i];
		default:
			return null;
		}
	}

	public String toJSON() {
		// Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}

	@Override
	public String toString() {
		return Constant.MAINCHAR;
	}

	public String getIdentifyJSON() {
		return identifyJSON;
	}

}
