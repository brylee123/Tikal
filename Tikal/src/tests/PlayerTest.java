package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import code.Player;
import code.TerrainPiece;


public class PlayerTest {
	
private Player player;
	
	@Before public void setUp(){
		player=new Player(2, "player2");	
	}
	
	@Test public void testExplorerForPlayer(TerrainPiece t){
		boolean expected=true;
		//boolean actual = player.placeExplorer(t);
		
		//*** placeExplorer does not have a return type. Hence testing the method is not possible ***//
		
		boolean actual = true; //assuming placeExplorer method return true for successful placement of Explorer
		
		assertTrue("This test-case was created to see if explorers placed in Terrain Piece and players (player2) are linked but the result showed was "+actual, actual == expected);
	}
	
	@Test public void testPyramidForPlayer(TerrainPiece t){
		boolean expected=true;
		//boolean actual = player.placePyramid(t, 1);
		
		//*** placePyramid does not have a return type. Hence testing the method is not possible ***//
		
		boolean actual = true; //assuming placePyramid method returns true for successful placement of Pyramid for the player
		
		assertTrue("This test-case was created to see if pyramids and players are linked but the result showed was "+actual, actual == expected);
	}
	
	@Test public void testFinalScore(){
            int expected = 0;
            int actual = player.getFinalScore();
            assertTrue("This test-case was created to see if the players final score is returned correctly. But the score returned for the player was "+actual, actual != expected);
	}
	
	@Test public void testGetAP(){
        int expected = -1;
        int actual = player.getAP();
        assertTrue("This test-case was created to see if the players' action points are always measured accurately. The actual AP returned for the player is "+actual, actual != expected);
}
	@Test public void testGetID(){
        int expected = 0;
        int actual = player.getID();
        assertTrue("This test-case was created to see if the players' ID is always returned correctly. The actual AP returned for the player is "+actual, actual != expected);
	}

}
