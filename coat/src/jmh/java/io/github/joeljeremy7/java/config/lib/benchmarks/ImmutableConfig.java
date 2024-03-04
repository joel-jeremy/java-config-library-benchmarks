package io.github.joeljeremy7.java.config.lib.benchmarks;

import de.poiu.coat.CoatConfig;
import de.poiu.coat.c14n.KeyC14n;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.System;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import javax.annotation.processing.Generated;

@Generated(
    value = "de.poiu.coat.processor.codegeneration.CodeGenerator",
    date = "2024-03-04T22:29:49.753072188+01:00"
)
public class ImmutableConfig extends CoatConfig implements Config {
  private static final System.Logger LOGGER = System.getLogger(ImmutableConfig.class.getName());

  private ImmutableConfig(final Map<String, String> props) {
    super(ConfigParam.values());

    this.add(props);
  }

  /**
   * Create a new ImmutableConfig from the given config entries.
   *
   * @param props the config entries
   * @return the ImmutableConfig created with the given entries
   */
  public static ImmutableConfig from(final Map<String, String> props) {
    return new ImmutableConfig(props);
  }

  /**
   * Create a new ImmutableConfig from the given config file.
   *
   * @param file the config file to read
   * @return the ImmutableConfig created with the entries from the given file
   * @throws IOException if reading the given file failed
   */
  public static ImmutableConfig from(final File file) throws IOException {
    return new ImmutableConfig(toMap(file));
  }

  /**
   * Create a new ImmutableConfig from the given config entries.
   *
   * @param jup the config entries
   * @return the ImmutableConfig created with the given entries
   */
  public static ImmutableConfig from(final Properties jup) {
    return new ImmutableConfig(toMap(jup));
  }

  /**
   * Create a new ImmutableConfig from the current environment variables.
   * <p>
   * Since the allowed characters for environment variables are much more restricted than Coat config keys,
   * a relaxed mapping is applied.
   * <p>Dots and hyphens are treated as underscores. Also uppercase
   * characters in config keys are preceded by an underscore (to convert camelCase to UPPER_CASE).
   * Comparison between the environment variables and the config keys is done case insensitively.<p>
   * For example the environment variable
   * <code>SERVER_MQTT_HOST</code> will match the config key <code>server.mqttHost</code>.
   *
   * @return the ImmutableConfig created with the entries in the current environment variables
   */
  public static ImmutableConfig fromEnvVars() {
    return builder().addEnvVars().build();
  }

  @Override
  public String test1() {
    return super.get(ConfigParam.TEST1);
  }

  @Override
  public int testInt1() {
    return super.getInt(ConfigParam.TEST_INT1);
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    final ImmutableConfig other = (ImmutableConfig) obj;

    if (!Objects.equals(this.test1(), other.test1())) {
      return false;
    }

    if (!Objects.equals(this.testInt1(), other.testInt1())) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return java.util.Objects.hash(
        test1(),
        testInt1());
  }

  /**
   * Write an example config file to the given Writer.
   *
   * @param writer the Writer to write to
   * @throws IOException if writing the example config file fails
   */
  public static void writeExampleConfig(final Writer writer) throws IOException {
    writer.append("test1 = \n"
            + "\n"
            + "testInt1 = \n"
            + "\n");
    writer.flush();
  }

  /**
   * Create a builder for {@link ImmutableConfig} instances.
   * <p>
   * Call the <code>add</code> and/or <code>addEnvVars</code> methods for specifying the config
   * sources (and the order in which they are applied), then call {@link Builder#build()} to create the
   * ImmutableConfig
   *
   * @return an new ImmutableConfig builder
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Builder class for creating new {@link ImmutableConfig} instances.
   * <p>
   * Call the <code>add</code> and/or <code>addEnvVars</code> methods for specifying the config
   * sources (and the order in which they are applied), then call {@link Builder#build()} to create the
   * ImmutableConfig
   */
  public static class Builder {
    private final Map<String, String> props = new HashMap<>();

    /**
     * Add the config entries from the given Map to the built ImmutableConfig.
     * Already existing config entries with the same keys will be overwritten.
     *
     * @param map the config entries to add
     * @return this Builder
     */
    public Builder add(final Map<String, String> map) {
      this.props.putAll(map);
      return this;
    }

    /**
     * Add the config entries from the given file to the built ImmutableConfig.
     * Already existing config entries with the same keys will be overwritten.
     *
     * @param file the file with the config entries to add
     * @return this Builder
     * @throws java.io.IOException if reading the config file failed
     */
    public Builder add(final File file) throws IOException {
      this.props.putAll(toMap(file));
      return this;
    }

    /**
     * Add the config entries from the given Properties to the built ImmutableConfig.
     * Already existing config entries with the same keys will be overwritten.
     *
     * @param jup the config entries to add
     * @return this Builder
     */
    public Builder add(final Properties jup) {
      this.props.putAll(toMap(jup));
      return this;
    }

    /**
     * Add the config entries from the current environment variables to the built ImmutableConfig.
     * Already existing config entries with the same keys will be overwritten.
     * <p>
     * Since the allowed characters for environment variables are much more restricted than Coat config keys,
     * a relaxed mapping is applied.
     * <p>Dots and hyphens are treated as underscores. Also uppercase
     * characters in config keys are preceded by an underscore (to convert camelCase to UPPER_CASE).
     * Comparison between the environment variables and the config keys is done case insensitively.<p>
     * For example the environment variable
     * <code>SERVER_MQTT_HOST</code> will match the config key <code>server.mqttHost</code>.
     * @return this Builder
     */
    public Builder addEnvVars() {
      final Map<String, String> envVars= System.getenv();
      final String[] configKeys= {
          "test1",
          "testInt1"
          };

      for (final String envVar : envVars.keySet()) {
        for (final String configKey : configKeys) {
          if (envVar.toUpperCase().equals(KeyC14n.c14n(configKey))) {
            LOGGER.log(System.Logger.Level.INFO, "Using environment variable {0} as config key {1}", new Object[]{envVar, configKey});
            this.props.put(configKey, envVars.get(envVar));
          }
        }
      }

      return this;
    }

    /**
     * Build a new {@link ImmutableConfig} with the config keys from this Builder.
     *
     * @return a new ImmutableConfig
     */
    public ImmutableConfig build() {
      return new ImmutableConfig(this.props);
    }
  }
}
