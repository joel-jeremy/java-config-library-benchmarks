package io.github.joeljeremy7.java.config.lib.benchmarks;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import io.github.joeljeremy7.java.config.lib.benchmarks.Benchmarks.BenchmarkState;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;

public abstract class BenchmarksSetup {

    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class InitialCreation extends BenchmarksSetup {}



    @Benchmark
    public Config LightbendConfig_Creation(BenchmarkState state) {
      return ConfigFactory.load("AppProps");
    }
}
