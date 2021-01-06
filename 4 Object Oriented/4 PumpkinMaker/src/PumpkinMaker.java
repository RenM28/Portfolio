import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class draws orange pumpkins on a panel. Users can specify what shape they want
 * its features to be, as well as the location and dimensions of its features.
 * @author gerne
 *
 */
class PumpkinPanel extends JPanel {
	// Following are used for dimensions of pumpkin
	private int pLeft;
	private int pTop;
	private int pWidth;
	private int pHeight;
	// Following are used to track user choice of shapes for each feature
	private int eyeShape;
	private int noseShape;
	private int mouthShape;
	
	// Following functions allow access to each of the variables initialized above
	public int getPLeft() {
		return pLeft;
	}
	public int getPTop() {
		return pTop;
	}
	public int getPWidth() {
		return pWidth;
	}
	public int getPHeight() {
		return pHeight;
	}
	public int getEyeShape() {
		return eyeShape;
	}
	public int getNoseShape() {
		return noseShape;
	}
	public int getMouthShape() {
		return mouthShape;
	}
	
	/**
	 * This function sets the left starting point of the pumpkin.
	 * @param lft The starting point as an integer
	 */
	public void setPLeft(int lft) {
		if (lft < 0) {
			pLeft = 0;
		} else {
			pLeft = lft;
		}
	}
	/**
	 * This function sets the top starting point of the pumpkin.
	 * @param tp The starting point as an integer
	 */
	public void setPTop(int tp) {
		if (tp < 0) {
			pTop = 0;
		} else {
			pTop = tp;
		}
	}
	/**
	 * This function sets the width of the pumpkin.
	 * @param wdth The width as an integer
	 */
	public void setPWidth(int wdth) {
		if (wdth < 50) {
			pWidth = 50;
		} else {
			pWidth = wdth;
		}
	}
	/**
	 * This function sets the height of the pumpkin.
	 * @param hght The height as an integer
	 */
	public void setPHeight(int hght) {
		if (hght < 50) {
			pHeight = 50;
		} else {
			pHeight = hght;
		}
	}
	/**
	 * This function takes in the eye shape entered as a String and assigns an integer value. 
	 * If any letter is chosen besides C, S, or T, the user is given an error message.
	 * @param choice The choice as a String
	 */
	public void setEyeShape(String choice) {
		if (choice.equalsIgnoreCase("C")) {
			eyeShape = 0;
		} else if (choice.equalsIgnoreCase("S")) {
			eyeShape = 1;
		} else if (choice.equalsIgnoreCase("T")) {
			eyeShape = 2;
		} else {
			JOptionPane.showMessageDialog(null, "Please choose a valid option for the eyes.");
		}
	}
	/**
	 * This function takes in the nose shape entered as a String and assigns an integer value. 
	 * If any letter is chosen besides C, S, or T, the user is given an error message.
	 * @param choice The choice as a String
	 */
	public void setNoseShape(String choice) {
		if (choice.equalsIgnoreCase("C")) {
			noseShape = 0;
		} else if (choice.equalsIgnoreCase("S")) {
			noseShape = 1;
		} else if (choice.equalsIgnoreCase("T")) {
			noseShape = 2;
		} else {
			JOptionPane.showMessageDialog(null, "Please choose a valid option for the nose.");
		}
	}
	/**
	 * This function takes in the mouth shape entered as a String and assigns an integer value. 
	 * If any letter is chosen besides O or R, the user is given an error message.
	 * @param choice The choice as a String
	 */
	public void setMouthShape(String choice) {
		if (choice.equalsIgnoreCase("O")) {
			mouthShape = 0;
		} else if (choice.equalsIgnoreCase("R")) {
			mouthShape = 1;
		} else {
			JOptionPane.showMessageDialog(null, "Please choose a valid option for the mouth.");
		}
	}
	
	/**
	 * Default constructor that sets up default PumpkinPanel look.
	 */
	public PumpkinPanel() {
		setPLeft(200);
		setPTop(100);
		setPWidth(100);
		setPHeight(100);
		setEyeShape("C");
		setNoseShape("S");
		setMouthShape("O");
	}
	/**
	 * Non-default constructor that takes in left starting location, top starting location,
	 * width, height, eye shape, nose shape, and mouth shape to construct a PumpkinPanel.
	 * @param lft The left starting point of the pumpkin as an integer
	 * @param tp The top starting point of the pumpkin as an integer
	 * @param wdth The width of the pumpkin as an integer
	 * @param hght The height of the pumpkin as an integer
	 * @param eye The shape of the eyes as a String
	 * @param nose The shape of the nose as a String
	 * @param mouth The shape of the mouth as a String
	 */
	public PumpkinPanel(int lft, int tp, int wdth, int hght, String eye, String nose, String mouth) {
		setPLeft(lft);
		setPTop(tp);
		setPWidth(wdth);
		setPHeight(hght);
		setEyeShape(eye);
		setNoseShape(nose);
		setMouthShape(mouth);
	}
	
