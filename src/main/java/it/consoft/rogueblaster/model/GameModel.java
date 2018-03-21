package it.consoft.rogueblaster.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;

import it.consoft.rogueblaster.model.enumeration.AttrEnum;
import it.consoft.rogueblaster.model.enumeration.MapSizeEnum;

public class GameModel {

	private MainCharModel mc = null;
	private MapModel map = new MapModel();

	private int[] charCoords;
	private List<int[]> enemiesCoords;

	public GameModel() {
		this.startGameGet(1);
	}
	
	public GameModel(int size) {
		this.startGameGet(size);
	}
	
	public String startGameGet(int sz) {
		this.mc = new MainCharModel();
		if (sz > 0) {
			switch (sz) {
			case 1: {
				this.map = new MapModel(MapSizeEnum.SMALL);
				break;
			}
			case 2: {
				this.map = new MapModel(MapSizeEnum.MEDIUM);
				break;
			}
			case 3: {
				this.map = new MapModel(MapSizeEnum.BIG);
				break;
			}
			default: {
				break;
			}
			}
			this.setup();
			return this.map.toJSON();
		}
		return this.mc.toJSON();
	}

	public void gameOver() {

	}

	public void update() {

	}

	private void setup() {
		EnemyModel enemy;
		ChestModel chest;
		this.enemiesCoords = new ArrayList<int[]>();

		int[] coords = this.generateCoords();
		while (!this.map.setTileContent(coords[0], coords[1], this.mc)) {
			System.out
					.println("Inserimento mainCharacter non riuscito alle coordinate: " + coords[0] + "," + coords[1]);
		}
		this.charCoords = coords;
		this.map.setMainAlive(true);
		System.out.println("Inserimento mainCharacter riuscito alle coordinate: " + coords[0] + "," + coords[1]);
		// blocco nemico
		for (int i = 0; i < this.map.getMaxEnemy(); i++) {
			enemy = new EnemyModel();
			coords = this.generateCoords();
			while (!this.map.setTileContent(coords[0], coords[1], enemy)) {
				System.out.println(
						"Inserimento Enemy " + i + " non riuscito alle coordinate: " + coords[0] + "," + coords[1]);
				coords = this.generateCoords();
			}
			this.enemiesCoords.add(coords);
			System.out.println("Inserimento Enemy " + i + " riuscito alle coordinate: " + coords[0] + "," + coords[1]);
		} System.out.println(this.enemiesCoords.toString());

		// blocco tesoro
		for (int i = 0; i < this.map.getMaxTeasure(); i++) {
			chest = new ChestModel((AttrEnum.getById((int) (Math.random() * 4) + 1)));
			coords = this.generateCoords();
			while (!this.map.setTileContent(coords[0], coords[1], chest)) {
				System.out.println(
						"Inserimento Teasure " + i + " non riuscito alle coordinate: " + coords[0] + "," + coords[1]);
				coords = this.generateCoords();
			}
			System.out
					.println("Inserimento Teasure " + i + " riuscito alle coordinate: " + coords[0] + "," + coords[1]);
		}
		System.out.println(this.map);
	}

	private int[] generateCoords() {
		int[] result = new int[2];
		result[0] = (int) (Math.random() * this.map.getWidth());
		result[1] = (int) (Math.random() * this.map.getHeight());
		return result;
	}

	@Scheduled(fixedRate = 20000, initialDelay = 10000)
	private void simulate() {
		int turn = -1;
		int newX = 0, newY = 0;
		try {
			while (turn == -1) {
				newX = this.charCoords[0] + ((int) (Math.random() * 3)) - 1;
				newY = this.charCoords[1] + ((int) (Math.random() * 3)) - 1;
				turn = this.map.moveMainChar(this.charCoords[0], this.charCoords[1], newX, newY);
			}
			System.out.println("Mossa Main Char: " + newX + " " + newY + "\n" + this.map);
			if (this.map.isEnemySlayed()) {
				this.map.setEnemySlayed(false);
				for (int[] a : this.enemiesCoords) {
					if (a[0] == newX && a[1] == newY) {
						System.out.println("Nemico rimosso dall'array");
						this.enemiesCoords.remove(a);
					}
				}
				System.out.println("Nemico ucciso. " + "\n" + this.map);
				if (this.enemiesCoords.size() == 0) {
					System.out.println(this.map);
					System.exit(0);
				}
			}
			if (turn == 0) {
				System.out.println("Mi muovo: " + newX + " " + newY);
				this.charCoords[0] = newX;
				this.charCoords[1] = newY;
			}
			if (turn == 2) {
				System.out.println("Attacco");
			}
			turn = -1;
			for (int i = 0; i < this.enemiesCoords.size(); i++) {
				System.out.println("Mossa del nemico " + i );
				while (turn == -1) {
					newX = this.enemiesCoords.get(i)[0] + ((int) (Math.random() * 3)) - 1;
					newY = this.enemiesCoords.get(i)[1] + ((int) (Math.random() * 3)) - 1;
					System.out.println(this.enemiesCoords.get(i)[0]);
					System.out.println(this.enemiesCoords.get(i)[1]);
					turn = this.map.moveEnemy(this.enemiesCoords.get(i)[0], this.enemiesCoords.get(i)[1], newX, newY);
				}
				if (!this.map.isMainAlive()) {
					System.out.println(this.map);
					System.exit(0);
				}
				if (turn == 0) {
					this.enemiesCoords.get(i)[0] = newX;
					this.enemiesCoords.get(i)[1] = newY;
					System.out.println(this.enemiesCoords.get(i)[0]);
					System.out.println(this.enemiesCoords.get(i)[1]);
					System.out.println("Nemico si muove: " + newX + " " + newY);
				}
				if (turn == 2) {
					System.out.println("Il nemico attacca: " + newX + " " + newY);
				}
				turn = -1;
			}
			System.out.println(this.mc.toJSON());
			System.out.println("Main: " + this.charCoords[0] + " " + this.charCoords[1]);
			System.out.println("Main Life: " + this.mc.getVit());
			System.out.println(this.map);
		} catch (IndexOutOfBoundsException e) {
			return;
		}
	}

}
