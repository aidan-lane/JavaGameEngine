package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class Logger {

	private static final String logPath = "debug/log.txt";
	private static PrintWriter out = null;
	
	static {
		try {
			out = new PrintWriter(new FileWriter(logPath, false));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Prints DEBUG statement and writes to log
	 * 
	 * @param statement String statement to be logged
	 */
	public static void LOG(String statement) {
		if (statement.length() == 0) return;
		String s = LocalDateTime.now() + " " + statement;
		out.println(s);
		System.out.println(s);
	}
	
	/**
	 * Prints ERROR message and writes to log
	 * 
	 * @param error String error to be logged
	 */
	public static void ERROR(String error) {
		if (error.length() == 0) return;
		String s = LocalDateTime.now() + " (ERROR): " + error;
		out.println(s);
		System.out.println(s);
	}
	
	/**
	 * Closes PrintWriter stream
	 */
	public static void close() {
		out.close();
	}
}
