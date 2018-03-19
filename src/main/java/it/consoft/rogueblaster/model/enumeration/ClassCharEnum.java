package it.consoft.rogueblaster.model.enumeration;

public enum ClassCharEnum {
	KNIGHT(1), ROGUE(2), NOVICE(3);
	private int idClassChar;

	private ClassCharEnum(int id) {
		this.idClassChar = id;
	}

	public int getIdClassChar() {
		return idClassChar;
	}

	public ClassCharEnum getByIdClassChar(int i) {
		switch (i) {
		case 1:
			return ClassCharEnum.KNIGHT;
		case 2:
			return ClassCharEnum.ROGUE;
		case 3:
			return ClassCharEnum.NOVICE;
		default:
			return null;
		}
	}

}
