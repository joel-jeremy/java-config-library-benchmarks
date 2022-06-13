package io.github.joeljeremy7.java.config.lib.benchmarks.microprofile.smallrye;

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

public abstract class MicroProfileSmallRyeBenchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private Config config;

        @Setup
        public void setup() throws IOException {
            this.config = ConfigProviderResolver.instance()
                .getBuilder()
                .addDefaultSources()
                .withSources(new PropertiesConfigSource(
                    getClass().getResource("/AppProps.properties")
                ))
                .build();
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class MicroProfileSmallRyeAvgt extends MicroProfileSmallRyeBenchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class MicroProfileSmallRyeThrpt extends MicroProfileSmallRyeBenchmarks {}

    @Benchmark
    public String stringProperty(BenchmarkState state) {
        return state.config.getValue("test1", String.class);
    }

    @Benchmark
    public int intProperty(BenchmarkState state) {
        return state.config.getValue("testInt1", int.class);
    }
}
