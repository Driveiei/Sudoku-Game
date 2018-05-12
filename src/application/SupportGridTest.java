package application;

import static org.junit.Assert.*;

import org.junit.Test;

public class SupportGridTest {

	private SupportGrid support = new SupportGrid(3);

	@Test
	public void testAdaptGrid() {
		assertEquals(7, support.adaptGrid(5, 6));
		assertEquals(0, support.adaptGrid(2, 2));
		assertEquals(3, support.adaptGrid(2, 5));
		assertEquals(8, support.adaptGrid(7, 6));
		assertEquals(4, support.adaptGrid(4, 5));
	}

	@Test
	public void testAdaptBox() {
		assertEquals(2, support.adaptBox(5, 6));
		assertEquals(7, support.adaptBox(1, 8));
		assertEquals(6, support.adaptBox(3, 2));
		assertEquals(7, support.adaptBox(4, 2));
		assertEquals(7, support.adaptBox(7, 5));
	}

	@Test
	public void testScaleToArray() {
		assertEquals(7, support.adaptGrid(4, 8));
		assertEquals(4, support.adaptGrid(5, 3));
		assertEquals(6, support.adaptGrid(0, 7));
		assertEquals(6, support.adaptGrid(2, 6));
		assertEquals(1, support.adaptGrid(3, 1));
	}

	@Test
	public void testScaleToRow() {
		assertEquals(8, support.adaptGrid(7, 6));
		assertEquals(4, support.adaptGrid(5, 4));
		assertEquals(7, support.adaptGrid(3, 8));
		assertEquals(3, support.adaptGrid(2, 3));
		assertEquals(2, support.adaptGrid(8, 0));
	}
	
	@Test
	public void testRandomCursor() {
		int randomOne = support.randomCursor(6);
		int randomTwo = support.randomCursor(100);
		int randomThree = support.randomCursor(3);
		int randomFour = support.randomCursor(2);
		int randomFive = support.randomCursor(6);
		boolean check = (randomOne <= 6 && randomOne >= 0);
		assertTrue(check);
		check = (randomTwo <= 100 && randomTwo >= 0);
		assertTrue(check);
		check = (randomThree <= 3 && randomThree >= 0);
		assertTrue(check);
		check = (randomFour <= 2 && randomFour >= 0);
		assertTrue(check);
		check = (randomFive <= 6 && randomFive >= 0);
		assertTrue(check);
	}
}
