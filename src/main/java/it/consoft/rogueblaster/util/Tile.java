package it.consoft.rogueblaster.util;

public class Tile {
	private int x;
	private int y;
	
	private Object content;
	
	public Tile() {
		this.x = -1;
		this.y = -1;
		this.content = null;
	}
	
	public Tile(int _x, int _y) {
		this.x = _x;
		this.y = _y;
		this.content = null;
	}
	
	public Tile(int _x, int _y, Object _content) {
		this.x = _x;
		this.y = _y;
		this.content = _content;		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
