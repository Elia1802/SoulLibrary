package de.elia.api.messages.builder;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerKickEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static de.elia.api.messages.prefix.PrefixClass.errorPrefix;
import static de.elia.api.messages.prefix.PrefixClass.prefix;

/**
 * This class simplifies the principle of the {@link MiniMessage}. If used correctly,
 * you can save a lot of space. You don't need color codes of the {@link MiniMessage}.
 * @author Elia
 * @since V.1.0.0
 */
public class MessageBuilder {

    private static final MiniMessage miniMessage = MiniMessage.miniMessage();

    public MessageBuilder(){
    }

    /**
     * Make a new line between each {@link Component}
     * @author Elia
     * @since V.1.0.0
     * @param lines Requires the Components
     * @return {@link Component}
     */
    @NotNull
    public static Component newLines(@NotNull List<Component> lines){
        Component result = Component.empty();

        for (Component component : lines) {
            result = result.append(component);
        }

        return result;
    }

    /**
     * Make a new line between each {@link Component}
     * @author Elia
     * @since V.1.0.0
     * @param lines Requires the Components
     * @return {@link Component}
     */
    @NotNull
    public static Component newLines(Component @NotNull ... lines){
        Component result = Component.empty();

        for (Component component : lines) {
            result = result.append(component);
        }

        return result;
    }

    /**
     * Send a broadcast message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link Component}
     */
    public static void broadcast(Component message){
        Bukkit.broadcast(message);
    }

    /**
     * Send a broadcast message with Prefix
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link Component}
     */
    public static void broadcastWithPrefix(Component message){
        Bukkit.broadcast(prefix().append(message));
    }

    /**
     * Send a broadcast message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link String}
     */
    public static void broadcast(String message){
        Bukkit.broadcast(miniMessage.deserialize(message));
    }

    /**
     * Send a broadcast message with Prefix
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link Component}
     */
    public static void broadcastWithPrefix(String message){
        Bukkit.broadcast(miniMessage.deserialize(prefix() + message));
    }

    /**
     * Send a broadcast message to {@link Player} with a specified permission
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param permission Requires a permission
     * @param message Requires a message as {@link Component}
     */
    public static void broadcast(@NotNull Player player , String permission , Component message){
        if (player.hasPermission(permission)) {
            Bukkit.broadcast(message);
        }
    }

    /**
     * Send a broadcast message to {@link Player} with a specified permission with prefix
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param permission Requires a permission
     * @param message Requires a message as {@link Component}
     */
    public static void broadcastWithPrefix(@NotNull Player player , String permission , Component message){
        if (player.hasPermission(permission)) {
            Bukkit.broadcast(prefix().append(message));
        }
    }

    /**
     * Send a broadcast message to {@link Player} with a specified permission
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param permission Requires a permission
     * @param message Requires a message as {@link String}
     */
    public static void broadcast(@NotNull Player player , String permission , String message){
        if (player.hasPermission(permission)) {
            Bukkit.broadcast(miniMessage.deserialize(message));
        }
    }

    /**
     * Send a broadcast message to {@link Player} with a specified permission with prefix
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param permission Requires a permission
     * @param message Requires a message as {@link String}
     */
    public static void broadcastWithPrefix(@NotNull Player player , String permission , String message){
        if (player.hasPermission(permission)) {
            Bukkit.broadcast(miniMessage.deserialize(prefix() + message));
        }
    }

    /**
     * Send a broadcast message to {@link Player} with a specified permission
     * @author Elia
     * @since V.1.0.0
     * @param permission Requires a permission
     * @param message Requires a message as {@link Component}
     */
    public static void broadcast(Component message , String permission){
        Bukkit.broadcast(message , permission);
    }

    /**
     * Send a broadcast message to {@link Player} with a specified permission with Prefix
     * @author Elia
     * @since V.1.0.0
     * @param permission Requires a permission
     * @param message Requires a message as {@link Component}
     */
    public static void broadcastWithPrefix(Component message , String permission){
        Bukkit.broadcast(prefix().append(message) , permission);
    }

