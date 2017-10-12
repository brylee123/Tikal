package tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import code.Main;
import code.Pyramid;

public class PyramidCountTest {
	private Pyramid pyramid;
	
	@Before public void setUp(){
		pyramid=new Pyramid();	
	}
	
	@Test public void testPyramidCount(){
		int expected=1;
		int actual = pyramid.getSize();
		assertTrue("This test-case was created to get the count of pyramids in a given tile. The actual pyramid count was: "+actual, actual == expected);
	}
		

}
