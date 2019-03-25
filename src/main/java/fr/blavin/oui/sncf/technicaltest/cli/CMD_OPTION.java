package fr.blavin.oui.sncf.technicaltest.cli;

public enum CMD_OPTION {
	BATCH("b", "batch", true, "Liste d'articles représentés par des entiers compris entre 1 et 9"),
	HELP("h", "help", false, "Affiche le message d'aide. ");

	private final String shortOption;
	private final String longOption;
	private final boolean hasValue;
	private final String description;

	private CMD_OPTION(String shortOption, String longOption, boolean hasValue, String description) {
		this.shortOption = shortOption;
		this.hasValue = hasValue;
		this.longOption = longOption;
		this.description = description;
	}

	/**
	 * @return the shortOption
	 */
	public String getShortOption() {
		return shortOption;
	}

	/**
	 * @return the longOption
	 */
	public String getLongOption() {
		return longOption;
	}

	/**
	 * @return the hasValue
	 */
	public boolean isHasValue() {
		return hasValue;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

}
