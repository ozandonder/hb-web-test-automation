package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.qameta.allure.Allure.addAttachment;

public class LoggingUtil {
    private static final Logger logger = LoggerFactory.getLogger(LoggingUtil.class);

    public static void logInfo(String message) {
        logger.info(message);
        addAttachment("Info Log", message);
    }

    public static void logError(String message, Throwable exception) {
        logger.error(message, exception);
        addAttachment("Error Log", exception.toString(), message);
    }

    public static void logTrace(String message) {
        logger.trace(message);
        addAttachment("Trace Log", message);
    }

    public static void logDebug(String message) {
        logger.debug(message);
        addAttachment("Debug Log", message);
    }

    public static void logWarning(String message) {
        logger.warn(message);
        addAttachment("Warning Log", message);
    }
}