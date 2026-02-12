package io.github.joeljeremy7.java.config.lib.benchmarks;

import io.github.joeljeremy7.java.config.lib.benchmarks.Benchmarks.BenchmarkState;
import io.helidon.config.mp.MpConfigSources;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

public abstract class BenchmarksSetup {

    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class InitialCreation extends BenchmarksSetup {}



    @Benchmark
    public Config MP_Helidon_Creation(BenchmarkState state) {
            return ConfigProviderResolver.instance()
                .getBuilder()
                .addDefaultSources()
                .addDiscoveredSources()
                .addDiscoveredConverters()
                .withSources(MpConfigSources.create(
                    getClass().getResource("/AppProps.properties")
                ))
                .build();
    }
}
