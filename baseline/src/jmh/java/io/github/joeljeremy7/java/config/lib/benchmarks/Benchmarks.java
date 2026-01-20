package io.github.joeljeremy7.java.config.lib.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class Benchmarks {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        private String test1Key = "test1";
        private String testInt1Key = "testInt1";
        private String test1EnvKey = "TEST1";
        private String testInt1EnvKey = "TEST_INT1";
    }

    @Setup
    public void setup() throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        System.setProperty("test1", "test.value.1");
        System.setProperty("testInt1", "1");
        setEnv("TEST1", "test.value.1");
        setEnv("TEST_INT1", "1");
    }

    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public static class Avgt extends Benchmarks {
    }

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {
    }

    @Benchmark
    public String Baseline_SystemProperty_String(BenchmarkState state) {
        return System.getProperty(state.test1Key);
    }

    @Benchmark
    public int Baseline_SystemProperty_Int(BenchmarkState state) {
        return Integer.parseInt(System.getProperty(state.testInt1Key));
    }

    @Benchmark
    public String Baseline_SystemEnv_String(BenchmarkState state) {
        return System.getenv(state.test1EnvKey);
    }

    @Benchmark
    public int Baseline_SystemEnv_Int(BenchmarkState state) {
        return Integer.parseInt(System.getenv(state.testInt1EnvKey));
    }

    // Reflectively modify the underlying mutable map of the env map.
    private static void setEnv(String key, String value) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, String> env = System.getenv();
        Field mutableMapField = env.getClass().getDeclaredField("m");
        mutableMapField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Map<String, String> mutableMap = (Map<String, String>) mutableMapField.get(env);
        mutableMap.put(key, value);
    }
}
