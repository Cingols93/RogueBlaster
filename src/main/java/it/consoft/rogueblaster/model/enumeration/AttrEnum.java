package it.consoft.rogueblaster.model.enumeration;

public enum AttrEnum {
	STR(1),AGI(2),VIT(3),LCK(4);
	private int id;
	
	private AttrEnum(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public AttrEnum getById(int i) {
		switch(i) {
		case 1: return AttrEnum.STR;
		case 2: return AttrEnum.AGI;
		case 3: return AttrEnum.VIT;
		case 4: return AttrEnum.LCK;
		default: return null;
		}
	}
}
