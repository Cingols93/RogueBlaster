package it.consoft.rogueblaster.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.consoft.rogueblaster.model.enumeration.AttrEnum;
import it.consoft.rogueblaster.model.enumeration.CharEnum;
import it.consoft.rogueblaster.model.enumeration.MapSizeEnum;
import it.consoft.rogueblaster.util.Tile;

public class MapModel {

	List<List<Tile>> map;

	private int width;
	private int height;

	public MapModel(MapSizeEnum mapSize) {
		this.width = mapSize.getWidth();
		this.height = mapSize.getHeight();
		this.map = new ArrayList<List<Tile>>();
		for (int i = 0; i < this.height; i++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int j = 0; j < this.width; j++) {
				row.add(new Tile(j, i));
			}
			this.map.add(row);
		}
	}

	public List<List<Tile>> getMap() {
		return map;
	}

	public void setMap(List<List<Tile>> map) {
		this.map = map;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private boolean validPosition(int x, int y) {
		if (x < 0 || x > this.width)
			return false;
		else if (y < 0 || y > this.height)
			return false;
		return true;
	}

	public Tile getTile(int x, int y) {
		return this.map.get(y).get(x);
	}

	public boolean isTileEmpty(int x, int y) {
		return (this.map.get(y).get(x) == null) ? true : false;
	}

	public boolean isContentEnemy(int x, int y) {
		return false;
	}

	public boolean isContentTeasure(int x, int y) {
		return false;
	}

	public boolean isContentStair(int x, int y) {
		return false;
	}

	public void moveContent(int sx, int sy, int dx, int dy) {
		if (this.validPosition(sx, sy) && this.validPosition(dx, dy))
			return;
		if (this.isTileEmpty(dx, dy)) {
			this.getTile(dx, dy).setContent(this.getTile(sx, sy).getContent());
			this.getTile(sx, sy).setContent(null);
		} else if (this.isContentEnemy(dx, dy)) {
			this.attackContent();
		} else if (this.isContentTeasure(dx, dy)) {
			this.teasureContent();
		} else if (this.isContentStair(dx, dy)) {
			// nulla al momento
			
		}
	}

	public void attackContent() {
		// se si sta andando nella casella con un nemico
	}

	public void teasureContent() {
		// se si sta andando nella casella con un tesoro
	}

	@Override
	public String toString() {

		String s = "";

		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.getTile(j, i).getContent() != null)
					s += this.getTile(j, i).getContent().toString();
				else
					s += "O";
				s += " ";
			}
			s += "\n";
		}

		return "MapModel [map=" + map + ", width=" + width + ", height=" + height + "]\n" + s + "\n" + this.toJSON();
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();;
		return gson.toJson(this.map);
	}

	public static void main(String[] args) {
		MapModel m = new MapModel(MapSizeEnum.MEDIUM);
		m.getTile(1, 2).setContent(new MainCharModel(CharEnum.KNIGHT));
		m.getTile(6, 5).setContent(new ChestModel(AttrEnum.STR));
		System.out.println(m.toString());
	}
}
