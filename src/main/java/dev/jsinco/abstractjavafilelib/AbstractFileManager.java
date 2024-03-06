package dev.jsinco.abstractjavafilelib;

import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public abstract class AbstractFileManager extends ConfigurationSection {

    protected final static File dataFolder = FileLibSettings.getDataFolder();
    protected final static Logger logger = FileLibSettings.getLogger();

    protected final File file;


    public AbstractFileManager(File file) {
        this.file = file;
    }


    public AbstractFileManager generateFile() {
        if (!file.exists()) {
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file.getName())) {
                file.getParentFile().mkdirs();
                file.createNewFile();

                if (inputStream != null) {
                    OutputStream outputStream = Files.newOutputStream(file.toPath());
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.flush();
                    outputStream.close();
                }

            } catch (IOException e) {
                logger.error("Error creating file: " + e.getMessage());
            }
        }
        return loadFile();
    }

    public abstract AbstractFileManager loadFile();

    public abstract AbstractFileManager loadImaginaryFile();

    public abstract void save();

}

