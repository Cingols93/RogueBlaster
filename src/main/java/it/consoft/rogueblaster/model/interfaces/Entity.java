package it.consoft.rogueblaster.model.interfaces;

public interface Entity {
	public boolean isDead();
	public void takeDamage(int d);
	public int attack();
}
