package it.consoft.rogueblaster.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import it.consoft.rogueblaster.model.enumeration.MapSizeEnum;
import it.consoft.rogueblaster.model.interfaces.Entity;
import it.consoft.rogueblaster.util.Constant;
import it.consoft.rogueblaster.util.Tile;

public class MapModel {

	List<List<Tile>> map;

	private int width;
	private int height;

	private MapSizeEnum size;
	private int maxEnemy;
	private int maxChest;

	private boolean mainAlive;
	private boolean enemySlayed;

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
		this.maxChest = Constant.MAX_TEASURES_SMALL;
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
			this.maxChest = Constant.MAX_TEASURES_SMALL;
		} else if (mapSize.equals(MapSizeEnum.MEDIUM)) {
			this.maxEnemy = Constant.MAX_ENEMY_SPAWN_MEDIUM;
			this.maxChest = Constant.MAX_TEASURES_MEDIUM;
		} else {
			this.maxEnemy = Constant.MAX_ENEMY_SPAWN_BIG;
			this.maxChest = Constant.MAX_TEASURES_BIG;
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
		return maxChest;
	}

	public void setMaxTeasure(int maxTeasure) {
		this.maxChest = maxTeasure;
	}

	public boolean isMainAlive() {
		return mainAlive;
	}

	public void setMainAlive(boolean mainAlive) {
		this.mainAlive = mainAlive;
	}

	public boolean isEnemySlayed() {
		return enemySlayed;
	}

	public void setEnemySlayed(boolean enemySlayed) {
		this.enemySlayed = enemySlayed;
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

	public int moveMainChar(int sx, int sy, int dx, int dy) {
		if (!this.validPosition(sx, sy) || !this.validPosition(dx, dy))
			return -1;
		if (this.isTileEmpty(dx, dy)) {
			this.getTile(dx, dy).setContent(this.getTile(sx, sy).getContent());
			this.getTile(sx, sy).setContent(null);
			return 0;
		} else if (this.isContentEnemy(dx, dy)) {
			if (this.attackContent(this.getTile(sx, sy), this.getTile(dx, dy))) {
				this.getTile(dx, dy).setContent(this.getTile(sx, sy).getContent());
				this.getTile(sx, sy).setContent(null);
				this.setEnemySlayed(true);
				return 1;
			}
			return 2;
		} else if (this.isContentTeasure(dx, dy)) {
			this.teasureContent(sx, sy, dx, dy);
			return 0;
		} else if (this.isContentStair(dx, dy)) {
			// nulla al momento

		}
		return -1;
	}

	/*
	 * 1 = MainChar DEAD -1 = Null 0 = Move 2 = Attack
	 */
	public int moveEnemy(int sx, int sy, int dx, int dy) {
		int[] checkMain = this.checkAround(sx, sy);
		if (checkMain[0] != -1) {
			Tile tEnemy = this.getTile(sx, sy);
			Tile tMain = this.getTile(checkMain[0], checkMain[1]);
			System.out.println(tEnemy.getContent().toString());
			System.out.println(tMain.getContent().toString());
			if (this.attackContent(tEnemy, tMain)) {
				this.setMainAlive(false);
				return 1;
			}
			return 2;
		} else if (!this.validPosition(sx, sy) || !this.validPosition(dx, dy) || (sx == dx && sy == dy)) {
			return -1;
		} else if (this.isTileEmpty(dx, dy)) {
			this.getTile(dx, dy).setContent(this.getTile(sx, sy).getContent());
			this.getTile(sx, sy).setContent(null);
			return 0;
		}
		return -1;
	}

	private int[] checkAround(int x, int y) {
		int[] rs = new int[2];

		for (int j = y - 1; j <= y + 1; j++) {
			for (int k = x - 1; k <= x + 1; k++) {
				if (this.validPosition(k, j)) {
					if (this.getTile(k, j).getContent() != null
							&& this.getTile(k, j).getContent().getClass() == (MainCharModel.class)) {
						rs[0] = k;
						rs[1] = j;
						System.out.println("Il nemico si guarda attorno" + k + " " + j);
						return rs;
					}
				}
			}
		}
		rs[0] = -1;
		rs[1] = -1;

		return rs;
	}

	private boolean attackContent(Tile e1, Tile e2) {
		Entity a = (Entity) e1.getContent();
		Entity d = (Entity) e2.getContent();
		System.out.println(a.getClass());
		int dmg = a.attack();
		System.out.println("Danno effettuato: " + dmg);
		d.takeDamage(dmg);
		if (d.isDead())
			return true;

		return false;
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
					s += "-";
				s += " ";
			}
			s += "\n";
		}
		return s;
	}

	public String toJSON() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}

}
