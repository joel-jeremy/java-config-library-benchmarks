package io.github.joeljeremy7.java.config.lib.benchmarks;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PropertiesFileConfigSource implements ConfigSource {

    private final Map<String, String> properties;

    public PropertiesFileConfigSource(URL propertiesFileUrl) throws IOException {
        Properties props = new Properties();
        props.load(propertiesFileUrl.openStream());
        this.properties = props.stringPropertyNames()
            .stream()
            .collect(Collectors.toMap(Function.identity(), props::getProperty));
    }

    @Override
    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

    @Override
    public Map<String, String> getProperties() {
        return properties;
    }

    @Override
    public String getValue(String propertyName) {
        return properties.get(propertyName);
    }

    @Override
    public String getName() {
        return "props";
    }
}
