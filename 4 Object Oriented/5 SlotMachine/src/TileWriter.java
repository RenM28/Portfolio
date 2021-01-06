import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/** 
 * This class writes tiles to a binary, text, or xml file.
 * @author gerne
 *
 */
public class TileWriter {
	/**
	 * Writes tiles to a text file.
	 * @param fname Name of text file as string
	 * @param tiles Array of tiles to be written
	 * @return true if successfully written, false otherwise
	 */
	public boolean writeToText(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToText(f, tiles);
	}
	
	/**
	 * Write tiles to a text file.
	 * @param f File object to write to
	 * @param tiles Array of tiles to be written
	 * @return true if successfully written, false otherwise
	 */
	public boolean writeToText(File f, ArrayList<Tile> tiles) {
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f)));
			for (Tile tile: tiles) {
				pw.println(tile);
			}
			pw.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * Writes tiles to a binary file.
	 * @param fname Name of binary file as string
	 * @param tiles Array of tiles to be written
	 * @return true if successfully written, false otherwise
	 */
	public boolean writeToBinary(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToBinary(f, tiles);
	}
	/**
	 * Write tiles to a binary file.
	 * @param f File object to write to
	 * @param tiles Array of tiles to be written
	 * @return true if successfully written, false otherwise
	 */
	public boolean writeToBinary(File f, ArrayList<Tile> tiles) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(tiles);
			oos.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	/**
	 * Writes tiles to a xml file.
	 * @param fname Name of xml file as string
	 * @param tiles Array of tiles to be written
	 * @return true if successfully written, false otherwise
	 */
	public boolean writeToXML(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return writeToXML(f, tiles);
	}
	/**
	 * Write tiles to a xml file.
	 * @param f File object to write to
	 * @param tiles Array of tiles to be written
	 * @return true if successfully written, false otherwise
	 */
	public boolean writeToXML(File f, ArrayList<Tile> tiles) {
		try {
			XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
			enc.writeObject(tiles);
			enc.close();
			return true;
		} catch (Exception ex) {
			return false;
		}
	}
	
	public boolean write(String fname, ArrayList<Tile> tiles) {
		File f = new File(fname);
		return write(f, tiles);
	}
	
	/**
	 * Hub for all write functions that chooses which function to call
	 * based on file extension.
	 * .bin -- writeToBinary
	 * .txt -- writeToText
	 * .xml -- writeToXML
	 * @param f File object to write to
	 * @param tiles Array of tiles to be written
	 * @return
	 */
	public boolean write(File f, ArrayList<Tile> tiles) {
		try {
			String fname = f.getName().toUpperCase();
			if (fname.endsWith(".TXT")) {
				return writeToText(f, tiles);
			} else if (fname.endsWith(".BIN")) {
				return writeToBinary(f, tiles);
			} else if (fname.endsWith(".XML")) {
				return writeToXML(f, tiles);
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
	}
}
