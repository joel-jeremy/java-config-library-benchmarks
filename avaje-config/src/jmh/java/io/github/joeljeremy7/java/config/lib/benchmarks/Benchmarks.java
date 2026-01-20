package io.github.joeljeremy7.java.config.lib.benchmarks;

import io.avaje.config.Config;
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
        private String test1Key;
        private String testInt1Key;

        @Setup
        public void setup() {
            this.test1Key = "test1";
            this.testInt1Key = "testInt1";
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Avgt extends Benchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {}

    @Benchmark
    public String Avaje_String(BenchmarkState state) {
        return Config.get(state.test1Key);
    }

    @Benchmark
    public int Avaje_Int(BenchmarkState state) {
        return Config.getInt(state.testInt1Key);
    }
}
