package dev.jsinco.abstractjavafilelib;

import com.google.gson.internal.LinkedTreeMap;

import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
public class ConfigurationSection {

    protected AbstractMap<String, Object> data;

    public ConfigurationSection() {
    }

    public ConfigurationSection(AbstractMap<String, Object> data) {
        this.data = data;
    }

    public List<String> getKeys() {
        return new ArrayList<>(data.keySet());
    }

    public boolean contains(String key) {
        return data.containsKey(key);
    }


    public ConfigurationSection getConfigurationSection(String key) {
        data.computeIfAbsent(key, k -> new LinkedHashMap<String, Object>());
        Object object = data.get(key);

        if (object instanceof LinkedHashMap<?, ?>) { // SNAKEYAML + GSON
            return new ConfigurationSection((LinkedHashMap<String, Object>) object);
        } else if (object instanceof LinkedTreeMap<?, ?>) { // GSON
            return new ConfigurationSection((LinkedTreeMap<String, Object>) object);
        } else if (object instanceof HashMap<?,?>) {
            return new ConfigurationSection((HashMap<String, Object>) object);
        }
        return new ConfigurationSection((AbstractMap<String, Object>) object); // UNKNOWN
    }

    private Object getLastFromSection(String path) {
        List<String> keys = new ArrayList<>(List.of(path.split("\\.")));
        String lastKey = keys.remove(keys.size() - 1);
        ConfigurationSection section = this;
        for (String key : keys) {
            section = section.getConfigurationSection(key);
        }
        return section.data.get(lastKey);
    }

    private void setLastInSection(String path, Object value) {
        List<String> keys = new ArrayList<>(List.of(path.split("\\.")));
        String lastKey = keys.remove(keys.size() - 1);
        ConfigurationSection section = this;
        for (String key : keys) {
            section = section.getConfigurationSection(key);
        }
        section.data.put(lastKey, value);
    }


    public String getString(String path) {
        return (String) getLastFromSection(path);
    }

    public int getInt(String path) {
        return (int) getLastFromSection(path);
    }

    public boolean getBoolean(String path) {
        return (boolean) getLastFromSection(path);
    }

    public double getDouble(String path) {
        return (double) getLastFromSection(path);
    }

    public long getLong(String path) {
        return (long) getLastFromSection(path);
    }

    public float getFloat(String path) {
        return (float) getLastFromSection(path);
    }

    public byte getByte(String path) {
        return (byte) getLastFromSection(path);
    }

    public short getShort(String path) {
        return (short) getLastFromSection(path);
    }


    public List<String> getStringList(String path) {
        if (data.get(path) instanceof AbstractList<?>) {
            return (AbstractList<String>) data.get(path);
        }
        return Collections.emptyList();
    }
    public List<Integer> getIntList(String path) {
        if (data.get(path) instanceof AbstractList<?>) {
            return (AbstractList<Integer>) data.get(path);
        }
        return Collections.emptyList();
    }
    public List<Double> getDoubleList(String path) {
        if (data.get(path) instanceof AbstractList<?>) {
            return (AbstractList<Double>) data.get(path);
        }
        return Collections.emptyList();
    }
    public List<Long> getLongList(String path) {
        if (data.get(path) instanceof AbstractList<?>) {
            return (AbstractList<Long>) data.get(path);
        }
        return Collections.emptyList();
    }
    public List<Float> getFloatList(String path) {
        if (data.get(path) instanceof AbstractList<?>) {
            return (AbstractList<Float>) data.get(path);
        }
        return Collections.emptyList();
    }

    public Object get(String path) {
        return getLastFromSection(path);
    }

    public void set(String path, Object value) {
        setLastInSection(path, value);
    }

    public void remove(String path) {
        setLastInSection(path, null);
    }
}