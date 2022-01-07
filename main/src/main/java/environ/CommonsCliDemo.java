package environ;

import java.io.*;

import org.apache.commons.cli.*;

/**
 * Simple Demo Apache Commons CLI options parser
 * Usage:
 *	CommonsCliDemo // Output on stdout
 *	CommonsCliDemo -o filename // Output in file named
 *	CommonsCliDemo --output filename // Same: Output in file named
 */
public class CommonsCliDemo {
	public static void main(String[] args) {
		Options options = new Options();
		Option input = new Option("o", "output", true, "output file name");
		input.setRequired(false);
		options.addOption(input);
		CommandLineParser parser = new DefaultParser();
		HelpFormatter formatter  = new HelpFormatter();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
			if(cmd.hasOption('o')) {
				String fileName = cmd.getOptionValue('o');
				System.setOut(new PrintStream(new FileOutputStream(fileName)));
			}
		} catch(ParseException e) {
			formatter.printHelp("CommonsCliDemo", options);
			System.exit(1);
		} catch(Exception e) {
			System.err.println("Unexpected error:");
			e.printStackTrace();
		}
		System.out.println("This is the output from CommonsCliDemo");
	}
}

