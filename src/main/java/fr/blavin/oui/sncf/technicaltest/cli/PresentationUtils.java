package fr.blavin.oui.sncf.technicaltest.cli;

import java.util.List;

import fr.blavin.oui.sncf.technicaltest.business.Box;

/**
 * The Class PresentationLayer is handling the user interface. Here the command
 * line.
 */
public class PresentationUtils {

	private static final String BOX_SEPARATOR = "/";

	/**
	 * this class only have static methods, and therefore should not be instanced.
	 */
	private PresentationUtils() {
	}

	public static String display(List<Box> boxes) {
		if (null == boxes) {
			return "";
		}
		StringBuilder formatedString = new StringBuilder();
		for (Box box : boxes) {
			if (formatedString.length() > 0) {
				formatedString.append(BOX_SEPARATOR);
			}
			for (Integer content : box.getContents()) {
				formatedString.append(content);
			}
		}
		return formatedString.toString();
	}

}
