package io.github.joeljeremy7.java.config.lib.benchmarks;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
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
        private Config config;
        private String test1Key = "test1";
        private String testInt1Key = "testInt1";

        @Setup
        public void setup() {
            this.config = ConfigFactory.load("AppProps");
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Avgt extends Benchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {}

    @Benchmark
    public String LightbendConfig_String(BenchmarkState state) {
        return state.config.getString(state.test1Key);
    }

    @Benchmark
    public int LightbendConfig_Int(BenchmarkState state) {
        return state.config.getInt(state.testInt1Key);
    }
}
