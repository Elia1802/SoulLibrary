package de.elia.api.configuration;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.MemorySection;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.File;
import java.lang.Boolean;
import java.lang.Byte;
import java.lang.Character;
import java.lang.Class;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Short;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class is a huge collection of all configuration file functions. Here are methods to
 * create and store and read values.
 * @author Elia
 * @since V. 1.0.0
 * @version V. 1.0.0
 */
public class SoulConfiguration {

    public File file;
    private File filepath;
    private String pathName;
    private YamlConfiguration configuration;
    private boolean useCustomPath = false;
    private final Plugin plugin;
    private final String fileName;

    /**
     * Create a configuration file.
     * @author Elia
     * @since V. 1.0.0
     * @param plugin Requires the instance of the Main class.
     * @param name Requires a name for the file.
     */
    public SoulConfiguration(Plugin plugin , String name){
        this(plugin , name , plugin.getDataFolder());
    }

    /**
     * Create a configuration file.
     * @author Elia
     * @since V. 1.0.0
     * @param plugin Requires the instance of the Main class.
     * @param name Requires a name for the file.
     * @param copyDefaults Requires true or false if the default values should be loaded into the target configuration.
     */
    public SoulConfiguration(Plugin plugin , String name , boolean copyDefaults){
        this(plugin , name , plugin.getDataFolder() , copyDefaults);
    }

    /**
     * Create a configuration file.
     * @author Elia
     * @since V. 1.0.0
     * @param plugin Requires the instance of the Main class.
     * @param name Requires a name for the file.
     * @param copyDefaults Requires true or false if the default values should be loaded into the target configuration.
     * @param replace Requires true or false to query whether to replace the file if it exists.
     */
    public SoulConfiguration(Plugin plugin , String name , boolean copyDefaults , boolean replace){
        this(plugin , name , plugin.getDataFolder() , copyDefaults , replace);
    }

    /**
     * Create a configuration file.
     * @author Elia
     * @since V. 1.0.0
     * @param plugin Requires the instance of the Main class.
     * @param name Requires a name for the file.
     * @param parent Requires a file path.
     */
    public SoulConfiguration(Plugin plugin , String name , File parent){
        this.plugin = plugin;
        this.fileName = name;
        filepath = parent;
        file = new File(filepath , name);

        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        configuration = YamlConfiguration.loadConfiguration(file);
        save();
    }

    /**
     * Create a configuration file.
     * @author Elia
     * @since V. 1.0.0
     * @param plugin Requires the instance of the Main class.
     * @param name Requires a name for the file.
     * @param parent Requires a file path.
     * @param copyDefaults Requires true or false if the default values should be loaded into the target configuration.
     */
    public SoulConfiguration(Plugin plugin , String name , File parent , boolean copyDefaults){
        this(plugin , name , parent , copyDefaults , false);
    }

    /**
     * Create a configuration file.
     * @author Elia
     * @since V. 1.0.0
     * @param plugin Requires the instance of the Main class.
     * @param name Requires a name for the file.
     * @param parent Requires a file path.
     * @param copyDefaults Requires true or false if the default values should be loaded into the target configuration.
     * @param replace Requires true or false to query whether to replace the file if it exists.
     */
    public SoulConfiguration(Plugin plugin , String name , File parent , boolean copyDefaults , boolean replace){
        this.plugin = plugin;
        this.fileName = name;
        filepath = parent;
        file = new File(filepath , name);

        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (copyDefaults) {
            if (!file.exists()) {
                this.plugin.saveResource(name , false);
            }else if (replace) {
                this.plugin.saveResource(name , true);
            }else {
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    }catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }

        configuration = YamlConfiguration.loadConfiguration(file);
        save();
    }

    /**
     * Create a configuration file.
     * @author Elia
     * @since V. 1.0.0
     * @param plugin Requires the instance of the Main class.
     * @param pathName Requires a file path.
     * @param name Requires a name for the file.
     */
    public SoulConfiguration(@NotNull Plugin plugin , String pathName , String name) {
        this.plugin = plugin;
        this.pathName = pathName;
        this.fileName = name;
        filepath = new File(plugin.getDataFolder() , pathName);
        useCustomPath = true;
        file = new File(filepath , name);

        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        configuration = YamlConfiguration.loadConfiguration(file);
        save();
    }

