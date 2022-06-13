package io.github.joeljeremy7.java.config.lib.benchmarks.microprofile.geronimo;

import org.apache.geronimo.config.configsource.PropertyFileConfigSource;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

public abstract class MicroProfileGeronimoBenchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private Config config;

        @Setup
        public void setup() {
            this.config = ConfigProviderResolver.instance()
                .getBuilder()
                .addDefaultSources()
                .withSources(new PropertyFileConfigSource(
                    getClass().getResource("/AppProps.properties")
                ))
                .build();
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class MicroProfileGeronimoAvgt extends MicroProfileGeronimoBenchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class MicroProfileGeronimoThrpt extends MicroProfileGeronimoBenchmarks {}

    @Benchmark
    public String stringProperty(BenchmarkState state) {
        return state.config.getValue("test1", String.class);
    }

    @Benchmark
    public int intProperty(BenchmarkState state) {
        return state.config.getValue("testInt1", int.class);
    }
}
