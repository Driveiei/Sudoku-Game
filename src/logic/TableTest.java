package logic;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TableTest {

	private RandomNumber random;
	private Table table;

	
	@Before
	public void setUp() throws Exception {
		RandomNumber.setRandomNumber(3);
		random = RandomNumber.getInstance();
		table = random.getTable();
		for (int grid = 0; grid < 9; grid++) {
			table.getList().add(new GridManager(grid));
		}
		for (int grid = 0; grid < 9; grid++) {
			for (int box = 1; box <= 9; box++) {
				table.getList().get(grid).getList().add(new BoxManager(box, false, false, 50));
			}
		}
	}

	@Test
	public void testDuplicateRow() {
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(table.duplicateRow(5, 5));
		assertEquals(6,list.size());
		assertTrue(list.contains(7));
		assertTrue(list.contains(8));
		assertTrue(list.contains(9));
		assertFalse(list.contains(1));
		assertFalse(list.contains(2));
		assertFalse(list.contains(3));
		assertFalse(list.contains(4));
		assertFalse(list.contains(5));
		assertFalse(list.contains(6));
		list.clear();
		list.addAll(table.duplicateRow(3, 4));
		assertEquals(0,list.size());
	}
	
	@Test
	public void testDuplicateColumn() {
		List<Integer> list = new ArrayList<Integer>();
		list.addAll(table.duplicateColumn(5, 6));
		assertEquals(3,list.size());
		assertTrue(list.contains(1));
		assertTrue(list.contains(4));
		assertTrue(list.contains(7));
		assertFalse(list.contains(3));
		assertFalse(list.contains(2));
		assertFalse(list.contains(6));
		assertFalse(list.contains(5));
		assertFalse(list.contains(9));
		assertFalse(list.contains(8));
		list.clear();
		list.addAll(table.duplicateColumn(0, 1));
		assertEquals(0,list.size());
	}
	
	@Test
	public void testInsert() {
		table.getList().remove(8);
		table.getList().add(new GridManager(8));
		table.insert(8, 6);
		assertEquals(6,table.getList().get(8).getList().get(0).getNumber());
		table.insert(8, 9);
		assertEquals(9,table.getList().get(8).getList().get(1).getNumber());
		assertEquals(2,table.getList().get(8).getList().size());

	}
	
	@Test
	public void testClear() {
		table.clear(8);
		table.clear(7);
		assertEquals(9, table.getList().size());
		assertEquals(0,table.getList().get(7).getList().size());
		assertEquals(0,table.getList().get(8).getList().size());
	}
}