    /**
     * Create a configuration file.
     * @author Elia
     * @since V. 1.0.0
     * @param plugin Requires the instance of the Main class.
     * @param pathName Requires a file path.
     * @param name Requires a name for the file.
     * @param copyDefaults Requires true or false if the default values should be loaded into the target configuration.
     */
    public SoulConfiguration(Plugin plugin , String pathName , String name , boolean copyDefaults){
        this(plugin , pathName , name , copyDefaults , false);
    }

    /**
     * Create a configuration file.
     * @author Elia
     * @since V. 1.0.0
     * @param plugin Requires the instance of the Main class.
     * @param pathName Requires a file path.
     * @param name Requires a name for the file.
     * @param copyDefaults Requires true or false if the default values should be loaded into the target configuration.
     * @param replace Requires true or false to query whether to replace the file if it exists.
     */
    public SoulConfiguration(@NotNull Plugin plugin , String pathName , String name , boolean copyDefaults , boolean replace){
        this.plugin = plugin;
        this.pathName = pathName;
        this.fileName = name;
        filepath = new File(plugin.getDataFolder() , pathName);
        useCustomPath = true;
        file = new File(filepath , name);

        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (copyDefaults) {
            if (!file.exists()) {
                this.plugin.saveResource(pathName + name , false);
            }else if (replace) {
                this.plugin.saveResource(pathName + name , true);
            }else {
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    }catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }

        configuration = YamlConfiguration.loadConfiguration(file);
        save();
    }

    /**
     * Reloads the configuration.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link YamlConfiguration}
     */
    @NotNull
    public YamlConfiguration reload() {
        if (filepath == null && useCustomPath) {
            filepath = new File(plugin.getDataFolder() , pathName);
        }else if (filepath == null) {
            filepath = new File(plugin.getDataFolder() , plugin.getDataFolder().getName());
        }

        if (file == null) {
            file = new File(filepath , fileName);
            configuration = YamlConfiguration.loadConfiguration(file);
            save();
        }
        return configuration;
    }

