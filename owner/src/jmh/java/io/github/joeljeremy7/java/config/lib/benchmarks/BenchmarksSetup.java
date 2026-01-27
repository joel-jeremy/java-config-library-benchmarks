package io.github.joeljeremy7.java.config.lib.benchmarks;

import io.github.joeljeremy7.java.config.lib.benchmarks.Benchmarks.BenchmarkState;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;
import org.aeonbits.owner.ConfigFactory;

public abstract class BenchmarksSetup {

    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class InitialCreation extends BenchmarksSetup {}


    @Benchmark
    public AppProps Owner_Create(BenchmarkState state) {
        return ConfigFactory.create(AppProps.class);
    }
}
