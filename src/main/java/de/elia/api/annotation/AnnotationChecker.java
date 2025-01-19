package de.elia.api.annotation;

import de.elia.api.Main;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;

/**
 * Checks a Methode or a Class if they have the Annotations {@link Planned} or {@link Beta}
 */
public class AnnotationChecker {

  /**
   * Checks the methode
   * @param object The methode
   * @param name The name of the methode
   * @param plugin the plugin name
   */
  public static void processAnnotations(@NotNull Object object, String name, String plugin) {
    Class<?> clazz = object.getClass();
    Method[] methods = clazz.getDeclaredMethods();
    for (Method method : methods) {
      if (method.isAnnotationPresent(Planned.class)) {
        Planned annotation = method.getAnnotation(Planned.class);
        Main.getSoulLibrary().logger().logWarning("The methode " + name + " of the Plugin " + plugin + " has the annotation Planned! This means that the function used can still have errors!");
      }else if (method.isAnnotationPresent(Beta.class)) {
        Beta annotation = method.getAnnotation(Beta.class);
        Main.getSoulLibrary().logger().logWarning("The methode " + name + " of the Plugin " + plugin + " has the annotation Beta! This means that the function used can still have bugs or is still unstable");
      }
    }
  }

  /**
   * Checks the class
   * @param clazz The class
   * @param name The name of the class
   * @param plugin the plugin name
   */
  public static void processAnnotations(@NotNull Class<?> clazz, String name, String plugin) {
    if (clazz.isAnnotationPresent(Planned.class)) {
      Planned annotation = clazz.getAnnotation(Planned.class);
      Main.getSoulLibrary().logger().logWarning("The class " + name + " of the Plugin " + plugin + " has the annotation Planned! This means that the function used can still have errors!");
    }else if (clazz.isAnnotationPresent(Beta.class)) {
      Beta annotation = clazz.getAnnotation(Beta.class);
      Main.getSoulLibrary().logger().logWarning("The class " + name + " of the Plugin " + plugin + " has the annotation Beta! This means that the function used can still have bugs or is still unstable");
    }
  }

}
