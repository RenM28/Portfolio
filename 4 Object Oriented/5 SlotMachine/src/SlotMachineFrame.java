import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/** 
 * This class sets up the look of the frame.
 * It also sets up the file menu and provides its execution through
 * action listeners.
 * @author gerne
 *
 */
public class SlotMachineFrame extends JFrame{
	// panel variable that displays tiles
	private TilePanel pan;
	// variable that scans tiles for matching shapes + colors
	private TileChecker tc;
	// variable that randomizes all tiles on panel
	private TileRandomizer tr;
	
	// random variable
	private Random rand;
	
	// sets up buttons
	private JButton btnMax;
	private JButton btnMid;
	private JButton btnMin;
	
	// sets up balance text field and double
	private JTextField txtBalance;
	private double balance = 5.00;
	
	/**
	 * This function allows the frame to be centered on the screen.
	 * @param width The width of the frame as an integer
	 * @param height The height of the frame as an integer
	 */
	public void centerFrame(int width, int height) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenDims = tk.getScreenSize();
		int screenWidth = (int)screenDims.getWidth();
		int screenHeight = (int)screenDims.getHeight();
		int left = (screenWidth - width)/2;
		int top = (screenHeight - height)/2;
		setBounds(left, top, width, height);
	}
	
	/**
	 * This function sets up the file menu.
	 */
	public void setupMenu() {
		JMenuBar mbar = new JMenuBar();
		JMenu mnuFile = new JMenu("File");
		mbar.add(mnuFile);
		 
		// If load tiles is clicked, extension will be checked and
		// respective function for reading that extension will 
		// be called
		JMenuItem miLoadTiles = new JMenuItem("Load Tiles");
		miLoadTiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				TileReader tr;
				ArrayList<Tile> tilesRead;
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					tr = new TileReader();
					tilesRead = tr.read(jfc.getSelectedFile());
					if (tilesRead == null) {
						JOptionPane.showMessageDialog(null, "Could not read tiles.");
					} else {
						pan.setTiles(tilesRead);
						repaint();
					}
				}
			}
		});
		mnuFile.add(miLoadTiles);
		
		// If save tiles is clicked, extension will be checked and
		// respective function for writing that file extension will
		// be called
		JMenuItem miSaveTiles = new JMenuItem("Save Tiles");
		miSaveTiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				TileWriter tw;
				if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					tw = new TileWriter();
					if (tw.write(jfc.getSelectedFile(), pan.getTiles())) {
						JOptionPane.showMessageDialog(null, "Tiles were written :)");
					} else {
						JOptionPane.showMessageDialog(null, "Tiles could not be written :(");
					}
				}
				
			}
		});
		mnuFile.add(miSaveTiles);
		
		// If print is clicked, the numbers corresponding to the color and shape type
		// will be printed to the console line for each tile
		JMenuItem miPrint = new JMenuItem("Print");
		miPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Tile> tiles = new ArrayList<Tile>();
				tiles = pan.getTiles();
				for (Tile tile: tiles) {
					System.out.println(tile);
				}
			}
		});
		mnuFile.add(miPrint);
		
		// If restart is clicked, the user's balance will be reset to 5
		// If the buttons have been disabled, they will be re-enabled
		JMenuItem miRestart = new JMenuItem("Restart");
		miRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//reset balance
				balance = 5.00;
				txtBalance.setText(String.format("%.2f", balance));
				// reset buttons
				btnMax.setEnabled(true);
				btnMid.setEnabled(true);
				btnMin.setEnabled(true);
			}
		});
		mnuFile.add(miRestart);
		
		// If exit is clicked, the program will close
		JMenuItem miExit = new JMenuItem("Exit");
		miExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnuFile.add(miExit);
		
		// If help is clicked, user will see name of author and github link
		JMenu mnuHelp = new JMenu("Help");
		mbar.add(mnuHelp);
		JMenuItem miAbout = new JMenuItem("About");
		miAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Author: Ren Gernes, URL: https://github.com/RenM28/Gernes_Lauren_cpsc24500_");
			}
		});
		mnuHelp.add(miAbout);
		setJMenuBar(mbar);
		
	}
	
	/**
	 * This function sets up the look of the frame.
	 */
	public void setupLook() {
		centerFrame(800,300); // centers frame
		setTitle("Las Vegas Slot Machine");
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		pan = new TilePanel(); // sets up tile panel
		c.add(pan, BorderLayout.CENTER);
		// sets up bottom bar with max, mid, and min buttons
		JPanel panSouth = new JPanel();
		panSouth.setLayout(new FlowLayout());
		btnMax = new JButton("Max");
		panSouth.add(btnMax);
		btnMid = new JButton("Mid");
		panSouth.add(btnMid);
		btnMin = new JButton("Min");
		panSouth.add(btnMin);
		panSouth.add(new JLabel("$"));
		txtBalance = new JTextField(6);
		txtBalance.setEditable(false); // prevents user from cheating
		txtBalance.setText(String.format("%.2f", balance)); // sets initial balance
		panSouth.add(txtBalance);
		c.add(panSouth, BorderLayout.SOUTH);
		
		// sets up balance
		balance = Double.parseDouble(txtBalance.getText());
		
		// if max is clicked, all the user's current balance is bet
		// if there is no match, the balance drops to zero
		// if the shapes match, the balance increases by 25 times 
		// if both shapes and colors match, the balance increases by 100 times
		btnMax.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tr.randomizeTiles(pan.getTiles(), rand);
				repaint();
				if (tc.checkTiles(pan.getTiles()) == 0) { // no match
					txtBalance.setText(String.format("%.2f", 0.00));
					btnMax.setEnabled(false); // disable buttons
					btnMid.setEnabled(false);
					btnMin.setEnabled(false);
				} else if (tc.checkTiles(pan.getTiles()) == 1) { // colors match
					balance = balance*25;
					txtBalance.setText(String.format("%.2f", balance));
				} else { // colors and shapes match
					balance = balance*100;
					txtBalance.setText(String.format("%.2f", balance));
				}
				
			}
		});
		
		// if mid is clicked, half the user's balance is bet
		// if there is no match, the balance drops by half
		// if the shapes match, the balance increases by 10 times 
		// if both shapes and colors match, the balance increases by 50 times
		btnMid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tr.randomizeTiles(pan.getTiles(), rand);
				repaint();
				if (tc.checkTiles(pan.getTiles()) == 0) { // no match
					balance -= 0.5*balance;
					txtBalance.setText(String.format("%.2f", balance));
					if (Double.parseDouble(txtBalance.getText()) == 0.00) { // disable buttons
						btnMax.setEnabled(false);
						btnMid.setEnabled(false);
						btnMin.setEnabled(false);
					}
				} else if (tc.checkTiles(pan.getTiles()) == 1) { // colors match
					balance = balance*10;
					txtBalance.setText(String.format("%.2f", balance));
				} else { // colors and shapes match
					balance = balance*50;
					txtBalance.setText(String.format("%.2f", balance));
				}
				
			}
		});
		
		// if min is clicked, one tenth of the user's balance is bet
		// if there is no match, the balance drops by one tenth
		// if the shapes match, the balance increases by 5 times 
		// if both shapes and colors match, the balance increases by 10 times
		btnMin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tr.randomizeTiles(pan.getTiles(), rand);
				repaint();
				if (tc.checkTiles(pan.getTiles()) == 0) { // no match
					balance -= 0.1*balance;
					txtBalance.setText(String.format("%.2f", balance));
					if (Double.parseDouble(txtBalance.getText()) == 0.00) { // disable buttons
						btnMax.setEnabled(false);
						btnMid.setEnabled(false);
						btnMin.setEnabled(false);
					}
				} else if (tc.checkTiles(pan.getTiles()) == 1) { // shapes match
					balance = balance*5;
					txtBalance.setText(String.format("%.2f", balance));
				} else { // colors and shapes match
					balance = balance*10;
					txtBalance.setText(String.format("%.2f", balance));
				}
				
			}
		});
		
		setupMenu(); // sets menu
		
		// randomizes tiles at start of game
		tr.randomizeTiles(pan.getTiles(), rand);
		repaint();
	}
	
	/**
	 * This constructor sets up the look of the frame, as well as the
	 * exit operation.
	 */
	public SlotMachineFrame() {
		tr = new TileRandomizer(); 
		tc = new TileChecker();
		rand = new Random();
		setupLook();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
