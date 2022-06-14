package io.github.joeljeremy7.java.config.lib.benchmarks;

import org.github.gestalt.config.Gestalt;
import org.github.gestalt.config.builder.GestaltBuilder;
import org.github.gestalt.config.exceptions.GestaltException;
import org.github.gestalt.config.source.ClassPathConfigSource;
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

        @Setup
        public void setup() throws GestaltException {
            this.gestalt = new GestaltBuilder()
                .addDefaultConfigLoaders()
                .addDefaultDecoders()
                .addDefaultPostProcessors()
                .addSource(new ClassPathConfigSource("/AppProps.properties"))
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
        return state.gestalt.getConfig("test1", String.class);
    }

    @Benchmark
    public int GestaltConfig_Int(BenchmarkState state) throws GestaltException {
        return state.gestalt.getConfig("testInt1", int.class);
    }
}
