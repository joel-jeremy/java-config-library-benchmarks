package io.github.joeljeremy7.java.config.lib.benchmarks;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public abstract class Benchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private ConfigurationProvider configurationProvider;
        private AppProps appProps;
        private String test1Key = "test1";
        private String testInt1Key = "testInt1";

        @Setup
        public void setup() {
            this.configurationProvider = initCf4jConfigurationProvider();
            this.appProps = configurationProvider.bind("", AppProps.class);
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

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Avgt extends Benchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {}

    @Benchmark
    public String Cfg4j_String(BenchmarkState state) {
        return state.appProps.test1();
    }

    @Benchmark
    public int Cfg4j_Int(BenchmarkState state) {
        return state.appProps.testInt1();
    }

    @Benchmark
    public String Cfg4j_GetProperty_String(BenchmarkState state) {
        return state.configurationProvider.getProperty(state.test1Key, String.class);
    }

    @Benchmark
    public int Cfg4j_GetProperty_Int(BenchmarkState state) {
        return state.configurationProvider.getProperty(state.testInt1Key, int.class);
    }
}
