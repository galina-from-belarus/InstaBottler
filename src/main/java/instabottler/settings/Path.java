/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instabottler.settings;

/**
 *
 * @author Galina Benedictovich
 */
public class Path
{
    public static final String APP_NAME = "InstaBottler";
    
    public static final String SEPARATOR = System.getProperty("file.separator");
    public static final String APP_DIR = System.getProperty("user.dir").replace("bin", "") + SEPARATOR;
    
    
    public static final String SETTINGS_PATH = APP_DIR + "settings" + SEPARATOR;
    public static final String DATA_PATH = "data" + SEPARATOR;
    public static final String OUTPUT_PATH = APP_DIR + "output" + SEPARATOR;
    
    public static final String DATA_ALL_FILE = DATA_PATH + FilesSettings.ALL_FILENAME + "." + FilesSettings.OUTPUT_FILE_EXTENSION;
    public static final String DATA_CLEAR_FILE = DATA_PATH + FilesSettings.CLEAR_FILENAME + "." + FilesSettings.OUTPUT_FILE_EXTENSION;
    public static final String DATA_BOT_FILE = DATA_PATH + FilesSettings.BOTS_FILENAME + "." + FilesSettings.OUTPUT_FILE_EXTENSION;
    public static final String DATA_SUSPICIOUS_FILE = DATA_PATH + FilesSettings.SUSPICIOUS_FILENAME + "." + FilesSettings.OUTPUT_FILE_EXTENSION;
 
    public static final String OUTPUT_ALL_FILE = OUTPUT_PATH + FilesSettings.ALL_FILENAME + "." + FilesSettings.OUTPUT_FILE_EXTENSION;
    public static final String OUTPUT_CLEAR_FILE = OUTPUT_PATH + FilesSettings.CLEAR_FILENAME + "." + FilesSettings.OUTPUT_FILE_EXTENSION;
    public static final String OUTPUT_BOT_FILE = OUTPUT_PATH + FilesSettings.BOTS_FILENAME + "." + FilesSettings.OUTPUT_FILE_EXTENSION;
    public static final String OUTPUT_SUSPICIOUS_FILE = OUTPUT_PATH + FilesSettings.SUSPICIOUS_FILENAME + "." + FilesSettings.OUTPUT_FILE_EXTENSION;
 
    public static final String GENERAL = SETTINGS_PATH + "general.txt";
    public static final String OUTPUT = SETTINGS_PATH + "output.txt";
    public static final String TARGET = SETTINGS_PATH + "target.txt";
}
