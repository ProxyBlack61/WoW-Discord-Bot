package Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Kilian Stöckler
 */

public class Logger {
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss, dd.MM.yyyy");
	
	public static void print(Type type, String string){
		System.out.format("%s (%s) -> %s\n", type.toString(), LocalDateTime.now().format(dtf), string);

	}

	public static void print(Type type, String string, Object... args) {
		System.out.format("%s (%s) -> %s\n", type.toString(), LocalDateTime.now().format(dtf), String.format(string, args));
	}

	public enum Type {
		SUCCESS, WARNING, ERROR, CONSOLE;
	}

}
