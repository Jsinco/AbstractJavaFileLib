package dev.jsinco.abstractjavafilelib;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public abstract class FileLibSettings {

    private static File dataFolder;
    private static Logger logger;

    public static void set(File dataFolder, Logger logger) {
        FileLibSettings.dataFolder = dataFolder;
        FileLibSettings.logger = logger;
    }

    public static File getDataFolder() {
        if (dataFolder == null) {
            String path = AbstractFileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            File jarFile = new File(path);
            String jarDir = jarFile.getParentFile().getAbsolutePath();

            dataFolder = new File(jarDir);
        }
        return dataFolder;
    }

    public static Logger getLogger() {
        if (logger == null) {
            logger = LoggerFactory.getLogger(AbstractFileManager.class);
        }
        return logger;
    }
}