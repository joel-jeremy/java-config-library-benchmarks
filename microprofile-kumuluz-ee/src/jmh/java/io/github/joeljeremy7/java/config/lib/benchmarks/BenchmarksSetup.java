package io.github.joeljeremy7.java.config.lib.benchmarks;

import com.kumuluz.ee.configuration.utils.ConfigurationImpl;
import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import io.github.joeljeremy7.java.config.lib.benchmarks.Benchmarks.BenchmarkState;
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
    public Config MP_KumuluzEE_Creation(BenchmarkState state) {
            System.setProperty("com.kumuluz.ee.configuration.file", "AppProps.properties");
            ConfigurationUtil.initialize(new ConfigurationImpl());

            return ConfigProviderResolver.instance()
                .getBuilder()
                .addDefaultSources()
                .addDiscoveredSources()
                .addDiscoveredConverters()
                .build();
    }
}
