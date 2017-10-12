package code;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/* This class defines a special JButton called a TButton (Tile Button). It has all the functionality of
 * a JButton, but can also hold data including the button's coordinates on the board, the board's bounds,
 * and more.*/
public class TButton extends JButton{
	private int[] _data;
	private int[] _coord;
	private int[] _bounds;
	private int _ID; //This variable matches the button to its specific TerrainPiece
	private ArrayList<ArrayList<TButton>> _buttonList;
	private Boolean _isNotEmpty;
	private Pyramid _pyramid;
	
	public TButton(ImageIcon image, int[] coord,int[] bounds,int ID, ArrayList<ArrayList<TButton>> buttonList) {
		super(image);
		_coord = coord;
		_bounds = bounds;
		_ID = ID;
		_buttonList = buttonList;
		_isNotEmpty = false;
		_pyramid = new Pyramid();
	}

	/* This is an accessor method for the _pyramid instance variable.*/
	public Pyramid getPyr(){
		return _pyramid;
	}
	
	/* This is an accessor method for the _data instance variable.*/
	public int[] get_data() {
		return _data;
	}
	
	/* This is an accessor method for the _ID instance variable.*/
	public int getID(){
		return _ID;
	}

	/* This is a mutator method for the _data instance variable.*/
	public void set_data(int[] data) {
		_data = data;
	}
	
	/* This is a mutator method for the _isNotEmpty instance variable.*/
	public void setIsNotEmpty(Boolean nonempty){
		_isNotEmpty = nonempty;
	}
	
	/* This is an accessor method for the _isNotEmpty instance variable.*/
	public Boolean getIsNotEmpty(){
		return _isNotEmpty;
	}
	
	/* This method returns whether or not a TButton is in the corner of the board.*/
	public boolean IsInACorner(){
		return (_coord[0]==0 ||_coord[0]==_bounds[0]-1+_coord[1]%2)&&(_coord[1]==0||_coord[1]==_bounds[1]-1);
	}
	
	private static final long serialVersionUID = 6462353313945223983L;
	
	/* This method returns whether or not a TButton has adjacent nonempty TButtons (i.e. a path).*/
	public boolean hasPath() {
		TButton[] adjacentList = this.getAdjacentButtons();
		for(int i=0;i<6;i++){
			if(adjacentList[i]!=null){
				if(adjacentList[i].getIsNotEmpty()){
					return true;
				}
			}
		}
		return false;
	}
	
	/* This method returns an array of the 6 TButtons adjacent to a given TButton. If there are not
	 * 6 adjacent TButtons, e.x. the button is in a corner, then the missing members of the array
	 * will be null.*/
	public TButton[] getAdjacentButtons(){
		TButton[] adjacentList = new TButton[6];
		if(_coord[0]==_bounds[0]-1+_coord[1]%2){
			adjacentList[0] = null;
		}
		else adjacentList[0] = _buttonList.get(_coord[1]).get(_coord[0]+1);
		
		if(_coord[1]==0||_coord[0]==_bounds[0]){
			adjacentList[1] = null;
		}
		else adjacentList[1] = _buttonList.get(_coord[1]-1).get(_coord[0]+(_coord[1]+1)%2);
		
		if(_coord[1]==0||_coord[0]==0&&_coord[1]%2==1){
			adjacentList[2] = null;
		}
		else adjacentList[2] = _buttonList.get(_coord[1]-1).get(_coord[0]-_coord[1]%2);
		
		if(_coord[0]==0){
			adjacentList[3] = null;
		}
		else adjacentList[3] = _buttonList.get(_coord[1]).get(_coord[0]-1);
		
		if(_coord[1]==_bounds[1]-1||(_coord[0]==0&&(_coord[1]%2==1))){
			adjacentList[4] = null;
		}
		else adjacentList[4] = _buttonList.get(_coord[1]+1).get(_coord[0]-_coord[1]%2);
		
		if(_coord[1]==_bounds[1]-1||_coord[0]==_bounds[0]){
			adjacentList[5] = null;
		}
		else adjacentList[5] = _buttonList.get(_coord[1]+1).get(_coord[0]+(_coord[1]+1)%2);
		return adjacentList;
	}
}
