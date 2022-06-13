package io.github.joeljeremy7.java.config.lib.benchmarks.lightbend;

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

public abstract class LightbendConfigBenchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private Config config;

        @Setup
        public void setup() {
            this.config = ConfigFactory.load("AppProps");
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class LightbendConfigAvgt extends LightbendConfigBenchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class LightbendConfigThrpt extends LightbendConfigBenchmarks {}

    @Benchmark
    public String stringProperty(BenchmarkState state) {
        return state.config.getString("test1");
    }

    @Benchmark
    public int intProperty(BenchmarkState state) {
        return state.config.getInt("testInt1");
    }
}
