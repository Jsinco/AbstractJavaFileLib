package dev.jsinco.abstractjavafilelib;

import com.google.gson.internal.LinkedTreeMap;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractList;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    @Nullable
    private Object getLastFromSection(String path) {
        List<String> keys = new ArrayList<>(List.of(path.split("\\.")));
        String lastKey = keys.remove(keys.size() - 1);
        ConfigurationSection section = this;
        for (String key : keys) {
            section = section.getConfigurationSection(key);
        }
        return Map.copyOf(section.data).get(lastKey);
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

    @Nullable
    public String getString(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof String) {
            return (String) object;
        }
        return null;
    }

    public int getInt(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof Integer) {
            return (int) object;
        }
        return 0;
    }

    public boolean getBoolean(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof Boolean) {
            return (boolean) object;
        }
        return false;
    }

    public double getDouble(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof Double) {
            return (double) object;
        }
        return 0.0;
    }

    public long getLong(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof Long) {
            return (long) object;
        }
        return 0L;
    }

    public float getFloat(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof Float) {
            return (float) object;
        }
        return 0.0f;
    }

    public byte getByte(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof Byte) {
            return (byte) object;
        }
        return 0;
    }

    public short getShort(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof Short) {
            return (short) object;
        }
        return 0;
    }


    public List<String> getStringList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<String>) object;
        }
        return Collections.emptyList();
    }
    public List<Integer> getIntList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Integer>) object;
        }
        return Collections.emptyList();
    }
    public List<Double> getDoubleList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Double>) object;
        }
        return Collections.emptyList();
    }
    public List<Long> getLongList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Long>) object;
        }
        return Collections.emptyList();
    }
    public List<Float> getFloatList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Float>) object;
        }
        return Collections.emptyList();
    }
    public List<Object> getObjectList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Object>) object;
        }
        return Collections.emptyList();
    }

    @Nullable
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