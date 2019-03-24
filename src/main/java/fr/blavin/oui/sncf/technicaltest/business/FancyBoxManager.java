package fr.blavin.oui.sncf.technicaltest.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 * @author Michel Blavin
 *
 */
public class FancyBoxManager {

	private static final Logger LOG = LoggerFactory.getLogger(FancyBoxManager.class);

	/** List of box with content */
	protected final List<Box> boxes = new ArrayList<>();

	public List<Box> process(String batch) {
		LOG.info("Chaîne d'articles en entrée : " + batch);

		if (null == batch) {
			String msg = "Wrong batch: the batch is null.";
			LOG.error(msg);
			throw new WrongBatchException(msg);
		}
		if (StringUtils.isEmpty(batch)) {
			LOG.warn("This is an empty batch nothing to do");
		} else {
			batch = batch.trim();
			process(batch.toCharArray());
		}
		return boxes;
	}

	protected void process(char[] charArray) {
		for (int i = 0; i < charArray.length; i++) {
			char contentToAdd = charArray[i];
			int contentAsInt = convert(contentToAdd);
			addToBox(contentAsInt);
		}
	}

	/**
	 * A valid content is an integer.
	 *
	 * @param content the content to add
	 * @return true, if is valid
	 */
	protected int convert(char content) {
		try {
			return Integer.parseInt(String.valueOf(content));
		} catch (NumberFormatException e) {
			String message = "Could not convert " + content + " to an integer";
			LOG.error(message);
			throw new WrongBatchException(message, e);
		}
	}

	/**
	 * Adds content the to a box.
	 *
	 * @param contentToAdd the content to add (an integer between 1 and 9)
	 */
	protected void addToBox(int content) {
		Box box = null;
		int i = 0;
		boolean notFound = true;

		while (notFound && i < boxes.size()) {
			box = boxes.get(i);
			boolean contentAdded = box.addContent(content);
			notFound = !contentAdded;
			++i;
		}

		if (notFound) {
			LOG.debug("No existing box found with enough space. Using a new one.");
			box = new Box();
			box.addContent(content);
			boxes.add(box);
		}
	}

}
