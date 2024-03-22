package io.github.joeljeremy7.java.config.lib.benchmarks;

import io.github.joeljeremy.externalizedproperties.core.ExternalizedProperties;
import io.github.joeljeremy.externalizedproperties.core.resolvers.ResourceResolver;
import io.github.joeljeremy7.java.config.lib.benchmarks.Benchmarks.BenchmarkState;
import java.io.IOException;
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
    public AppProps EP_Creation(BenchmarkState state) throws IOException {
      ExternalizedProperties externalizedProperties =
        ExternalizedProperties.builder()
          .defaults()
          .resolvers(ResourceResolver.fromUrl(
            getClass().getResource("/AppProps.properties")
          ))
          .build();

      return externalizedProperties.initialize(AppProps.class);
    }


    @Benchmark
    public ResolverFacadeProxy EP_CreationResolverFacade(BenchmarkState state) throws IOException {
      ExternalizedProperties externalizedProperties =
        ExternalizedProperties.builder()
          .defaults()
          .resolvers(ResourceResolver.fromUrl(
            getClass().getResource("/AppProps.properties")
          ))
          .build();
      return externalizedProperties.initialize(ResolverFacadeProxy.class);
    }
}
