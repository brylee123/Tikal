package code;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.ImageIcon;

/* This class defines the tiles that are loaded by the .loadBaseImages() method in the guiTest.java
 * class. Each tile has a randomly created image and an integer array of data that tells what segments
 * the tile has.*/
public class Tile {
	private int[] _data;
	private HashMap<String, BufferedImage> _imBase;
	private BufferedImage _im;
	private boolean _allowRotation;
	private TerrainPiece _TP;
	
	public Tile(HashMap<String, BufferedImage> imBase, int ID) {
	//	_TP = Main.getBoardPieces().get(ID); this will give each tile a reference to its terrainpiece, uncomment when resizing works
		
		
		
		_allowRotation = false;
		_imBase = imBase;
		_data = new int[6];
		BufferedImage im = _imBase.get("empty");
		BufferedImage im2 = new BufferedImage(im.getHeight(),im.getWidth(),im.getType());
		Graphics2D graphics = (Graphics2D) im2.getGraphics();
		graphics.drawImage(im, 0, 0, im.getWidth(), im.getHeight(), null);
		for(int i = GUI.randInt(3,1); i>0; i--){  //pick random number of paths
			int index = GUI.randInt(5,0);	//pick random spot to put path
			while(_data[index] != 0){
				index = GUI.randInt(5,0);	//don't overwrite previous paths
			}
			_data[index] = GUI.randInt(2, 1);	//pick either 1 or 2 segments
			graphics.rotate(Math.PI*(index-index%3)/3, im2.getWidth()/2, im2.getHeight()/2);
			if(index == 0 ||index == 3){
				graphics.drawImage(_imBase.get(""+_data[index]), 0, 0, im.getWidth(), im.getHeight(), null);
			}
			if(index == 1 ||index == 4){
				graphics.drawImage(_imBase.get(_data[index]+"b"), 0, 0, im.getWidth(), im.getHeight(), null);
			}
			if(index == 2 ||index == 5){
				graphics.drawImage(_imBase.get(3*_data[index]+"b"), 0, 0, im.getWidth(), im.getHeight(), null);
			}
			graphics.rotate(-1*Math.PI*(index-index%3)/3, im2.getWidth()/2, im2.getHeight()/2);
		}
		_im = im2;
	}
	
	/* This is an accessor method for the _data instance variable.*/
	public int[] getData() {
		return _data;
	}
	
	/* This is a mutator method for the _allowRotation instance variable.*/
	public void setAllowRotation(boolean allowRotation) {
		_allowRotation = allowRotation;
	}
	
	/* This is an accessor method for the _im instance variable (though it returns
	 * an ImageIcon rather than a BufferedImage).*/
	public ImageIcon getIcon(){
		return new ImageIcon(_im);
	}
	
	/* This is an accessor method for the _TP instance variable.*/
	public TerrainPiece getPiece(){
		return _TP;
	}
	
	/* This method rotates a Tile 180 degrees and changes its data to reflect
	 * its new orientation.*/
	public void rotate(){
		if(!_allowRotation){
			return;
		}
		_im = GUI.rotate(_im, 2);
		int temp;
		for(int i=0; i<3; i++){
			temp = _data[i];
			_data[i] = _data[i+3];
			_data[i+3] = temp;
		}
	}
}
