package it.consoft.rogueblaster.model;

import it.consoft.rogueblaster.model.enumeration.AttrEnum;
import it.consoft.rogueblaster.model.enumeration.CharEnum;

public class ChestModel {
	private int id;
	private int str;
	private int vit;
	private int agi;
	private int lck;

	public ChestModel(AttrEnum attrenum, CharEnum charenum) {
		this.id= attrenum.getId();
		this.str= charenum.getStr();
		this.agi= charenum.getAgi();
		this.vit= charenum.getVit();
		this.lck= charenum.getLck();
	}
	
	private double powerChest(int id,int str, int vit, int agi, int lck) {
		
	}
}
