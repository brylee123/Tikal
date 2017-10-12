package code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


/*This class is the Main class of the program, it starts the game and creates various objects
 * needed to run the game
 */

public class Main {

	private static ArrayList<TerrainPiece> board;
	private static ArrayList<Player> playerNames;
	private static int[] scores;

	public static void main(String[] args) {
		makePlayers();
		createBoard();


		new GUI(playerNames);

	/*	TESTING METHODS
	 * playerNames.get(0).placeTile(board.get(12));
		playerNames.get(1).placeExplorer(board.get(12));
		playerNames.get(1).placeExplorer(board.get(12));
		playerNames.get(1).placeExplorer(board.get(12));
		playerNames.get(0).placeExplorer(board.get(12));

		score();*/

	}

	//This method creates a number of TerrainPieces based on the # of players in the game
	public static void createBoard() {
		board = new ArrayList<TerrainPiece>();
		// creates a list of TerrainPieces
		int tileNum = 34 + (playerNames.size() * 11) - 22;
		for (int i = 0; i < tileNum; i++) {
			board.add(new TerrainPiece(i));
		}

	}

	//This method creates a specified number of players based
	//on user input
	public static void makePlayers() {
		playerNames = new ArrayList<Player>();

		System.out.println("Please enter the number of players: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String amount = null;
		String name = null;

		try {
			amount = br.readLine();
		} catch (IOException ioe) {
			System.out.println("IO error trying to read your name!");
			System.exit(1);
		}

		for (int i = 1; i <= Integer.parseInt(amount); i++) {
			System.out.println("Player " + i + "'s Name: ");

			try {
				name = br.readLine();
			} catch (IOException ioe) {
				System.out.println("IO error trying to read your name!");
				System.exit(1);
			}

			playerNames.add(new Player(i - 1, name));

		}

		System.out.println("The players of the game are: ");
		for (int j = 0; j < playerNames.size(); j++) {
			System.out.println(playerNames.get(j).getName());
		}

	}

	//Accesor method for the list of Players
	public static ArrayList<Player> getPlayerList() {
		return playerNames;
	}
	//Accesor method for a specific Player
	public static Player getPlayer(int x){
		return playerNames.get(x);
	}

	//Accesor method for list of TerrainPieces
	public static ArrayList<TerrainPiece> getBoardPieces() {
		return board;
	}

	//This method will score the game and pronounce a winner
	public static void score() {
		scores = new int[playerNames.size()];
		for (int i = 0; i < board.size(); i++) {
			int[] expoCount = board.get(i).getExpoCount();

			int winner = 0;
			int winnerVal = expoCount[0];
			if (board.get(i).isEmpty()) {
				System.out.println("Empty piece");
			} else {
				if(board.get(i).getPyramid()==0){
				for (int j = 1; j < expoCount.length; j++) {
					if (expoCount[j] > winnerVal) {
						winnerVal = expoCount[j];
						winner++;
					}
					else{
					}
					scores[winner]++;
					System.out.println("Player " + winner + " gets a point");
				}}
				else{
					for (int j = 1; j < expoCount.length; j++) {
						if (expoCount[j] > winnerVal) {
							winnerVal = expoCount[j];
							winner++;
						}
						scores[winner]+= board.get(i).getPyramid();
						System.out.println("Player " + winner + " gets a pyramid");
						winner = 0;
						winnerVal = 0;
					}
					
				}
			}
		}
		int winnerID = 0;
		int winnerscore = scores[0];
		for (int x = 1; x < scores.length; x++) {
			System.out.println(scores[x]);
			if (scores[x] > winnerscore) {
				winnerscore = scores[x];
				winnerID++;
				System.out.println(winnerID);
			}
		}
		System.out.println("The winner is Player number " + (winnerID + 1));
		System.exit(0);

	}
}
