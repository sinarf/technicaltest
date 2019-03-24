package fr.blavin.oui.sncf.technicaltest.cli;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import fr.blavin.oui.sncf.technicaltest.business.Box;
import fr.blavin.oui.sncf.technicaltest.cli.PresentationUtils;

public class PresentationUtilsTest {

	@Test
	public void testDisplay() throws Exception {
		ArrayList<Box> boxes = new ArrayList<>();
		assertEquals("Should return an empty string.", "", PresentationUtils.display(boxes));

		Box box1 = new Box();
		box1.addContent(1);
		box1.addContent(2);
		boxes.add(box1);
		assertEquals("Should return 12.", "12", PresentationUtils.display(boxes));

		Box box2 = new Box();
		box2.addContent(3);
		box2.addContent(4);
		boxes.add(box2);
		assertEquals("Incorrectly formated string.", "12/34", PresentationUtils.display(boxes));

		Box box3 = new Box();
		box3.addContent(5);
		box3.addContent(4);
		box3.addContent(1);
		boxes.add(box3);
		assertEquals("Incorrectly formated string.", "12/34/541", PresentationUtils.display(boxes));

		assertEquals("Should return an empty string.", "", PresentationUtils.display(null));
	}

}
