package de.elia.api.timing.utils;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * This is the Timer class of the Plugin SoulBoss.
 * @author D1p4k, Elia
 * @since 2.0.0
 */
public class TimerUtils {

  /**
   * Run a timer.
   * @author D1p4k, Elia
   * @since 2.0.0
   * @param runnable Requires the runnable to be executed when ticks are 0.
   * @param ticks Requires the ticks how long the runnable goes
   */
  public void countdownAndRun(int ticks, Runnable runnable, Plugin plugin){
    if (runnable == null)return;
    new BukkitRunnable() {
      private int internalTicks = ticks;
      @Override
      public void run() {
        if (internalTicks == 0) {
          runnable.run();
          cancel();
          return;
        }

        internalTicks--;
      }
    }.runTaskTimerAsynchronously(plugin, 1, 1);
  }

  /**
   * Run a timer.
   * @author D1p4k, Elia
   * @since 2.0.0
   * @param ticks Requires the ticks how long the runnable goes
   * @param intervalRunnable Requires a runnable counting down the timer
   * @param finalRunnable Requires a runnable to be executed when intervalRunnable has finished running.
   */
  public void countdownInterval(int ticks, TimeRunnable intervalRunnable, Runnable finalRunnable, Plugin plugin){
    if (intervalRunnable == null || finalRunnable == null)return;
    new BukkitRunnable() {
      private int internalTicks = ticks;
      @Override
      public void run() {
        if (internalTicks == 0) {
          finalRunnable.run();
          cancel();
          return;
        }
        intervalRunnable.run(internalTicks);
        internalTicks--;
      }
    }.runTaskTimerAsynchronously(plugin, 1, 1);
  }

  /**
   * This is the TimerRunnable interface of the Plugin SoulBoss.
   * @author D1p4k, Elia
   * @since 2.0.0
   */
  public interface TimeRunnable {
    /**
     * @author D1p4k, Elia
     * @version 1.0.0
     * @since 2.0.0
     * @description Just lets a countdown count down without what happens after
     * @param ticks Requires the ticks how long the runnable goes
     */
    void run(int ticks);
  }
}
