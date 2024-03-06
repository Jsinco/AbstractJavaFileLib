package dev.jsinco.abstractjavafilelib.schemas;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import dev.jsinco.abstractjavafilelib.AbstractFileManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TomlConfig extends AbstractFileManager {

    private final Toml toml = new Toml();

    public TomlConfig(String fileName) {
        this(new File(dataFolder, fileName), false);
    }

    public TomlConfig(String fileName, boolean isImaginary) {
        this(new File(dataFolder, fileName), isImaginary);
    }

    public TomlConfig(File file, boolean isImaginary) {
        super(file);
        if (isImaginary) {
            loadImaginaryFile();
        } else {
            generateFile();
        }
    }

    @Override
    public TomlConfig loadFile() {
        if (isToml(file)) {
            data = (HashMap<String, Object>) toml.read(file).toMap();
        }
        if (data == null) {
            data = new LinkedHashMap<>();
        }
        return this;
    }

    @Override
    public AbstractFileManager loadImaginaryFile() {
        if (isToml(file)) {
            try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file.getName())) {
                data = (HashMap<String, Object>) toml.read(inputStream).toMap();
                if (data == null) {
                    data = new LinkedHashMap<>();
                }
            } catch (IOException e) {
                logger.error("Error loading imaginary file: " + e.getMessage());
            }
        }
        return this;
    }

    @Override
    public void save() {
        TomlWriter writer = new TomlWriter();
        try {
            writer.write(data, file);
        } catch (IOException e) {
            logger.error("Error saving file: " + e.getMessage());
        }
    }

    public static boolean isToml(File file) {
        return file.isFile() && file.getName().endsWith(".toml");
    }
}
