package com.simplehttpserver.httpserver.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerLogger {
    private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void logInfos(String info) {
        LOGGER.log(Level.INFO, info);
    }

    public static void logError(String info, Throwable throwable) {
        LOGGER.log(Level.INFO, info, throwable);
    }
}
