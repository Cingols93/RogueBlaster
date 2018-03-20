package it.consoft.rogueblaster.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.consoft.rogueblaster.model.enumeration.MapSizeEnum;
import it.consoft.rogueblaster.util.Constant;
import it.consoft.rogueblaster.util.Tile;

public class MapModel {

	List<List<Tile>> map;

	private int width;
	private int height;

	private MapSizeEnum size;
	private int maxEnemy;
	private int maxTeasure;

	public MapModel() {
		this.width = MapSizeEnum.SMALL.getWidth();
		this.height = MapSizeEnum.SMALL.getHeight();
		this.map = new ArrayList<List<Tile>>();
		for (int i = 0; i < this.height; i++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int j = 0; j < this.width; j++) {
				row.add(new Tile(j, i));
			}
			this.map.add(row);
		}
		this.size = MapSizeEnum.SMALL;
		this.maxEnemy = Constant.MAX_ENEMY_SPAWN_SMALL;
		this.maxTeasure = Constant.MAX_TEASURES_SMALL;
	}

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
		this.size = mapSize;
		if (mapSize.equals(MapSizeEnum.SMALL)) {
			this.maxEnemy = Constant.MAX_ENEMY_SPAWN_SMALL;
			this.maxTeasure = Constant.MAX_TEASURES_SMALL;
		} else if (mapSize.equals(MapSizeEnum.MEDIUM)) {
			this.maxEnemy = Constant.MAX_ENEMY_SPAWN_MEDIUM;
			this.maxTeasure = Constant.MAX_TEASURES_MEDIUM;
		} else {
			this.maxEnemy = Constant.MAX_ENEMY_SPAWN_BIG;
			this.maxTeasure = Constant.MAX_TEASURES_BIG;
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

	public MapSizeEnum getSize() {
		return size;
	}

	public void setSize(MapSizeEnum size) {
		this.size = size;
	}

	public int getMaxEnemy() {
		return maxEnemy;
	}

	public void setMaxEnemy(int maxEnemy) {
		this.maxEnemy = maxEnemy;
	}

	public int getMaxTeasure() {
		return maxTeasure;
	}

	public void setMaxTeasure(int maxTeasure) {
		this.maxTeasure = maxTeasure;
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

	private boolean isTileEmpty(int x, int y) {
		if (this.map.get(y).get(x).getContent() == null)
			return true;
		else
			return false;
	}

	public boolean isContentEnemy(int x, int y) {
		if (this.map.get(y).get(x).getContent().getClass() == EnemyModel.class)
			return true;
		else
			return false;
	}

	public boolean isContentTeasure(int x, int y) {
		if (this.map.get(y).get(x).getContent().getClass() == ChestModel.class)
			return true;
		else
			return false;
	}

	public boolean isContentStair(int x, int y) {
		return false;
	}

	public boolean setTileContent(int x, int y, Object content) {

		if (this.getTile(x, y).getContent() == null) {
			this.getTile(x, y).setContent(content);
			return true;
		}
		return false;
	}

	public boolean moveContent(int sx, int sy, int dx, int dy) {
		if (!this.validPosition(sx, sy) || !this.validPosition(dx, dy))
			return false;
		if (this.isTileEmpty(dx, dy)) {
			this.getTile(dx, dy).setContent(this.getTile(sx, sy).getContent());
			this.getTile(sx, sy).setContent(null);
			return true;
		} else if (this.isContentEnemy(dx, dy)) {
			this.attackContent();
		} else if (this.isContentTeasure(dx, dy)) {
			this.teasureContent(sx, sy, dx, dy);
		} else if (this.isContentStair(dx, dy)) {
			// nulla al momento

		}
		return false;
	}

	public void attackContent() {
		// se si sta andando nella casella con un nemico shish
	}

	public void teasureContent(int sx, int sy, int dx, int dy) {
		ChestModel c = (ChestModel) this.map.get(dy).get(dx).getContent();
		MainCharModel mc = (MainCharModel) this.map.get(sy).get(sx).getContent();
		this.getTile(dx, dy).setContent(mc);
		this.getTile(sx, sy).setContent(null);
		mc.powerUp(c);

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
		return s;
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
		return gson.toJson(this.map);
	}

}
