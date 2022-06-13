package io.github.joeljeremy7.java.config.lib.benchmarks.externalizedproperties;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperties;
import io.github.joeljeremy7.externalizedproperties.core.resolvers.ResourceResolver;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class ExternalizedPropertiesBenchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private AppProps appProps;
        private ResolverFacadeProxy resolverFacadeProxy;

        @Setup
        public void setup() throws IOException {
            ExternalizedProperties externalizedProperties =
                ExternalizedProperties.builder()
                    .defaults()
                    .resolvers(ResourceResolver.fromUrl(
                        getClass().getResource("/AppProps.properties")
                    ))
                    .build();

            this.appProps = externalizedProperties.initialize(AppProps.class);
            this.resolverFacadeProxy = 
                externalizedProperties.initialize(ResolverFacadeProxy.class);
        }
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class ExternalizedPropertiesAvgt extends ExternalizedPropertiesBenchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class ExternalizedPropertiesThrpt extends ExternalizedPropertiesBenchmarks {}

    @Benchmark
    public String stringProperty(BenchmarkState state) {
        return state.appProps.test1();
    }

    @Benchmark
    public int intProperty(BenchmarkState state) {
        return state.appProps.testInt1();
    }

    @Benchmark
    public String stringProperty_resolverFacade(BenchmarkState state) {
        return state.resolverFacadeProxy.resolve("test1");
    }

    @Benchmark
    public int intProperty_resolverFacade(BenchmarkState state) {
        return state.resolverFacadeProxy.resolve("testInt1", int.class);
    }
}
