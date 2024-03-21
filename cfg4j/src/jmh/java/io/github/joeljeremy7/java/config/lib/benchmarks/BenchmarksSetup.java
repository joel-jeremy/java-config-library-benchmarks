package io.github.joeljeremy7.java.config.lib.benchmarks;

import io.github.joeljeremy7.java.config.lib.benchmarks.Benchmarks.BenchmarkState;
import java.nio.file.Paths;
import java.util.Arrays;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;

public abstract class BenchmarksSetup {

    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class InitialCreation extends BenchmarksSetup {}



    @Benchmark
    public AppProps Cfg4j_Creation(BenchmarkState state) {
      ConfigurationProvider configurationProvider = initCf4jConfigurationProvider();
      return configurationProvider.bind("", AppProps.class);
    }

    private static ConfigurationProvider initCf4jConfigurationProvider() {
      ConfigFilesProvider configFilesProvider =
        () -> Arrays.asList(Paths.get("AppProps.properties"));

      // Use local files as configuration store
      ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);

      // Use relative paths
      Environment environment = new ImmutableEnvironment("./build/resources/jmh/");
      return new ConfigurationProviderBuilder()
        .withConfigurationSource(source)
        .withEnvironment(environment)
        .build();
    }
}
