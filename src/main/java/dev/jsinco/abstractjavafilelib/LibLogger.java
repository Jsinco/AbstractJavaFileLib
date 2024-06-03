package dev.jsinco.abstractjavafilelib;

import java.util.logging.Logger;

public class LibLogger { // ttt

    private Logger logger;
    private org.slf4j.Logger slf4jLogger;

    public LibLogger(Logger logger) {
        this.logger = logger;
    }

    public LibLogger(org.slf4j.Logger logger) {
        this.slf4jLogger = logger;
    }

    public void info(String message) {
        if (logger != null) {
            logger.info(message);
        } else if (slf4jLogger != null) {
            slf4jLogger.info(message);
        }
    }

    public void error(String message) {
        if (logger != null) {
            logger.severe(message);
        } else if (slf4jLogger != null) {
            slf4jLogger.error(message);
        }
    }

    public void warn(String message) {
        if (logger != null) {
            logger.warning(message);
        } else if (slf4jLogger != null) {
            slf4jLogger.warn(message);
        }
    }

    public void debug(String message) {
        if (logger != null) {
            logger.fine(message);
        } else if (slf4jLogger != null) {
            slf4jLogger.debug(message);
        }
    }
}
