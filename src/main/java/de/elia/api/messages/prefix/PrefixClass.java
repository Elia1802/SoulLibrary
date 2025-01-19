package de.elia.api.messages.prefix;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

import java.lang.String;

/**
 * Creates all the prefixes you need.
 * @author Elia
 * @since V.1.0.0
 */
public class PrefixClass {

    public PrefixClass(){

    }

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    /**
     * a prefix for the in game chat
     * @author Elia
     * @since V.1.0.0
     * @return {@link Component}
     */
    @NotNull
    public static Component prefix(){
        return miniMessage.deserialize("<dark_grey>[<#9545a3>SoulSMP</#9545a3>]</dark_grey> ");
    }

    /**
     * a prefix for error messages in the console
     * @author Elia
     * @since V.1.0.0
     * @return {@link Component}
     */
    @NotNull
    public static String errorPrefix(){
        return "[SoulError] ";
    }

    /**
     * a prefix for warning messages in the console
     * @author Elia
     * @since V.1.0.0
     * @return {@link Component}
     */
    @NotNull
    public static String warningPrefix(){
        return "[SoulWarning] ";
    }

    /**
     * a prefix for info messages in the console
     * @author Elia
     * @since V.1.0.0
     * @return {@link Component}
     */
    @NotNull
    public static String infoPrefix(){
        return "[SoulSMP] ";
    }
}
