package io.github.joeljeremy7.java.config.lib.benchmarks;

import io.smallrye.config.PropertiesConfigSource;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class Benchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private Config config;
        private String test1Key;
        private String testInt1Key;

        @Setup
        public void setup() throws IOException {
            this.test1Key = "test1";
            this.testInt1Key = "testInt1";

            this.config = ConfigProviderResolver.instance()
                .getBuilder()
                .addDefaultSources()
                .addDiscoveredSources()
                .addDiscoveredConverters()
                .withSources(new PropertiesConfigSource(
                    getClass().getResource("/AppProps.properties")
                ))
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
    public String MP_SmallRye_String(BenchmarkState state) {
        return state.config.getValue(state.test1Key, String.class);
    }

    @Benchmark
    public int MP_SmallRye_Int(BenchmarkState state) {
        return state.config.getValue(state.testInt1Key, int.class);
    }
}
