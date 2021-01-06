import java.util.ArrayList;

/**
 * This class compares the colors and shapes of each tile to the first tile.
 * @author gerne
 *
 */
public class TileChecker {
	/**
	 * This function provides the primary function of the class.
	 * If there is no match, returns 0.
	 * If just the colors of the shapes match, returns 1.
	 * If all tiles match (shapes and colors), returns 2.
	 * @param tiles Array list of tile objects
	 * @return 0, 1, or 2 to indicate the type of match
	 */
	public int checkTiles(ArrayList<Tile> tiles) {
		// 0 - no match :(
		// 1 - just colors match
		// 2 - shape and color match
		int matchType = 2; // assume all shapes and colors match
		for (int i = 1; i < tiles.size(); i ++) { // for each tile
			 if (tiles.get(0).getShapeColor() != tiles.get(i).getShapeColor()) { // if colors don't match
				 matchType = 0; // no match
				 break; // stop loop
			 } else { // if colors match
				 if (tiles.get(0).getShapeType() != tiles.get(i).getShapeType()) { // if shapes don't match
					 matchType = 1; // only colors match
				 }
			 }
		}
		return matchType;
	}
}
