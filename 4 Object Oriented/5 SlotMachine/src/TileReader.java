import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unchecked")

/**
 * This class reads a bin, text, or xml file of Tile instances.
 * @author gerne
 *
 */
public class TileReader {
	/**
	 * Reads tiles from a text file.
	 * @param fname Name of text file as string
	 * @return tile array if successfully written, null otherwise
	 */
	public ArrayList<Tile> readFromText(String fname) {
		File f = new File(fname);
		return readFromText(f);
	}
	
	/**
	 * Reads tiles from a text file.
	 * @param f File object to read from
	 * @return tile array if successfully written, null otherwise
	 */
	public ArrayList<Tile> readFromText(File f) {
		try {
			ArrayList<Tile> tilesRead = new ArrayList<Tile>();
			Scanner fsc = new Scanner(f);
			String line;
			String[] parts;
			int color, type;
			Tile tile;
			while (fsc.hasNextLine()) {
				line = fsc.nextLine();
				parts = line.split(" ");
				color = Integer.parseInt(parts[0]);
				type = Integer.parseInt(parts[1]);
				tile = new Tile(color, type);
				tilesRead.add(tile);
			}
			fsc.close();
			return tilesRead;
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * Reads tiles from a binary file.
	 * @param fname Name of binary file as string
	 * @return tile array if successfully written, null otherwise
	 */
	public ArrayList<Tile> readFromBinary(String fname) {
		File f = new File(fname);
		return readFromBinary(f);
	}
	
	/** 
	 * Reads tiles from a binary file.
	 * @param f File object to read from
	 * @return tile array if successfully written, null otherwise
	 */
	public ArrayList<Tile> readFromBinary(File f) {
		try {
			ArrayList<Tile> tilesRead;
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			tilesRead = (ArrayList<Tile>)ois.readObject();
			ois.close();
			return tilesRead;
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * Reads tiles from a xml file.
	 * @param fname Name of xml file as string
	 * @return tile array if successfully written, null otherwise
	 */
	public ArrayList<Tile> readFromXML(String fname) {
		File f = new File(fname);
		return readFromXML(f);
	}
	
	/**
	 * Reads tiles from a xml file.
	 * @param f File object to read from
	 * @return tile array if successfully written, null otherwise
	 */
	public ArrayList<Tile> readFromXML(File f) {
		try {
			ArrayList<Tile> tilesRead;
			XMLDecoder dec = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
			tilesRead = (ArrayList<Tile>)dec.readObject();
			dec.close();
			return tilesRead;
		} catch (Exception ex) {
			return null;
		}
	}
	
	public ArrayList<Tile> read(String fname) {
		File f = new File(fname);
		return read(f);
	}
	
	/**
	 * Hub for all read functions that chooses which function to call
	 * based on file extension.
	 * .txt -- readFromText
	 * .bin -- readFromBinary
	 * .xml -- readFromXML
	 * @param f File object to read from
	 * @return tile array if successfully written, null otherwise
	 */
	public ArrayList<Tile> read(File f) {
		try {
			String fname = f.getName().toUpperCase();
			if (fname.endsWith(".TXT")) {
				return readFromText(f);
			} else if (fname.endsWith(".BIN")) {
				return readFromBinary(f);
			} else if (fname.endsWith(".XML")) {
				return readFromXML(f);
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}
}
