import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

/**
 * This class sets up an array of 4 Tile instances as a panel.
 * Each tile can be randomly changed when clicked.
 * @author gerne
 *
 */
public class TilePanel extends JPanel implements MouseListener {
	// sets up array of tiles
	private ArrayList<Tile> tiles;
	private TileRandomizer tr;
	// tile randomizer
	private Random rand;
	
	// allows access to private array of tiles
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	/**
	 * This function sets up the array of tiles.
	 * @param tiles Tile array
	 */
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	/**
	 * This constructor sets up an initial tile panel with 4 tiles
	 * of random shape and color.
	 */
	public TilePanel() {
		addMouseListener(this);
		tiles = new ArrayList<Tile>(); // sets up tile array
		Tile tile; // sets up individual tile
		rand = new Random(); // creates new random instance
		// creates 4 randomly set tiles at start of program
		for (int i = 0; i < 4; i ++) {
			tile = new Tile();
			//tile.setShapeColor(0);
			//tile.setShapeType(rand.nextInt(2));
			tile.setRandomly(rand);
			tiles.add(tile);
		}
	}
	
	/**
	 * This function overrides the paint component so that tiles will be 
	 * drawn based on their shape and color.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int cellWidth = this.getWidth()/4; // divides panel width by 4
		int tileDim = 4*cellWidth/5; // sets up tile height + width
		Tile tile; // sets up individual tile
		
		for (int i = 0; i < tiles.size(); i ++) { // for each tile
			tile = tiles.get(i); // gets tile
			
			if (tile.getShapeColor() == 0) { // yellow
				g.setColor(Color.YELLOW);
			} else if (tile.getShapeColor() == 1) { // green
				g.setColor(Color.GREEN);
			} else if (tile.getShapeColor() == 2) { // orange
				g.setColor(Color.ORANGE);
			} else if (tile.getShapeColor() == 3) { // red
				g.setColor(Color.RED);
			} else { // blue
				g.setColor(Color.BLUE);
			}
			
			if (tile.getShapeType() == 0) { // circle
				g.fillOval(i*cellWidth+cellWidth/10, cellWidth/10, tileDim, tileDim);
			} else { // square
				g.fillRect(i*cellWidth+cellWidth/10, cellWidth/10, tileDim, tileDim);
			}
		}
	}
	
	// Mouse commands 
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
	/**
	 * When the mouse is clicked within the range of a certain tile, 
	 * its shape and color will be changed randomly.
	 */
	public void mouseClicked(MouseEvent e) { 
		// determines which tile user selects
		int tileSelected = e.getX()/(this.getWidth()/4);
		// gets tile from array
		Tile tile = tiles.get(tileSelected);
		// sets tile randomly
		tile.setRandomly(rand);
		repaint();
	}
}
