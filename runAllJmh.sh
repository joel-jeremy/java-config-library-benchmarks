#!/bin/sh

./gradlew build \
    :avaje-config:jmh \
    :baseline:jmh \
    :cfg4j:jmh \
    :coat:jmh \
    :externalized-properties:jmh \
    :gestalt-config:jmh \
    :lightbend-config:jmh \
    :microprofile-geronimo:jmh \
    :microprofile-helidon:jmh \
    :microprofile-kumuluz-ee:jmh \
    :microprofile-microbean:jmh \
    :microprofile-smallrye:jmh \
    :owner:jmh \
    :spring-core:jmh

./gradlew mergeJmhResults jmhReport