package fr.blavin.oui.sncf.technicaltest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class Box, represents and standard box.
 */
public class Box {
	private static final Logger LOG = LoggerFactory.getLogger(Box.class);

	/** The size of the box. */
	protected static final int SIZE = 10;

	/** List of contents in the box. */
	private final List<Integer> contents = new ArrayList<>();

	/**
	 * Checks if is full.
	 *
	 * @return true, if is full
	 */
	public boolean isFull() {
		return SIZE == occupiedSpace();
	}

	/**
	 * Adds the content. Return true if the content has been added, false otherwise.
	 *
	 * @param content the content
	 * @return true, if successful
	 */
	public boolean addContent(int content) {
		if (content > 0 && freeSpace() >= content) {
			contents.add(content);
			LOG.debug("Content added: " + content);
			return true;
		}
		return false;
	}

	/**
	 * Occupied space.
	 *
	 * @return the occupied space in the box
	 */
	private int occupiedSpace() {
		int occupiedSpace = 0;
		for (Integer content : contents) {
			occupiedSpace += content;
		}
		return occupiedSpace;
	}

	/**
	 * Free space.
	 *
	 * @return the free space left in the box.
	 */
	public int freeSpace() {
		return SIZE - occupiedSpace();
	}

	public List<Integer> getContents() {
		return contents;
	}

	@Override
	public String toString() {
		return "Box " + contents;
	}
}
