package code;


/*This class defines a player and hold various variables needed to play the game
 * 
 */
public class Player {

	private int _AP;
	private int _explorers;
	private int _score;
	private int _ID;
	private String _name;


	public Player(int ID, String name) {
		_explorers = 10;
		_AP = 12;
		_ID = ID;
		_name = name;

	}

	//returns the player's name
	public String getName() {
		return _name;
	}

	//places an explorer on a TerrainPiece and increments AP's
	public void placeExplorer(TerrainPiece t) {
		if (_AP > 1) {
			if (_explorers > 0) {
				t.setExpo(_ID);
				_explorers -= 1;
				_AP = _AP - 2;
			} else {
				System.out.println("You are out of explorers");
			}
		} else {
			System.out.println("You are out of Action Points");
		}

	}

	//Moves an explorer from one TP to the next
	public void moveExplorer(TerrainPiece orig, TerrainPiece next) {

		if (_AP > 0) {

			if (orig.removeExpo(_ID)) {
				next.setExpo(_ID);
				_AP -= 1;
			}

		} else {
			System.out.println("You are out of Action Points");
		}
	}

	//Places a tile on a TP and sets the TP to notEmpty state
	public void placeTile(TerrainPiece t) {
		if (_AP > 0) {
			t.setTile();
			_AP -= 6;
		} else {
			System.out.println("You are out of Action Points");
		}
	}

	//Places a pyramid on a nonempty TP
	public void placePyramid(TerrainPiece t) {
		if (_AP > 0) {
			t.setPyramid();
			_AP -= 3;
		}
		else{
			System.out.println("You are out of Action Points");
		}
	}

	//Method to help scoring
	public void incScore(int points) {
		_score += points;
	}
	
	//Method to help scoring
	public int getFinalScore() {
		return _score;
	}

	//Returns the current amount of AP's
	public int getAP() {
		return _AP;
	}
	
	//mutator method for APs
	public void resetAP(){
		_AP = 12;
	}

	//Numerical ID for the player
	public int getID() {
		return _ID;
	}

}
