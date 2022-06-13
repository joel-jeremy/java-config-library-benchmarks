# Benchmarks Results

To view latest benchmarks results, click [here](https://jmh.morethan.io/?gist=016a70a392934d0e5a07a4d291731218).

## Benchmark Setup

Each library is setup with its "default" configurations (via the exposed "defaults" method in their builders). In addition to the defaults, an additional configuration/setup will be done to "load" a `.properties` file (with exactly the same file contents). For the benchmark runs, each library will load properties/configuration from the loaded `.properties` file.
