package it.consoft.rogueblaster.model;

import it.consoft.rogueblaster.model.enumeration.AttrEnum;
import it.consoft.rogueblaster.util.Constant;

public class ChestModel {
	private AttrEnum attr;
	private int mod;

	public ChestModel(AttrEnum a) {
		this.attr= a;
		this.generateMod();
	}
	
	public AttrEnum getAttr() {
		return attr;
	}

	public void setAttr(AttrEnum attr) {
		this.attr = attr;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	private void generateMod(){
		this.mod= (int) (Math.random()*4)-2;
		if(this.mod==0) {
			this.generateMod();
		}
	}
	
	@Override
	public String toString() {
		return Constant.CHEST;
	}
	
	
	
	
	
}
