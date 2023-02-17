package infra;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerImpl {
    private static final Logger logger = Logger.getLogger(FileHandler.class.getName());
    public static void log(String message) {
        logger.log(Level.INFO, getTimestamp() + " [log] " + message);
    }

    public static void error(String message) {
        logger.log(Level.SEVERE, getTimestamp() + " [error] " + message);
    }

    public static String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss:SSS"));
    }

}
