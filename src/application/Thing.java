
package application;

import javafx.scene.control.Button;


/**
 * @author Zhaoyuan(Sarah) Zhang
 *
 */
public class Thing extends Button{


	private String name;
	private int x;
	private int y;
	private int energy;
	private int id;
	private static int idSquence = 1;

	public Thing(String name, int x, int y, int energy) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
		this.energy = energy;
		this.id = idSquence++;
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	@Override
	public String toString() {
		return name + ": ID-> " + id + ", Energy-> " + energy +", Location: ("+ x +", " + y + ")";
	}
}