    /**
     * Send a broadcast message to {@link Player} with a specified permission
     * @author Elia
     * @since V.1.0.0
     * @param permission Requires a permission
     * @param message Requires a message as {@link String}
     */
    public static void broadcast(String message , String permission){
        Bukkit.broadcast(miniMessage.deserialize(message) , permission);
    }

    /**
     * Send a broadcast message to {@link Player} with a specified permission with prefix
     * @author Elia
     * @since V.1.0.0
     * @param permission Requires a permission
     * @param message Requires a message as {@link String}
     */
    public static void broadcastWithPrefix(String message , String permission){
        Bukkit.broadcast(miniMessage.deserialize(prefix() + message) , permission);
    }

    /**
     * kick a player with a message
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param message Requires a message as {@link Component}
     */
    public static void kickMessage(@NotNull Player player , Component message){
        player.kick(message);
    }

    /**
     * kick a player with a message and a cause
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param message Requires a message as {@link Component}
     * @param cause Requires a cause for the kick ({@link PlayerKickEvent.Cause})
     */
    public static void kickMessage(@NotNull Player player , Component message , PlayerKickEvent.Cause cause) {
        player.kick(message , cause);
    }

    /**
     * kick a player with a message and in a new line a cause
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param message Requires a message as {@link Component}
     * @param cause Requires a cause for the kick ({@link Component})
     */
    public static void kickMessage(@NotNull Player player , Component message , Component cause){
        player.kick(newLines(message , cause));
    }

    /**
     * kick a player with a message
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param message Requires a message as {@link String}
     */
    public static void kickMessage(@NotNull Player player , String message){
        player.kick(miniMessage.deserialize(message));
    }

    /**
     * kick a player with a message and a cause
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param message Requires a message as {@link Component}
     * @param cause Requires a cause for the kick ({@link PlayerKickEvent.Cause})
     */
    public static void kickMessage(@NotNull Player player , String message , PlayerKickEvent.Cause cause) {
        player.kick(miniMessage.deserialize(message) , cause);
    }

    /**
     * kick a player with a message and in a new line a cause
     * @author Elia
     * @since V.1.0.0
     * @param player Requires a {@link Player}
     * @param message Requires a message as {@link Component}
     * @param cause Requires a cause for the kick ({@link Component})
     */
    public static void kickMessage(@NotNull Player player, String message , Component cause){
        player.kick(newLines(miniMessage.deserialize(message) , cause));
    }

    /**
     * Set a command permission message
     * @author Elia
     * @since V.1.0.0
     * @deprecated permission messages have not worked for player-executed commands since 1.13
     * as clients without permission to execute a command are unaware of its existence
     * and therefore will not send an unknown command execution to the server.
     * This message will only ever be shown to consoles or when this command is executed with Bukkit.
     * dispatchCommand(CommandSender, String).
     * @param command Requires the Command
     * @param message Requires the Message
     */
    @Deprecated
    public static void permissionMessage(String command , String message){
        Component component = miniMessage.deserialize(errorPrefix() + message);
        Bukkit.getPluginCommand(command).permissionMessage(component);
    }

    /**
     * Send a message to a {@link Player}
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link Component}
     */
    public static void message(@NotNull Player player , Component message){
        player.sendMessage(message);
    }

    /**
     * Send a message to a {@link Player} with prefix
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link Component}
     */
    public static void messageWithPrefix(@NotNull Player player , Component message){
        player.sendMessage(prefix().append(message));
    }

    /**
     * Send a message to a {@link Player}
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link String}
     */
    public static void message(@NotNull Player player , String message){
        player.sendMessage(miniMessage.deserialize(message));
    }

    /**
     * Send a message to a {@link Player} with prefix
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link String}
     */
    public static void messageWithPrefix(@NotNull Player player , String message){
        player.sendMessage(miniMessage.deserialize(prefix() + message));
    }

