package dev.jsinco.abstractjavafilelib;

import com.google.gson.internal.LinkedTreeMap;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

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
        return getLastFromSection(key) != null;
    }

    @Nullable
    public ConfigurationSection getConfigurationSection(String key) {
        return getConfigurationSection(key, false);
    }

    @Contract("_, true -> !null")
    public ConfigurationSection getConfigurationSection(String key, boolean create) {
        if (!data.containsKey(key)) {
            if (create) {
                data.computeIfAbsent(key, k -> new LinkedHashMap<String, Object>());
            } else {
                return null;
            }
        }
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
            if (section == null) {
                return null;
            }
            section = section.getConfigurationSection(key);
        }
        if (section == null) {
            return null;
        }
        return section.data.get(lastKey);
    }

    private void setLastInSection(String path, Object value) {
        List<String> keys = new ArrayList<>(List.of(path.split("\\.")));
        String lastKey = keys.remove(keys.size() - 1);
        ConfigurationSection section = this;
        for (String key : keys) {
            section = section.getConfigurationSection(key, true);
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


    //


    public String getString(String path, String defaultTo) {
        final Object object = getLastFromSection(path);
        if (object instanceof String) {
            return (String) object;
        }
        return defaultTo;
    }

    public int getInt(String path, int defaultTo) {
        final Object object = getLastFromSection(path);
        if (object instanceof Integer) {
            return (int) object;
        }
        return defaultTo;
    }

    public boolean getBoolean(String path, boolean defaultTo) {
        final Object object = getLastFromSection(path);
        if (object instanceof Boolean) {
            return (boolean) object;
        }
        return defaultTo;
    }

    public double getDouble(String path, double defaultTo) {
        final Object object = getLastFromSection(path);
        if (object instanceof Double) {
            return (double) object;
        }
        return defaultTo;
    }

    public long getLong(String path, long defaultTo) {
        final Object object = getLastFromSection(path);
        if (object instanceof Long) {
            return (long) object;
        }
        return defaultTo;
    }

    public float getFloat(String path, float defaultTo) {
        final Object object = getLastFromSection(path);
        if (object instanceof Float) {
            return (float) object;
        }
        return defaultTo;
    }

    public byte getByte(String path, byte defaultTo) {
        final Object object = getLastFromSection(path);
        if (object instanceof Byte) {
            return (byte) object;
        }
        return defaultTo;
    }

    public short getShort(String path, short defaultTo) {
        final Object object = getLastFromSection(path);
        if (object instanceof Short) {
            return (short) object;
        }
        return defaultTo;
    }

    //

    public List<String> getStringList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<String>) object;
        } else if (object instanceof List<?>) {
            return (List<String>) object;
        }
        return Collections.emptyList();
    }
    public List<Integer> getIntList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Integer>) object;
        } else if (object instanceof List<?>) {
            return (List<Integer>) object;
        }
        return Collections.emptyList();
    }
    public List<Double> getDoubleList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Double>) object;
        } else if (object instanceof List<?>) {
            return (List<Double>) object;
        }
        return Collections.emptyList();
    }
    public List<Long> getLongList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Long>) object;
        } else if (object instanceof List<?>) {
            return (List<Long>) object;
        }
        return Collections.emptyList();
    }
    public List<Float> getFloatList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Float>) object;
        } else if (object instanceof List<?>) {
            return (List<Float>) object;
        }
        return Collections.emptyList();
    }
    public List<Object> getObjectList(String path) {
        final Object object = getLastFromSection(path);
        if (object instanceof AbstractList<?>) {
            return (AbstractList<Object>) object;
        } else if (object instanceof List<?>) {
            return (List<Object>) object;
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