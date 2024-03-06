package dev.jsinco.abstractjavafilelib;

import org.slf4j.Logger;

import java.io.File;

public abstract class AbstractFileManager extends ConfigurationSection {

    protected final static File dataFolder = FileLibSettings.getDataFolder();
    protected final static Logger logger = FileLibSettings.getLogger();

    protected final File file;


    public AbstractFileManager(File file) {
        this.file = file;
    }


    public abstract AbstractFileManager generateFile();

    public abstract AbstractFileManager loadFile();

    public abstract AbstractFileManager loadImaginaryFile();

    public abstract void save();

}

