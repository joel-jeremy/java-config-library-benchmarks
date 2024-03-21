package io.github.joeljeremy7.java.config.lib.benchmarks;

import de.poiu.coat.validation.ConfigValidationException;
import java.io.IOException;
import java.util.Properties;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

public abstract class BenchmarksSetup {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
    }


    @BenchmarkMode(Mode.SingleShotTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class InitialCreation extends BenchmarksSetup {}



    @Benchmark
    public Config Coat_Creation(BenchmarkState state) throws ConfigValidationException, IOException {
      final Properties props= new Properties();
      props.load(Config.class.getResourceAsStream("/AppProps.properties"));
      return ConfigBuilder.from(props);
    }
}
