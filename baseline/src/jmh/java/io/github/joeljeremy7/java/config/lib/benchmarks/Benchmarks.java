package io.github.joeljeremy7.java.config.lib.benchmarks;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Setup;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class Benchmarks {

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
    public static class Avgt extends Benchmarks {}

    @BenchmarkMode(Mode.Throughput)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    public static class Thrpt extends Benchmarks {}

    @Benchmark
    public String SystemProperty_String() {
        return System.getProperty("test1");
    }

    @Benchmark
    public int SystemProperty_Int() {
        return Integer.parseInt(System.getProperty("test1"));
    }

    @Benchmark
    public String EnvVar_String() {
        return System.getenv("TEST1");
    }

    @Benchmark
    public int EnvVar_Int() {
        return Integer.parseInt(System.getenv("TEST_INT1"));
    }

    // Reflectively modify the underlying mutable map of the env map.
    private static void setEnv(String key, String value) throws NoSuchFieldException,
            SecurityException, IllegalArgumentException, IllegalAccessException {
        Map<String, String> env = System.getenv();
        Field mutableMapField = env.getClass().getDeclaredField("m");
        mutableMapField.setAccessible(true);
        @SuppressWarnings("unchecked")
        Map<String, String> mutableMap = 
            (Map<String, String>)mutableMapField.get(env);
        mutableMap.put("key", "value");
    }
}
