package fr.blavin.oui.sncf.technicaltest.cli;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.MissingArgumentException;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.blavin.oui.sncf.technicaltest.business.Box;
import fr.blavin.oui.sncf.technicaltest.business.FancyBoxManager;
import fr.blavin.oui.sncf.technicaltest.business.WrongBatchException;

public class CliParser {
	private static final Logger LOG = LoggerFactory.getLogger(CliParser.class);
	/** The application name. */
	private final String applicationName;

	public CliParser(String applicationName) {
		this.applicationName = applicationName;
	}

	public void parseCommandLineArgs(String[] commandLineArgs) {
		Options options = constructOptions();
		LOG.debug("Parsing the command line args");
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, commandLineArgs);
			runApplication(cmd);
		} catch (MissingArgumentException e) {
			String message = "Argument manquant: " + e.getLocalizedMessage();
			LOG.error(message, e);
			throw new WrongBatchException(message, e);
		} catch (ParseException e) {
			String message = "Impossible to parse command line arguments";
			LOG.error(message, e);
			throw new WrongBatchException(message, e);
		}

	}

	private void runApplication(CommandLine cmd) {
		String batchContent = cmd.getOptionValue(CMD_OPTION.BATCH.getShortOption());
		System.out.println("Chaîne d'articles en entrée : " + batchContent);
		try {
			List<Box> boxes = new FancyBoxManager().process(batchContent);
			LOG.debug("List of boxes: " + boxes);
			System.out.println("Chaîne d'articles emballés  : " + PresentationUtils.display(boxes));
		} catch (WrongBatchException e) {
			System.err.println(e.getMessage());
		}

	}

	/**
	 * Construct options.
	 *
	 * @return the options
	 */
	public Options constructOptions() {
		final Options options = new Options();
		for (CMD_OPTION cmdOption : CMD_OPTION.values()) {
			options.addOption(cmdOption.getShortOption(), cmdOption.getLongOption(), cmdOption.isHasValue(),
					cmdOption.getDescription());
		}
		return options;
	}

	/**
	 * Prints the usage.
	 *
	 * @param applicationName the application name
	 * @param options         the options
	 * @param out             the out
	 */
	public void printUsage(final Options options, final OutputStream out) {
		try (final PrintWriter writer = new PrintWriter(out);) {
			final HelpFormatter usageFormatter = new HelpFormatter();
			usageFormatter.printUsage(writer, 80, applicationName, options);
		}
	}

}
