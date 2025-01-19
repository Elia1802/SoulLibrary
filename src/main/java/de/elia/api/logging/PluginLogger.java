package de.elia.api.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * A logger for this Plugin
 * @author Elia
 * @since 2.0.0
 */
public class PluginLogger {

  private final Logger logger;

  /**
   * Create a new Logger
   * @author Elia
   * @since 2.0.0
   * @param loggerName Requires a name for the logger
   */
  public PluginLogger(@NotNull String loggerName){
    this.logger = (Logger) LogManager.getLogger(loggerName);
  }

  /**
   * Print a Stacktrace in the console
   * @author Elia
   * @since 2.0.0
   * @param exception Requires the exception
   */
  public void stacktrace(Exception exception) {
    logger.error("Error occurred: ", exception);
  }

  /**
   * Print a Stacktrace in the console
   * @author Elia
   * @since 2.0.0
   * @param message
   * @param exception
   */
  public void stacktrace(String message, Exception exception) {
    logger.error("Error occurred: " + message, exception);
  }

  /**
   * log an info message
   * @author Elia
   * @since 2.0.0
   * @param message Requires the Message
   */
  public void logInfo(@NotNull String message){
    logger.info("[INFO]" + message);
  }

  /**
   * log a warning message
   * @author Elia
   * @since 2.0.0
   * @param message Requires the Message
   */
  public void logWarning(@NotNull String message){
    logger.warn("[WARNING]" + message);
  }

  /**
   * log an error message
   * @author Elia
   * @since 2.0.0
   * @param message Requires the Message
   */
  public void logError(@NotNull String message){
    logger.error("[ERROR]" + message);
  }

  /**
   * Gets the Logger
   * @author Elia
   * @since 2.0.0
   */
  @NotNull
  public Logger logger() {
    return logger;
  }

}
