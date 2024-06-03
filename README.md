# A file library for JVM-based applications.

## Currently supported: YAML, JSON, TOML
"SnakeYamlConfig.class"
"JsonSavingSchema.class"
"TOMLConfig.class"

----
Gradle:
```kts
repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("com.github.Jsinco:AbstractJavaFileLib:2.1")
}
```
---


### Example:
```java
        // Set the folder where we want our files to be generated/read from. If none is set, we'll default to the jar's directory
        // Setting a logger is optional.
        FileLibSettings.set(this.getDataFolder(), this.getLogger());

        // Let's create a new file in our directory
        // This will create a new file in the data folder with the name "config.yml"
        // if the file is also found in jar resources it's contents will be written to the file
        final SnakeYamlConfig yamlConfig = new SnakeYamlConfig("config.yml");

        // Let's get a value from the file
        String value = yamlConfig.getString("example-key");
        System.out.println(value); // Output: "example-value" from the yaml file

        // Let's set a value in the file
        yamlConfig.set("example-key", "new-value");
        yamlConfig.save(); // Save the changes to the file

        // Let's get a value from a section
        String sectionValue = yamlConfig.getConfigurationSection("example-section").getString("example-key");
        // OR
        sectionValue = yamlConfig.getString("example-section.example-key");
        
        // Let's iterate over all the values in a certain section
        ConfigurationSection configurationSection = yamlConfig.getConfigurationSection("example-section");
        assert configurationSection != null; // ConfigurationSections CAN be null if they do not exist!
        for (String key : configurationSection.getKeys()) {
            System.out.println(key + ": " + configurationSection.get(key));
        }
        
        
        // Let's get a section from inside a section!
        ConfigurationSection section = yamlConfig.getConfigurationSection("example-section.another-section");
        assert section != null;
        System.out.println(section.getString("example-key-in-another-section"));
        
        // This library will work the same for every schema (Json, TOML, Yaml, and future schemas!)
```
