
package application;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.control.Button;

/**
 * This is a fundamental class. Most basic methods inside it.
 * All of things are also inside the world.
 * @author Zhaoyuan(Sarah) Zhang
 *
 */
public class World {

	private int width;
	private int height;

	ArrayList<Button> things = new ArrayList<Button>();
	ArrayList<Button> deadPlants = new ArrayList<Button>();
	Random random = new Random();

	public World(int width, int height) {
		super();
		this.width = width;
		this.height = height;
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

	public void addThing(Button button) {
		things.add(button);
	}
	/**
	 * random direction 
	 * @return a String represent direction
	 */
	public String randomDirection() {

		int rand = random.nextInt(4);
		String direction = null;
		if (rand == 0) {
			direction = "N";
		} else if (rand == 1) {
			direction = "S";
		} else if (rand == 2) {
			direction = "E";
		} else if (rand == 3) {
			direction = "W";
		}
		return direction;
	}
	/**
	 * bugs can move by random directions and moves 10 pixel each time.
	 * @param bug is the bug which moves
	 */
	public void move(Bug b) {// x-->col, y-->row

		String direction = this.randomDirection();

		int x = b.getX();// col
		int y = b.getY();// row
		/**if the bug arrives the edge of each direction and it still 
		randomly moves to that direction, use recursion to call move method
		until it moves to other directions.
		*/
		if ((y == 0 && direction.equalsIgnoreCase("N")) || 
			(y == height - 20 && direction.equalsIgnoreCase("S"))|| 
			(x == width - 20 && direction.equalsIgnoreCase("E"))||
			(x == 0 && direction.equalsIgnoreCase("W"))) {
			this.move(b);
//				return;
		}

		else if (direction.equalsIgnoreCase("N") && y > 10) {
			y -= 10;
			b.setY(y);
		} else if (direction.equalsIgnoreCase("S") && y < height-30) {
			y += 10;
			b.setY(y);
		} else if (direction.equalsIgnoreCase("E") && x < width-30) {
			x += 10;
			b.setX(x);
		} else if (direction.equalsIgnoreCase("W") && x > 10) {
			x -= 10;
			b.setX(x);
		}
	}
	//check if the bug collide with plant or not
	public boolean checkPlantCollision(Bug bug, Plant plant) {
		
		if(bug.getBoundsInParent().intersects(plant.getBoundsInParent())) {
			return true;
		}	
		return false;
	}
	
	//check if the bug collide with obstacle or not
	public boolean checkObstacleCollision(Bug bug, Obstacle obstacle) {
		
		if(bug.getBoundsInParent().intersects(obstacle.getBoundsInParent())) {
			return true;
		}				
		return false;
	}
	
	//check if the bug collide with other bugs or not
	public boolean checkBugsCollision(Bug bug1, Bug bug2) {
		
		if(bug1.getBoundsInParent().intersects(bug2.getBoundsInParent()) &&(bug1!= bug2)) {
			return true;
		}			
		return false;
	}
	/**
	 * if the bug touches the plant, its energy increases 1, and this plant's energy reduces 1. 
	 * @param bug 
	 */
	public void touchPlant(Bug bug) {
		
		Plant deadPlant = null;
		for(Button button: things) {
			if(button instanceof Plant && (checkPlantCollision(bug, (Plant)button))) {
				((Plant) button).setEnergy(((Plant) button).getEnergy() - 1);// plant energy -1
					bug.setEnergy(bug.getEnergy() + 1);// bug energy +1
					if (((Plant) button).getEnergy() <= 0) {
						deadPlant = (Plant) button;
						deadPlants.add(deadPlant);
						break;
					}
				}					
			}
	}
}
	