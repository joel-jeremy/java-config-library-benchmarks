package io.github.joeljeremy7.java.config.lib.benchmarks;

import io.github.joeljeremy.externalizedproperties.core.ResolverFacade;

public interface ResolverFacadeProxy {
    @ResolverFacade
    <T> T resolve(String propertyName, Class<T> targetType);

    @ResolverFacade
    String resolve(String propertyName);
}
