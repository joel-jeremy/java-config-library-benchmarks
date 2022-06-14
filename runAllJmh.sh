#!/bin/sh

./gradlew build \
    :baseline:jmh \
    :cfg4j:jmh \
    :externalized-properties:jmh \
    :gestalt-config:jmh \
    :lightbend-config:jmh \
    :microprofile-geronimo:jmh \
    :microprofile-microbean:jmh \
    :microprofile-smallrye:jmh \
    :owner:jmh

./gradlew mergeJmhResults jmhReport