    /**
     * Loads the file.
     * @author Elia
     * @since V. 1.0.0
     * @param file Requires the file.
     */
    public void load(File file){
        try {
            configuration.load(file);
            save();
        }catch (InvalidConfigurationException | IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loads the file.
     * @author Elia
     * @since V. 1.0.0
     * @param file Requires the file.
     */
    public void load(String file){
        try {
            configuration.load(file);
            save();
        }catch (InvalidConfigurationException | IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loads the reader.
     * @author Elia
     * @since V. 1.0.0
     * @param reader Requires the reader.
     */
    public void load(Reader reader){
        try {
            configuration.load(reader);
            save();
        }catch (InvalidConfigurationException | IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Loads the configuration.
     * @author Elia
     * @since V. 1.0.0
     * @param file Requires the file.
     * @return {@link YamlConfiguration}
     */
    @NotNull
    public YamlConfiguration loadConfiguration(File file){
        configuration = YamlConfiguration.loadConfiguration(file);
        save();
        return configuration;
    }

    /**
     * Loads the configuration.
     * @author Elia
     * @since V. 1.0.0
     * @param reader Requires the reader.
     * @return {@link YamlConfiguration}
     */
    @NotNull
    public YamlConfiguration loadConfiguration(Reader reader){
        configuration = YamlConfiguration.loadConfiguration(reader);
        save();
        return configuration;
    }

    /**
     * Loads the configuration from a string.
     * @author Elia
     * @since V. 1.0.0
     * @param content Requires the String.
     */
    public void loadFromString(String content){
        try {
            configuration.loadFromString(content);
            save();
        }catch (InvalidConfigurationException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Save the configuration from a String.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link String}
     */
    @NotNull
    public String saveToString(){
        String string = configuration.saveToString();
        save();
        return string;
    }

    /**
     * Save the Configuration.
     * @author Elia
     * @since V. 1.0.0
     */
    public void save(){
        try {
            configuration.save(file);
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Save the Configuration.
     * @author Elia
     * @since V. 1.0.0
     * @param file Requires the file.
     */
    public void save(File file){
        try {
            configuration.save(file);
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Save the Configuration.
     * @author Elia
     * @since V. 1.0.0
     * @param file Requires the file.
     */
    public void save(String file){
        try {
            configuration.save(file);
        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Sets the defaults to false.
     * @author Elia
     * @since V. 1.0.0
     */
    public void setDefaults(){
        setDefaults(false);
    }

    /**
     * Sets the defaults settings.
     * @author Elia
     * @since V. 1.0.0
     * @param replace Requires true or false.
     */
    public void setDefaults(boolean replace){
        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        if (pathName != null) {
            if (!file.exists()) {
                this.plugin.saveResource(pathName + fileName , false);
            }else if (replace) {
                this.plugin.saveResource(pathName + fileName , true);
            }else {
                if (!file.exists()) {
                    this.plugin.saveResource(fileName , false);
                }else if (replace) {
                    this.plugin.saveResource(fileName , true);
                }
            }
        }

        save();
    }

    /**
     * Sets the default settings.
     * @author Elia
     * @since V. 1.0.0
     * @param defaults Requires the default settings.
     */
    public void setDefaults(Configuration defaults){
        configuration.setDefaults(defaults);
        save();
    }

    /**
     * Adds the default settings.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path where to save it.
     * @param value Requires the {@link Object} to be stored.
     */
    public void addDefault(String path , Object value){
        configuration.addDefault(path , value);
        save();
    }

    /**
     * Adds the default settings.
     * @author Elia
     * @since V. 1.0.0
     * @param defaults Requires the path and the {@link Object} in a {@link Map}.
     *                 The map looks like this: {@link Map<String, Object>}
     */
    public void addDefaults(Map<String , Object> defaults){
        configuration.addDefaults(defaults);
        save();
    }

    /**
     * Adds the default settings.
     * @author Elia
     * @since V. 1.0.0
     * @param defaults Requires the default settings.
     */
    public void addDefaults(Configuration defaults){
        configuration.addDefaults(defaults);
        save();
    }

    /**
     * Gets the default settings.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link Configuration}
     */
    @NotNull
    public Configuration getDefaults(){
        return configuration.getDefaults();
    }

    /**
     * If the value is true, the default values are loaded into the target configuration.
     * @author Elia
     * @since V. 1.0.0
     * @param value Requires true or false.
     */
    public void copyDefaults(boolean value){
        YamlConfigurationOptions options = configuration.options().copyDefaults(value);
        save();
    }

    /**
     * Gets the copy default settings.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean getCopyDefaults(){
        return copyDefaults();
    }

    /**
     * Get the copy default value.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean copyDefaults(){
        return configuration.options().copyDefaults();
    }

    /**
     * Requires a List of Strings.
     * @author Elia
     * @since V. 1.0.0
     * @param value Requires a List of Strings.
     * @return {@link YamlConfigurationOptions}
     */
    @NotNull
    public YamlConfigurationOptions setHeader(List<String> value){
        YamlConfigurationOptions options = configuration.options().setHeader(value);
        save();
        return options;
    }

    /**
     * Requires multiple String.
     * @author Elia
     * @since V. 1.0.0
     * @param values Requires multiple String.
     * @return {@link YamlConfigurationOptions}
     */
    @NotNull
    public YamlConfigurationOptions setHeader(String... values){
        List<String> headerValues = new ArrayList<>();
        Collections.addAll(headerValues , values);
        YamlConfigurationOptions options = configuration.options().setHeader(headerValues);
        save();
        return options;
    }

    /**
     * Gets the Header.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link List<String>}
     */
    @NotNull
    public List<String> header(){
        return configuration.options().getHeader();
    }

    /**
     * Requires a List of Strings.
     * @author Elia
     * @since V. 1.0.0
     * @param value Requires a List of Strings.
     * @return {@link YamlConfigurationOptions}
     */
    @NotNull
    public YamlConfigurationOptions setFooter(List<String> value){
        YamlConfigurationOptions options = configuration.options().setFooter(value);
        save();
        return options;
    }

    /**
     * Requires multiple Strings.
     * @author Elia
     * @since V. 1.0.0
     * @param values Requires multiple Strings.
     * @return {@link YamlConfigurationOptions}
     */
    @NotNull
    public YamlConfigurationOptions setFooter(String... values){
        List<String> footerValues = new ArrayList<>();
        Collections.addAll(footerValues , values);
        YamlConfigurationOptions options = configuration.options().setFooter(footerValues);
        save();
        return options;
    }

    /**
     * Gets a Footer.
     * @author Elia
     * @return {@link List<String>}
     */
    @NotNull
    public List<String> getFooter(){
        return configuration.options().getFooter();
    }

    /**
     * Set comments in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param comments Requires the comments in a {@link String} {@link List}.
     */
    public void setComments(String path , List<String> comments) {
        configuration.setComments(path, comments);
        save();
    }

    /**
     * Set comments in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param comments Requires the comments in multiple {@link String}s.
     */
    public void setComments(String path , String... comments){
        List<String> commentList = new ArrayList<>();
        Collections.addAll(commentList , comments);
        configuration.setComments(path , commentList);
        save();
    }

    /**
     * Gets the comments in a specific path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path
     * @return {@link List<String>}
     */
    @NotNull
    public List<String> getComments(String path){
        return !contains(path) ? null : configuration.getComments(path);
    }

    /**
     * Set inline comments in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param comments Requires the inline comments in a {@link String} {@link List}.
     */
    public void setInlineComments(String path , List<String> comments){
        configuration.setInlineComments(path , comments);
        save();
    }

    /**
     * Set inline comments in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param comments Requires the inline comments in multiple {@link String}s.
     */
    public void setInlineComments(String path , String... comments) {
        List<String> commentList = new ArrayList<>();
        Collections.addAll(commentList, comments);
        configuration.setInlineComments(path, commentList);
        save();
    }

    /**
     * Gets the comments in a specific path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<String>}
     */
    @NotNull
    public List<String> getInlineComments(String path){
        return !contains(path) ? null : configuration.getInlineComments(path);
    }

    /**
     * Set parse comments.
     * @author Elia
     * @since V. 1.0.0
     * @param value Requires true or false.
     * @return {@link YamlConfiguration}
     */
    @NotNull
    public YamlConfigurationOptions setParseComments(boolean value){
        return parseComments(value);
    }

    /**
     * Set parse comments.
     * @author Elia
     * @since V. 1.0.0
     * @param value Requires true or false.#
     * @return {@link YamlConfigurationOptions}
     */
    @NotNull
    public YamlConfigurationOptions parseComments(boolean value){
        YamlConfigurationOptions options = configuration.options().parseComments(value);
        save();
        return options;
    }

    /**
     * Get parse comments.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link SoulConfiguration#parseComments()}
     */
    @NotNull
    public Boolean getParseComments(){
        return parseComments();
    }

    /**
     * Get parse comments.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean parseComments(){
        return configuration.options().parseComments();
    }

    /**
     * Save an indent in the configuration.
     * @author Elia
     * @since V. 1.0.0
     * @param indent Requires the indent.
     * @return {@link YamlConfigurationOptions}
     */
    @NotNull
    public YamlConfigurationOptions indent(int indent){
        YamlConfigurationOptions options = configuration.options().indent(indent);
        save();
        return options;
    }

    /**
     * Gets the indent.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link Integer}
     */
    public Integer getIndent(){
        return configuration.options().indent();
    }

    /**
     * Save a width in the configuration.
     * @author Elia
     * @since V. 1.0.0
     * @param width Requires the width.
     * @return {@link YamlConfigurationOptions}
     */
    @NotNull
    public YamlConfigurationOptions setWidth(int width){
        YamlConfigurationOptions options = configuration.options().width(width);
        save();
        return options;
    }

    /**
     * Gets the width.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link Integer}
     */
    public Integer getWidth(){
        return configuration.options().width();
    }

    /**
     * Set a path separator.
     * @author Elia
     * @since V. 1.0.0
     * @param separator Requires a separator
     * @return {@link YamlConfigurationOptions}
     */
    @NotNull
    public YamlConfigurationOptions setPathSeparator(char separator){
        YamlConfigurationOptions options = configuration.options().pathSeparator(separator);
        save();
        return options;
    }

    /**
     * Gets the path separator.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link YamlConfigurationOptions#pathSeparator()}
     */
    public char getPathSeparator(){
        return configuration.options().pathSeparator();
    }

    /**
     * Clear all {@link Map}s in the section.
     * @author Elia
     * @since V. 1.0.0
     */
    public void clear(){
        clear(true);
    }

    /**
     * Clear a Map.
     * @author Elia
     * @since V. 1.0.0
     * @param deep Requires a {@link Boolean}
     */
    public void clear(Boolean deep){
        Map<String , Object> configValues = configuration.getValues(deep);
        for (Map.Entry<String , Object> entry : configValues.entrySet()) {
            configuration.set(entry.getKey() , null);
        }
        save();
    }

    /**
     * Clear a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     */
    public void clearPath(String path){
        configuration.set(path , null);
        save();
    }

    /**
     * Create a path.
     * @author Elia
     * @since V. 1.0.0
     * @param section Requires a {@link ConfigurationSection}
     * @param pathName Requires the path name.
     * @return {@link String}
     */
    @NotNull
    public String createPath(ConfigurationSection section , String pathName){
        String string = MemorySection.createPath(section , pathName);
        save();
        return string;
    }

    /**
     * Create a path.
     * @author Elia
     * @since V. 1.0.0
     * @param section Requires a {@link ConfigurationSection}
     * @param pathName Requires the path name.
     * @param relativeTo
     * @return {@link String}
     */
    @NotNull
    public String createPath(ConfigurationSection section , String pathName , ConfigurationSection relativeTo){
        String string = MemorySection.createPath(section , pathName , relativeTo);
        save();
        return string;
    }

    /**
     * Gets the current path
     * @author Elia
     * @since V. 1.0.0
     * @return {@link String}
     */
    @NotNull
    public String getCurrentPath(){
        return configuration.getCurrentPath();
    }

    /**
     * Queries what was set in the path
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isSet(String path){
        return configuration.isSet(path);
    }

    /**
     * Queries a {@link String} has been set.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isString(String path){
        return configuration.isString(path);
    }

    /**
     * Gets a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link SoulConfiguration#getString(String)}
     */
    public String getPath(String path){
        return getString(path);
    }
    /**
     * Gets a {@link String} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link String}
     */
    public String getString(String path){
        return !contains(path) ? null : configuration.getString(path);
    }

    /**
     * Gets a specify {@link String} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param string Requires the string.
     * @return {@link String}
     */
    public String getString(String path , String string){
        return !contains(path) ? null : configuration.getString(path , string);
    }

    /**
     * Create a section in a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link ConfigurationSection}
     */
    @NotNull
    public ConfigurationSection createSection(String path){
        ConfigurationSection section = configuration.createSection(path);
        save();
        return section;
    }

    /**
     * Creates a section as a {@link Map} with two objects in a specified path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param values Requires the values of your Map.
     * @return {@link ConfigurationSection}
     */
    @NotNull
    public ConfigurationSection createSection(String path , Map<? , ?> values){
        ConfigurationSection section = configuration.createSection(path , values);
        save();
        return section;
    }

    /**
     * Queries a configuration section is set in a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isConfigurationSection(String path){
        return configuration.isConfigurationSection(path);
    }

    /**
     * Gets the configuration section on a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link ConfigurationSection}
     */
    @NotNull
    public ConfigurationSection getConfigurationSection(String path){
        return !contains(path) ? null : configuration.getConfigurationSection(path);
    }

    /**
     * Gets the default section.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link ConfigurationSection}
     */
    @NotNull
    public ConfigurationSection getDefaultSection(){
        return configuration.getDefaultSection();
    }

    /**
     * Gets the parents
     * @author Elia
     * @since V. 1.0.0
     * @return {@link ConfigurationSection}
     */
    @NotNull
    public ConfigurationSection getParent(){
        return configuration.getParent();
    }

    /**
     * Set a {@link Object} in the configuration.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires the {@link Object}.
     */
    public void set(String path , Object value){
        configuration.set(path , value);
        save();
    }

    /**
     * Asks if there is anything in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    public Boolean contains(String path){
        return configuration.contains(path);
    }

    /**
     * Gets a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Object}
     */
    @NotNull
    public Object get(String path){
        return !contains(path) ? null : configuration.get(path);
    }

    /**
     * Checks a boolean on the specified path with the specified boolean
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path
     * @param def Requires the boolean
     * @return the boolean of the config or false if not a boolean in the config
     */
    public boolean checkBoolean(String path, boolean def) {
        if (this.isBoolean(path)) {
            boolean b = this.getBoolean(path, def);
            if (b) {
                return true;
            }else return false;
        }return false;
    }

    /**
     * Checks the boolean on a specify path
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path
     * @return the boolean of the config or false if not a boolean in the config
     */
    public boolean checkBoolean(String path) {
        if (this.isBoolean(path)) {
            boolean b = this.getBoolean(path, false);
            if (b) {
                return true;
            }else return false;
        }return false;
    }

    /**
     * Gets a {@link Object} on a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires the {@link Object}.
     * @return {@link Object}
     */
    @NotNull
    public Object get(String path , Object value){
        return !contains(path) ? null : configuration.get(path , value);
    }

    /**
     * Gets a {@link Boolean} in specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires true or false.
     * @return {@link Boolean}
     */
    public Boolean get(String path , boolean value){
        return configuration.contains(path , value);
    }

    /**
     * Gets a {@link Object} on a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires the {@link Object}.
     * @return {@link Object}
     */
    @NotNull
    public Object getObject(String path , Class<Object> value){
        return !contains(path) ? null : configuration.getObject(path , value);
    }

    /**
     * Gets all keys on the section.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link Set<String>}
     */
    @NotNull
    public Set<String> getKeys(){
        return configuration.getKeys(true);
    }

    /**
     * Gets all keys on the section if {@link boolean} true.
     * @author Elia
     * @since V. 1.0.0
     * @param deep Requires true or false.
     * @return {@link Set<String>}
     */
    @NotNull
    public Set<String> getKeys(boolean deep){
        return configuration.getKeys(deep);
    }

    /**
     * Gets all Values.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link SoulConfiguration#getValues(Boolean)}
     */
    @NotNull
    public Map<String , Object> getValues(){
        return getValues(true);
    }

    /**
     * Gets all values if {@link boolean} true.
     * @author Elia
     * @since V. 1.0.0
     * @param deep Requires true or false.
     * @return {@link SoulConfiguration#getValues(Boolean)}
     */
    @NotNull
    public Map<String , Object> getValues(Boolean deep){
        return getValues(deep);
    }

    /**
     * Asks if a boolean was stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    public Boolean isBoolean(String path){
        return !contains(path) ? null : configuration.isBoolean(path);
    }

    /**
     * Gets a specify {@link Boolean} on a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires true or false.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean getBoolean(String path , boolean value){
        return !contains(path) ? null : configuration.getBoolean(path , value);
    }

    /**
     * Gets a boolean list from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<Boolean>}
     */
    @NotNull
    public List<Boolean> getBooleanList(String path) {
        return configuration.getBooleanList(path);
    }

    /**
     * Asks if an {@link int} was stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isInt(String path) {
        return configuration.isInt(path);
    }

    /**
     * Gets all {@link int} in a specify Path
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Integer}
     */
    @NotNull
    public Integer getInt(String path) {
        return !contains(path) ? null : configuration.getInt(path);
    }

    /**
     * Gets a specify {@link int} in specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires a {@link int}.
     * @return {@link Integer}
     */
    @NotNull
    public Integer getInt(String path, int value) {
        return !contains(path) ? null : configuration.getInt(path, value);
    }

    /**
     * Gets a {@link Integer} {@link List} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<Integer>}
     */
    @NotNull
    public List<Integer> getIntegerList(String path) {
        return !contains(path) ? null : configuration.getIntegerList(path);
    }

    /**
     * Asks if a {@link long} was stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isLong(String path) {
        return configuration.isLong(path);
    }

    /**
     * Gets a {@link Long} on a specify path
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Long}
     */
    @NotNull
    public Long getLong(String path) {
        return !contains(path) ? null : configuration.getLong(path);
    }

    /**
     * Gets a specify {@link long} in a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires a {@link long}.
     * @return {@link Long}
     */
    @NotNull
    public Long getLong(String path, long value) {
        return !contains(path) ? null : configuration.getLong(path, value);
    }

    /**
     * Gets a {@link Long} {@link List} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<Long>}
     */
    @NotNull
    public List<Long> getLongList(String path) {
        return !contains(path) ? null : configuration.getLongList(path);
    }

    /**
     * Asks if a {@link Double} was stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Double}
     */
    @NotNull
    public Boolean isDouble(String path) {
        return configuration.isDouble(path);
    }

    /**
     * Gets a {@link Double} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Double}
     */
    @NotNull
    public Double getDouble(String path) {
        return !contains(path) ? null : configuration.getDouble(path);
    }

    /**
     * Gets a specify {@link Double} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires a {@link Double}.
     * @return {@link Double}
     */
    @NotNull
    public Double getDouble(String path, double value) {
        return !contains(path) ? null : configuration.getDouble(path, value);
    }

    /**
     * Gets a {@link List} of {@link Double}s.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<Double>}
     */
    @NotNull
    public List<Double> getDoubleList(String path) {
        return !contains(path) ? null : configuration.getDoubleList(path);
    }

    /**
     * Gets a {@link List} of {@link Float}s
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<Float>}
     */
    @NotNull
    public List<Float> getFloatList(String path) {
        return !contains(path) ? null : configuration.getFloatList(path);
    }

    /**
     * Gets a {@link List} of {@link Short}s.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<Short>}
     */
    @NotNull
    public List<Short> getShortList(String path) {
        return !contains(path) ? null : configuration.getShortList(path);
    }

    /**
     * Gets a {@link List} of {@link Byte}s.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<Byte>}
     */
    @NotNull
    public List<Byte> getByteList(String path) {
        return configuration.getByteList(path);
    }

    /**
     * Gets a {@link List} of {@link Character}.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<Character>}
     */
    @NotNull
    public List<Character> getCharacterList(String path) {
        return configuration.getCharacterList(path);
    }

    /**
     * Asks if a {@link List<?>} was stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isList(String path) {
        return configuration.isList(path);
    }

    /**
     * Gets a {@link List<?>} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link List<?>}
     */
    @NotNull
    public List<?> getList(String path) {
        return !contains(path) ? null : configuration.getList(path);
    }

    /**
     * Gets a specify {@link List<?>} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires the {@link List<?>}.
     * @return {@link List<?>}
     */
    @NotNull
    public List<?> getList(String path, List<?> value) {
        return !contains(path) ? null : (configuration.getList(path, value));
    }

    /**
     * Gets a {@link List} of {@link Map<?, ?>}s from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Map<?, ?>}
     */
    @NotNull
    public List<Map<?, ?>> getMapList(String path) {
        return !contains(path) ? null : configuration.getMapList(path);
    }

    /**
     * Asks if a {@link OfflinePlayer} was stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isOfflinePlayer(String path) {
        return configuration.isOfflinePlayer(path);
    }

    /**
     * Gets a {@link OfflinePlayer}.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link OfflinePlayer}
     */
    @NotNull
    public OfflinePlayer getOfflinePlayer(String path) {
        return !contains(path) ? null : configuration.getOfflinePlayer(path);
    }

    /**
     * Gets a specify {@link OfflinePlayer} in a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param def Requires the {@link OfflinePlayer}
     * @return {@link OfflinePlayer}
     */
    @NotNull
    public OfflinePlayer getOfflinePlayer(String path, OfflinePlayer def) {
        return !contains(path) ? null : configuration.getOfflinePlayer(path, def);
    }

    /**
     * Asks if a {@link ItemStack} was stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isItemStack(String path) {
        return configuration.isItemStack(path);
    }

    /**
     * Gets a {@link ItemStack} on a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link ItemStack}
     */
    @NotNull
    public ItemStack getItemStack(String path) {
        return !contains(path) ? null : configuration.getItemStack(path);
    }

    /**
     * Gets a specify {@link ItemStack} on a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires the {@link ItemStack}.
     * @return {@link ItemStack}
     */
    @NotNull
    public ItemStack getItemStack(String path, ItemStack value) {
        return !contains(path) ? null : (configuration.getItemStack(path, value));
    }

    /**
     * Asks if a {@link Location} was stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isLocation(String path) {
        return configuration.isLocation(path);
    }

    /**
     * Gets a {@link Location} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Location}
     */
    @NotNull
    public Location getLocation(String path) {
        return !contains(path) ? null : configuration.getLocation(path);
    }

    /**
     * Gets a specify {@link Location} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param location Requires the {@link Location}.
     * @return {@link Location}
     */
    @NotNull
    public Location getLocation(String path, Location location) {
        return !contains(path) ? null : configuration.getLocation(path, location);
    }

    /**
     * Asks if a {@link Vector} was stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isVector(String path) {
        return configuration.isVector(path);
    }

    /**
     *  Gets a {@link Vector} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Vector}
     */
    @NotNull
    public Vector getVector(String path) {
        return !contains(path) ? null : configuration.getVector(path);
    }

    /**
     * Gets a specify {@link Vector} from a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires the {@link Vector}
     * @return {@link Vector}
     */
    @NotNull
    public Vector getVector(String path, Vector value) {
        return !contains(path) ? null : configuration.getVector(path, value);
    }

    /**
     * Queries if a color is stored in the path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @return {@link Boolean}
     */
    @NotNull
    public Boolean isColor(String path) {
        return configuration.isColor(path);
    }

    /**
     * Gets the colors in a specify path.
     * @author Elia
     * @since V. 1.0.0
     * @param path Requires the path.
     * @param value Requires the color.
     * @return {@link Color}
     */
    @NotNull
    public Color getColor(String path, Color value) {
        return !contains(path) ? null : (configuration.getColor(path, value));
    }

    /**
     * Gets the configuration name.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link SoulConfiguration#getConfigurationName()}
     */
    @NotNull
    public String getName() {
        return getConfigurationName();
    }

    /**
     * Gets the configuration name.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link YamlConfiguration#getName()}
     */
    @NotNull
    public String getConfigurationName() {
        return configuration.getName();
    }

    /**
     * Gets the options from the configuration.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link SoulConfiguration#getOptions()}
     */
    @NotNull
    public FileConfigurationOptions options() {
        return getOptions();
    }

    /**
     * Gets the options from the configuration.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link YamlConfiguration#options()}
     */
    @NotNull
    public YamlConfigurationOptions getOptions() {
        return configuration.options();
    }

    /**
     * Gets the file.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link File}
     */
    @NotNull
    public File getFile() {
        return file;
    }

    /**
     * Gets the file path.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link String}
     */
    @NotNull
    public File getFilepath() {
        return filepath;
    }

    /**
     * Gets the file name.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link String}
     */
    @NotNull
    public String getFileName() {
        return fileName;
    }

    /**
     * Gets the path name.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link String}
     */
    @NotNull
    public String getPathName() {
        return pathName;
    }

    /**
     * Gets a configuration.
     * @author Elia
     * @since V. 1.0.0
     * @return {@link YamlConfiguration}
     */
    @NotNull
    public YamlConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * Sets a configuration.
     * @author Elia
     * @since V. 1.0.0
     * @param configuration Requires the configuration
     */
    public void setConfiguration(YamlConfiguration configuration) {
        this.configuration = configuration;
    }
}
