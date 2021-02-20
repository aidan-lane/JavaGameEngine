package util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Logger logs the debug output to both standard out
 * and the specified log file
 *
 */
public class Logger {

	private String logPath;
	private PrintWriter out;
	
	/**
	 * Default conctructor chooses location
	 * of log file as `debug/log.txt`
	 */
	public Logger() {
		this("debug/log.txt");
	}
	
	/**
	 * User specified path for log file
	 * @param path path where log file will be saved
	 */
	public Logger(String path) {
		this.logPath = path;
		try {
			out = new PrintWriter(new FileWriter(logPath, false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints DEBUG statement and writes to log
	 * @param statement String statement to be logged
	 */
	public void log(String statement) {
		String s = LocalDateTime.now() + " " + statement;
		out.println(s);
		System.out.println(s);
	}
	
	/**
	 * Prints ERROR message and writes to log
	 * @param error String error to be logged
	 */
	public void err(String error) {
		String s = LocalDateTime.now() + " (ERROR): " + error;
		out.println(s);
		System.out.println(s);
	}
	
	/**
	 * Closes PrintWriter stream
	 */
	public void close() {
		out.close();
	}
}
