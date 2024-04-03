package io.github.joeljeremy7.java.config.lib.benchmarks;

import io.github.joeljeremy7.java.config.lib.benchmarks.Benchmarks.BenchmarkState;
import java.io.IOException;
import java.util.Properties;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.StandardEnvironment;

public abstract class BenchmarksSetup {

    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class InitialCreation extends BenchmarksSetup {}



    @Benchmark
    public Environment SpringCore_Creation(BenchmarkState state) throws IOException {
      Properties props = new Properties();
      props.load(Benchmarks.class.getResourceAsStream("/AppProps.properties"));

      ConfigurableEnvironment env = new StandardEnvironment();
      env.getPropertySources().addFirst(
        new PropertiesPropertySource("appProps", props)
      );
      return  env;
    }

}
