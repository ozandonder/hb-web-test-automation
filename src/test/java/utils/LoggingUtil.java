package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.qameta.allure.Allure.addAttachment;

public class LoggingUtil {
    private static Logger logger;

    private static Logger getLogger() {
        return logger == null ? LoggerFactory.getLogger(LoggingUtil.class) : logger;
    }

    public static void logInfo(String message) {
        getLogger().info(message);
        addAttachment("Info Log", message);
    }

    public static void logError(String message, Throwable exception) {
        getLogger().error(message, exception);
        addAttachment("Error Log", exception.toString(), message);
    }

    public static void logTrace(String message) {
        getLogger().trace(message);
        addAttachment("Trace Log", message);
    }

    public static void logDebug(String message) {
        getLogger().debug(message);
        addAttachment("Debug Log", message);
    }

    public static void logWarning(String message) {
        getLogger().warn(message);
        addAttachment("Warning Log", message);
    }
}