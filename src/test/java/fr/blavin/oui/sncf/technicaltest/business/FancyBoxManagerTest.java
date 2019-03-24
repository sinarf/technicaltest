package fr.blavin.oui.sncf.technicaltest.business;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class FancyBoxManagerTest {

	private FancyBoxManager boxManager = new FancyBoxManager();

	@Test
	public void testProcessBatch() throws Exception {

		List<Box> boxes = new FancyBoxManager().process("163841689525773");
		assertEquals(8, boxes.size());

		List<Box> boxes2 = new FancyBoxManager().process("99992222111148488");
		assertEquals(9, boxes2.size());
	}

	@Test
	public void testAddToBox() throws Exception {
		// this part of the test would have work in legacy code.
		boxManager.addToBox(10);
		assertEquals(1, boxManager.boxes.size());
		boxManager.addToBox(6);
		assertEquals(2, boxManager.boxes.size());
		boxManager.addToBox(6);
		assertEquals(3, boxManager.boxes.size());

		// now we are testing the new behavior, by filling the space in existing box
		boxManager.addToBox(4);
		assertEquals(3, boxManager.boxes.size());
		boxManager.addToBox(3);
		assertEquals(3, boxManager.boxes.size());

	}
}
