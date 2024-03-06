package dev.jsinco.abstractjavafilelib;


import java.io.File;
import java.util.logging.Logger;

public abstract class FileLibSettings {

    private static File dataFolder;
    private static LibLogger libLogger;

    public static void set(File dataFolder, Logger logger) {
        FileLibSettings.dataFolder = dataFolder;
        libLogger = new LibLogger(logger);
    }

    public static void set(File dataFolder, org.slf4j.Logger logger) {
        FileLibSettings.dataFolder = dataFolder;
        libLogger = new LibLogger(logger);
    }

    public static File getDataFolder() {
        if (dataFolder == null) {
            String path = AbstractFileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            File jarFile = new File(path);
            String jarDir = jarFile.getParentFile().getAbsolutePath();

            dataFolder = new File(jarDir + File.separator + "data");
            dataFolder.mkdirs();
        }
        return dataFolder;
    }

    public static LibLogger getLogger() {
        if (libLogger == null) {
            libLogger = new LibLogger(Logger.getGlobal());
        }
        return libLogger;
    }
}