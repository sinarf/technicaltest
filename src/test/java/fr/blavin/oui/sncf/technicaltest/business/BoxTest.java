package fr.blavin.oui.sncf.technicaltest.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.blavin.oui.sncf.technicaltest.business.Box;

public class BoxTest {

	@Test
	public void testIsFull() throws Exception {
		Box box = new Box();
		box.addContent(10);
		assertTrue("The box should be full", box.isFull());

		box = new Box();
		box.addContent(6);
		box.addContent(4);
		assertTrue("The box should be full", box.isFull());

		box = new Box();
		assertFalse("The box should NOT be full", box.isFull());

		box = new Box();
		box.addContent(9);
		assertFalse("The box should NOT be full", box.isFull());
		
	}

	@Test
	public void testAddContent() throws Exception {
		Box box = new Box();
		assertTrue("The content should have been added.", box.addContent(10));
		assertFalse("The box is full, the content shouldn't have been added", box.addContent(1));

		box = new Box();
		assertTrue("The content should have been added.", box.addContent(1));
		assertTrue("The content should have been added.", box.addContent(9));
		
		box = new Box();
		assertFalse("0 is not a valid content and shouldn't have been added", box.addContent(0));
	}


	@Test
	public void testFreeSpace() throws Exception {
		Box box = new Box();
		assertEquals ("The box is empty, the free space should be the box size.", Box.SIZE, box.freeSpace());
		box.addContent(2);
		assertEquals (8, box.freeSpace());
		box.addContent(8);
	}

}
