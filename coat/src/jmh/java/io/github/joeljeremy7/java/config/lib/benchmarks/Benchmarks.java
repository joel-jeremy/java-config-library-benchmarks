package io.github.joeljeremy7.java.config.lib.benchmarks;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
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
        private ImmutableConfig config;

        @Setup
        public void setup() throws IOException, URISyntaxException {
          final InputStream inStream = Benchmark.class.getResourceAsStream("/AppProps.properties");
          final Properties props= new Properties();
          props.load(inStream);
          this.config= ImmutableConfig.from(props);
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Avgt extends Benchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {}

    @Benchmark
    public String Coat_String(BenchmarkState state) {
        return state.config.test1();
    }

    @Benchmark
    public int Coat_Int(BenchmarkState state) {
        return state.config.testInt1();
    }
}
