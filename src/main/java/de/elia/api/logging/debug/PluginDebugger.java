package de.elia.api.logging.debug;

/**
 * To Send Debug messages
 * @author Elia
 * @since 1.0.0.pre1
 */
public class PluginDebugger {

  /**
   * Debug a {@link Object} in the console
   * @author Elia
   * @since 2.0.0
   * @param debug send a debug message
   */
  public static void debug(Object debug){
    System.out.println("!!!SOUL-DEBUG!!!" + debug);
  }

}
