# Benchmarks Results

To view latest benchmarks results, go to [https://joeljeremy7.github.io/java-config-library-benchmarks/](https://joeljeremy7.github.io/java-config-library-benchmarks/).

## Benchmark Setup

Each library is setup with its "default" configurations (via the exposed "defaults" method in their builders). In addition to the defaults, additional configuration/setup is done to "load" a single `.properties` file (with exactly the same file contents). For the benchmark runs, each library will load properties/configuration from the loaded `.properties` file.

## Adding New Benchmarks

To introduce a new benchmark project, create a new gradle module and:

1. Create an `Benchmark` abstract class under `io.github.joeljeremy7.java.config.lib.benchmarks` package.
2. Create benchmark methods in `Benchmark` class (Minimum required methods are retrieving a String and an int property/configuration)
3. Create an `Avgt` and `Thrpt` nested class within `Benchmark` which extends the abstract `Benchmark` class so that they execute the same benchmarks.
4. Annotate `Avgt` and `Thrpt` nested classes with appropriate JMH annotations to record results: avgt (ns) and thrpt (ms).
5. Add the module's `jmh` task to the `runAllJmh.sh` script.

## Benchmark Run

Benchmarks require Java 11.

To run the benchmarks, use the `runAllJmh.sh` script.
