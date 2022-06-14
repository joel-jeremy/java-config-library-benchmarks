package io.github.joeljeremy7.java.config.lib.benchmarks;

import org.aeonbits.owner.ConfigFactory;
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
        private AppProps appProps;

        @Setup
        public void setup() {
            this.appProps = ConfigFactory.create(AppProps.class);
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Avgt extends Benchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {}

    @Benchmark
    public String Owner_String(BenchmarkState state) {
        return state.appProps.test1();
    }

    @Benchmark
    public int Owner_Int(BenchmarkState state) {
        return state.appProps.testInt1();
    }
}
