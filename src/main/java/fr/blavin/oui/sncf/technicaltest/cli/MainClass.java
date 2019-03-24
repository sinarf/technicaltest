package fr.blavin.oui.sncf.technicaltest.cli;

/**
 * The class MainClass is the entry point of the application.
 */
public class MainClass {

	/**
	 * only static methods ;).
	 */
	private MainClass() {

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] commandLineArgs) {
		new CliParser("Xspeedt").parseCommandLineArgs(commandLineArgs);
	}

}
