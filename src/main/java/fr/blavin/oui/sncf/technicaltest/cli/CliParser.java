package fr.blavin.oui.sncf.technicaltest.cli;

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

	public void parseCommandLineArgs(String[] commandLineArgs) {
		Options options = constructOptions();
		LOG.debug("Parsing the command line args");
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, commandLineArgs);
			if (cmd.hasOption(CMD_OPTION.HELP.getShortOption())) {
				printHelp(options);
			} else if (cmd.hasOption(CMD_OPTION.BATCH.getShortOption())) {
				runApplication(cmd);
			} else {
				printHelp(options);
			}
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

	public void printHelp(final Options options) {
		HelpFormatter helpFormatter = new HelpFormatter();
		helpFormatter.printHelp("java -jar XspeedIt-jar-with-dependencies.jar [--batch <arg>] [--help]", options);
	}

}