    /**
     * Send a message to a {@link CommandSender}
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link Component}
     */
    public static void message(@NotNull CommandSender sender , Component message){
        sender.sendMessage(message);
    }

    /**
     * Send a message to a {@link CommandSender} with Prefix
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link Component}
     */
    public static void messageWithPrefix(@NotNull CommandSender sender , Component message){
        sender.sendMessage(prefix().append(message));
    }

    /**
     * Send a message to a {@link CommandSender}
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link String}
     */
    public static void message(@NotNull CommandSender sender , String message){
        sender.sendMessage(miniMessage.deserialize(message));
    }

    /**
     * Send a message to a {@link CommandSender} with Prefix
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message as {@link String}
     */
    public static void messageWithPrefix(@NotNull CommandSender sender , String message){
        sender.sendMessage(miniMessage.deserialize(prefix() + message));
    }

    //FOR THIS CLASS
    @NotNull
    private static Component color(String color , String message){
        return miniMessage.deserialize("<" + color + ">" + message + "</" + color + ">");
    }

    /**
     * write a message gradient
     * @author Elia
     * @since V.1.0.0
     * @param ofColor Requires the Color 1
     * @param toColor Requires the Color 2
     * @param message Requires the Message
     * @return {@link Component}
     */
    @NotNull
    public static Component gradient(String ofColor , String toColor , String message){
        return miniMessage.deserialize("<gradient:" + ofColor + ":" + toColor + ">" + message);
    }

    /**
     * create a hover text
     * @author Elia
     * @since V.1.0.0
     * @param textToShow the message to show if the player hovers
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component hover(String textToShow , String message){
        return miniMessage.deserialize("<hover:show_text'" + textToShow + "'>" + message);
    }

    /**
     * write a black message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component black(String message){
        return color("black" , message);
    }

    /**
     * write a dark blue message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component darkBlue(String message){
        return color("dark_blue" , message);
    }

    /**
     * write a dark green message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component darkGreen(String message){
        return color("dark_green" , message);
    }

    /**
     * write a dark aqua message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component darkAqua(String message){
        return color("dark_aqua" , message);
    }

    /**
     * write a dark red message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component darkRed(String message){
        return color("dark_red" , message);
    }

    /**
     * write a dark purple message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component darkPurple(String message){
        return color("dark_purple" , message);
    }

    /**
     * write a dark gray message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component darkGray(String message){
        return color("dark_gray" , message);
    }

    /**
     * write a gold message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component gold(String message){
        return color("gold" , message);
    }

    /**
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component gray(String message){
        return color("gray" , message);
    }

    /**
     * write a blue message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component blue(String message){
        return color("blue" , message);
    }

    /**
     * write a green message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component green(String message){
        return color("green" , message);
    }

    /**
     * write an aqua message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component aqua(String message){
        return color("aqua" , message);
    }

    /**
     * write a red message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component red(String message){
        return color("red" , message);
    }

    /**
     * write a light-purple message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component lightPurple(String message){
        return color("light_purple" , message);
    }

    /**
     * write a yellow message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component yellow(String message){
        return color("yellow" , message);
    }

    /**
     * write a white message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component white(String message){
        return color("white" , message);
    }

    /**
     * write a bold message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component bold(String message){
        return color("bold" , message);
    }

    /**
     * write an italic message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component italic(String message){
        return color("italic" , message);
    }

    /**
     * write an underlined message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component underlined(String message){
        return color("underlined" , message);
    }

    /**
     * write a strikethrough message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component strikethrough(String message){
        return color("strikethrough" , message);
    }

    /**
     * write an obfuscated message
     * @author Elia
     * @since V.1.0.0
     * @param message Requires a message
     * @return {@link Component}
     */
    @NotNull
    public static Component obfuscated(String message){
        return color("obfuscated" , message);
    }

}
