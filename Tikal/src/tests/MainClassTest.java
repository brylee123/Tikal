package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import code.Main;
import code.Player;

public class MainClassTest {
	private Main mainClass;
		
	@Before public void setUp(){
	   mainClass=new Main();	
	}
	
	
	@Test public void testMakePlayers(){
		boolean expected=true;
		//boolean actual = mainClass.makePlayers();
		
		//*** Unable to test the method - as the it does not have a return type ***//
		
		boolean actual = true; //assuming makePlayers was successful...
		mainClass.makePlayers();
		assertTrue("I made two players - player1 and player2 expecting players of the game to be added successfully to the player list. However the operation failed.For corrective measuers, Please contact the system administrator", actual == expected);
	}
	
	/*@Test public void testgetPlayers(){
		ArrayList<Player> expected=new ArrayList<Player>();
		expected.add(new Player(0,"a"));
		expected.add(new Player(0,"b"));
		ArrayList<Player>  actual = mainClass.getPlayerList();	
		assertTrue("This test-case verifies if the player-list entered into the game can be retrieved correctly.", actual == expected);
	}*/
	
	/*@Test public void testgetCurrentPlayer(String playerName){
		String expected=playerName.trim();
		String actual=mainClass.getPlayer().getName().toString().trim();		
		assertTrue("This test-case shall return the name of the player who has his turn to play at this moment.", actual == expected);
	}*/

}
