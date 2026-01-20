package io.github.joeljeremy7.java.config.lib.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class Benchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private Environment env;
        private String test1Key = "test1";
        private String testInt1Key = "testInt1";

        @Setup
        public void setup() throws IOException {
            ConfigurableEnvironment env = new StandardEnvironment();
            env.getPropertySources().addFirst(
                new PropertiesPropertySource("appProps", appProps())
            );
            this.env = env;
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Avgt extends Benchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {}

    @Benchmark
    public String SpringCore_String(BenchmarkState state) {
        return state.env.getProperty(state.test1Key);
    }

    @Benchmark
    public int SpringCore_Int(BenchmarkState state) {
        return state.env.getProperty(state.testInt1Key, int.class);
    }

    private static Properties appProps() throws IOException {
        Properties props = new Properties();
        props.load(Benchmarks.class.getResourceAsStream("/AppProps.properties"));
        return props;
    }
}