	/**
	 * This function sets the left starting point for the pumpkin's features.
	 * @param dec The amount width needs to be multiplied by for feature to start in correct location as a double
	 * @return The left starting point of the feature as an integer
	 */
	public int setFeatLeft(double dec) {
		return pLeft + (int)(pWidth*(dec));
	}
	/**
	 * This function sets the top starting point for the pumpkin's features.
	 * @param dec The amount height needs to be multiplied by for feature to start in correct location as a double
	 * @return The top starting point of the feature as an integer
	 */
	public int setFeatTop(double dec) {
		return (int)(pTop + (pHeight * dec));
	}
	/**
	 * This function sets the width for the pumpkin's features.
	 * @param dec The proportion of the pumpkin's width needed to set feature's width as a double
	 * @return The width of the feature as an integer
	 */
	public int setFeatWidth(double dec) {
		return (int)(dec*pWidth);
	}
	/**
	 * This function sets the height for the pumpkin's features.
	 * @return The height of the feature as an integer
	 */
	public int setFeatHeight() {
		return (int)((0.125)*pHeight);
	}
	
	/**
	 * This function draws the pumpkin and its features based on values of variables entered.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.ORANGE);
		g.fillOval(pLeft, pTop, pWidth, pHeight); // draws pumpkin
		g.setColor(Color.WHITE); // sets color to white for features
		// draws stem
		g.fillRect(setFeatLeft(0.4375), setFeatTop(-0.125), setFeatWidth(0.125), setFeatHeight());
		if (eyeShape == 0) { // draws oval eyes
			g.fillOval(setFeatLeft(0.25), setFeatTop(0.25), setFeatWidth(0.125), setFeatHeight());
			g.fillOval(setFeatLeft(0.625), setFeatTop(0.25), setFeatWidth(0.125), setFeatHeight());
		} else if (eyeShape == 1) { // draws rectangular eyes
			g.fillRect(setFeatLeft(0.25), setFeatTop(0.25), setFeatWidth(0.125), setFeatHeight());
			g.fillRect(setFeatLeft(0.625), setFeatTop(0.25), setFeatWidth(0.125), setFeatHeight());
		} else { // draws triangular eyes
			int xsEye1[] = new int[] {setFeatLeft(0.25), setFeatLeft(0.3125), setFeatLeft(0.375)};
			int ysEye1[] = new int[] {setFeatTop(0.375), setFeatTop(0.25), setFeatTop(0.375)};
			g.fillPolygon(xsEye1,ysEye1,3);
			int xsEye2[] = new int[] {setFeatLeft(0.625), setFeatLeft(0.6875), setFeatLeft(0.75)};
			int ysEye2[] = new int[] {setFeatTop(0.375), setFeatTop(0.25), setFeatTop(0.375)};
			g.fillPolygon(xsEye2,ysEye2,3);
			
		}
		if (noseShape == 0) { // draws oval nose
			g.fillOval(setFeatLeft(0.4375), setFeatTop(0.4375), setFeatWidth(0.125), setFeatHeight());
		} else if (noseShape == 1) { // draws rectangular nose
			g.fillRect(setFeatLeft(0.4375), setFeatTop(0.4375), setFeatWidth(0.125), setFeatHeight());
		} else { // draws triangular nose
			int xs[] = new int[] {setFeatLeft(0.4375), setFeatLeft(0.5), setFeatLeft(0.5625)};
			int ys[] = new int[] {setFeatTop(0.5625), setFeatTop(0.4375), setFeatTop(0.5625)};
			g.fillPolygon(xs,ys,3);
		}
		if (mouthShape == 0) { // draws oval mouth
			g.fillOval(setFeatLeft(0.25), setFeatTop(0.625), setFeatWidth(0.5), setFeatHeight());
		} else { // draws rectangular mouth
			g.fillRect(setFeatLeft(0.25), setFeatTop(0.625), setFeatWidth(0.5), setFeatHeight());
		} 
		
		
	}
}


/**
 * This class sets up the frame the PumpkinPanel is displayed on and updates it based
 * on user entries in each text box.
 * @author gerne
 *
 */
class PumpkinPrinterFrame extends JFrame {
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
	 * This function sets up the look of the panel, as well as updates the features, location, 
	 * and dimensions of the pumpkin when the Draw button is clicked.
	 */
	public void setUpLook() {
		centerFrame(800,480); // centers frame on screen
		setTitle("Pumpkin Maker"); // sets title
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		PumpkinPanel panCenter = new PumpkinPanel(); // creates new PumpkinPanel
		c.add(panCenter, BorderLayout.CENTER); // adds center panel to screen
		JPanel panSouth = new JPanel(); 
		panSouth.setLayout(new FlowLayout());
		// labels and text boxes will all appear in bottom ribbon on panel
		JLabel lblLeft = new JLabel("Left: ");
		JTextField txtLeft = new JTextField(3);
		JLabel lblTop = new JLabel("Top: ");
		JTextField txtTop = new JTextField(3);
		JLabel lblWidth = new JLabel("Width: ");
		JTextField txtWidth = new JTextField(3);
		JLabel lblHeight = new JLabel("Height: ");
		JTextField txtHeight = new JTextField(3);
		JLabel lblEye = new JLabel("Eye (C S T): ");
		JTextField txtEye = new JTextField(1);
		JLabel lblNose = new JLabel("Nose (C S T): ");
		JTextField txtNose = new JTextField(1);
		JLabel lblMouth = new JLabel("Mouth (O R): ");
		JTextField txtMouth = new JTextField(1);
		// adds all labels and text boxes to south panel
		panSouth.add(lblLeft);
		panSouth.add(txtLeft);
		panSouth.add(lblTop);
		panSouth.add(txtTop);
		panSouth.add(lblWidth);
		panSouth.add(txtWidth);
		panSouth.add(lblHeight);
		panSouth.add(txtHeight);
		panSouth.add(lblEye);
		panSouth.add(txtEye);
		panSouth.add(lblNose);
		panSouth.add(txtNose);
		panSouth.add(lblMouth);
		panSouth.add(txtMouth);
		// creates Draw button
		JButton btnDraw = new JButton("Draw");
		btnDraw.addActionListener(new ActionListener() {
			/**
			 * This function updates the features, location, and dimensions of the pumpkin at 
			 * the click of the button.
			 */
			public void actionPerformed(ActionEvent e) {
				// gets eye, nose, and mouth shape from text boxes 
				String eye = txtEye.getText();
				String nose = txtNose.getText();
				String mouth = txtMouth.getText();
				// sets said shape to center panel
				panCenter.setEyeShape(eye);
				panCenter.setMouthShape(mouth);
				panCenter.setNoseShape(nose);
				try {
					// if dimensions/location values are integers, they are retrieved from text boxes
					int pLeft = Integer.parseInt(txtLeft.getText());
					int pTop = Integer.parseInt(txtTop.getText());
					int pWidth = Integer.parseInt(txtWidth.getText());
					int pHeight = Integer.parseInt(txtHeight.getText());
					// dimensions/location values are set in center panel
					panCenter.setPLeft(pLeft);
					panCenter.setPTop(pTop);
					panCenter.setPWidth(pWidth);
					panCenter.setPHeight(pHeight);
				} catch (Exception ex) {
					// error message shown if integer values not entered
					JOptionPane.showMessageDialog(null, "Please enter integer values.");
				}
				repaint(); // updates look of panel
			}
		});
		panSouth.add(btnDraw); // adds button to south panel
		c.add(panSouth, BorderLayout.SOUTH);
	}
	
	/**
	 * Constructor that sets up look and close operation of PumpkinPrinterFrame
	 */
	public PumpkinPrinterFrame() {
		setUpLook();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
}

/**
 * This class creates a new PumpkinPrinterFrame that will show the user a frame displaying a pumpkin.
 * The labels and text boxes on the panel allow the user to update the dimensions, location, 
 * and features of the pumpkin as desired. The new look will be shown when the draw button
 * is clicked.
 * @author gerne
 *
 */
public class PumpkinMaker {
	public static void main(String[] args) {
		PumpkinPrinterFrame frm = new PumpkinPrinterFrame();
		frm.setVisible(true);
	}
}
