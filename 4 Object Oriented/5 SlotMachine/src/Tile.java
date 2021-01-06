import java.io.Serializable;
import java.util.Random;

/**
 * This class creates a tile instance that is defined by its shape
 * and color
 * @author gerne
 *
 */
public class Tile implements Serializable{
	// Following variables define color and shape type
	private int shapeColor;
	private int shapeType;
	
	// Following functions allow access to each private variable
	public int getShapeColor() {
		return shapeColor;
	}
	public int getShapeType() {
		return shapeType;
	}
	
	/**
	 * This function sets the color of the tile.
	 * @param color Color of tile as integer
	 */
	public void setShapeColor(int color) {
		shapeColor = color;
	}
	/**
	 * This function sets the shape of the tile.
	 * @param type Shape of tile as integer
	 */
	public void setShapeType(int type) {
		shapeType = type;
	}
	
	/**
	 * Default constructor that sets up a default panel.
	 */
	public Tile() {
		shapeColor = 0;
		shapeType = 0;
	}
	
	/**
	 * Non-default constructor that takes in color and shape type to
	 * create a tile.
	 * @param color The color of the tile as an integer
	 * @param type The shape of the tile as an integer
	 */
	public Tile(int color, int type) {
		setShapeColor(color);
		setShapeType(type);
	}
	
	/**
	 * This function chooses random integers between 0 and 1 and between
	 * 0 and 4 in order in order to randomly set the shape and color
	 * of the tile respectively.
	 */
	public void setRandomly(Random rand) {
		int shapeSelection = 2;
		int colorSelection = 5;
		shapeType = rand.nextInt(shapeSelection);
		shapeColor = rand.nextInt(colorSelection);
	}
	
	/**
	 * This function overrides the toString() method so that the tile
	 * will print out its color and type.
	 */
	@Override
	public String toString() {
		return String.format("%d %d", shapeColor, shapeType);
	}
	
}
