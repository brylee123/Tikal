package code;

/*
 * This class defines the underlying Terrain pieces of the game.
 * The piece can either be empty or not empty defined by it's state.
 * Methods called on the TP are deferred to the methods defined by the state.
 */

public class TerrainPiece {
	public int ID;

	private AState _state;
	private int _stateBoo;

	public TerrainPiece(int i) {
		ID = i;
		_stateBoo = 0;
		_state = new Empty();
	

	}
	
	//Method which determines whether or not the TP is empty
	public boolean isEmpty(){
		if(_stateBoo == 0){
			return true;
		}
		else{
			return false;
		}
	}

	//The following methods defer to the state as defined in the inner class
	public void setExpo(int player) {
		_state.setExpo(player);
	}

	public boolean removeExpo(int player) {
		return _state.removeExpo(player);
	}

	public void setTile() {
		_state = new NotEmpty();
	}

	public int getPyramid() {
		return _state.getPyramid();
	}

	public void setPyramid() {
		_state.setPyramid();
	}
	
	public int[] getExpoCount(){
		return _state.getExpoCount();
	}

	//This class allows for interacting with the game
	private abstract class AState {
		protected int _pyramid;

		public AState() {
			_pyramid = 0;
		}

		//This method sets an explorer on a nonEmpty TP
		public abstract void setExpo(int player);

		//This method removes an explorer from a nonEmpty TP
		public abstract boolean removeExpo(int player);
		
		//This method gets the amount of explorers on for all player on a specific TP
		public abstract int[] getExpoCount();

		//This method places a pyramid on a TP
		public abstract void setPyramid();

		//Accesor method for pyramids on a TP
		public abstract int getPyramid();
	}

	private class NotEmpty extends AState {

		private int[] _explorerCount;

		public NotEmpty() {
			_explorerCount = new int[Main.getPlayerList().size()];
			_stateBoo = 1;
			// this holds the number of explorers, player ID is based on
			// position in array
		}

		public void setExpo(int player) {
			// Increments the number of explorers on the tile
			_explorerCount[player] += 1;
			System.out.println("An explorer was placed in tile number " + ID);

		}

		public boolean removeExpo(int playerID) {

			if (_explorerCount[playerID] > 0) {
				_explorerCount[playerID] -= 1;
				return true;
			} else {
				System.out.println("There are no explorers here to move");
				return false;

			}

			// System.out.println("Removed an explorer from " + ID);
			// System.out.println("The total number of explorers here are "
			// + _expoNum);

		}

		@Override
		public void setPyramid() {
			System.out.println("boo");
			_pyramid++;

		}

		@Override
		public int getPyramid() {
			return _pyramid;

		}

		@Override
		public int[] getExpoCount() {
			return _explorerCount;
		}

	}

	private class Empty extends AState {

		@Override
		public void setExpo(int player) {
			System.out
					.println("You may not place an explorer on an empty tile");

		}

		@Override
		public boolean removeExpo(int player) {
			System.out.println("There are no explorers here to move");
			return false;
		}

		@Override
		public void setPyramid() {
			System.out.println("boo");
			_pyramid++;
		}

		@Override
		public int getPyramid() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int[] getExpoCount() {
			return new int[Main.getPlayerList().size()];
			
		}

	}
}
