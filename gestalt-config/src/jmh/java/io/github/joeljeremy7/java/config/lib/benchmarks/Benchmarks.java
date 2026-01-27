package io.github.joeljeremy7.java.config.lib.benchmarks;

import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.exceptions.GestaltException;
import org.github.gestalt.config.source.ClassPathConfigSourceBuilder;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.util.concurrent.TimeUnit;

public abstract class Benchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private Gestalt gestalt;
        private String test1Key;
        private String testInt1Key;

        @Setup
        public void setup() throws GestaltException {
            this.test1Key = "test1";
            this.testInt1Key = "testInt1";

            this.gestalt = new GestaltBuilder()
                .addDefaultConfigLoaders()
                .addDefaultDecoders()
                .addDefaultPostProcessors()
                .addSource(ClassPathConfigSourceBuilder.builder().setResource("/AppProps.properties").build())
                .build();
            this.gestalt.loadConfigs();
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Avgt extends Benchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {}

    @Benchmark
    public String GestaltConfig_String(BenchmarkState state) throws GestaltException {
        return state.gestalt.getConfig(state.test1Key, String.class);
    }

    @Benchmark
    public int GestaltConfig_Int(BenchmarkState state) throws GestaltException {
        return state.gestalt.getConfig(state.testInt1Key, int.class);
    }
}
