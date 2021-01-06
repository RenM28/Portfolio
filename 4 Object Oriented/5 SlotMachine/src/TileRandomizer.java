import java.util.ArrayList;
import java.util.Random;

/**
 * This class randomizes every tile on screen.
 * @author gerne
 *
 */
public class TileRandomizer {
	/**
	 * This function provides the primary function of the class
	 * by randomizing each tile.
	 * @param tiles Array list of tile objects
	 * @param rand Random object to select random numbers
	 */
	public void randomizeTiles(ArrayList<Tile> tiles, Random rand) {
		Tile tile; // sets up individual tile
		for (int i = 0; i < 4; i ++) { // for each tile
			tile = tiles.get(i);
			tile.setRandomly(rand);
		}
	}
}
