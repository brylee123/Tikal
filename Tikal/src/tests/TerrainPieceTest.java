package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import code.TerrainPiece;

public class TerrainPieceTest {
	
	private TerrainPiece terrainPiece;
	
	@Before public void setUp(){
	   terrainPiece=new TerrainPiece(33);	
	}
	
	@Test public void testExplorerCount(){
		int expected = 0;
		int[] actual = terrainPiece.getExpoCount();
		assertTrue("This test-case was created to get the count of explorer for a given player. The actual explorer count was: "+ actual, expected != actual.length);
	}
	
	@Test public void testRemoveExpo(){
		boolean expected = true;
		boolean actual = terrainPiece.removeExpo(2);
		assertTrue("This test-case was created to remove explorers for players. The explorer count after removal of explorer was: "+ actual, expected == actual);
	}
		

}
