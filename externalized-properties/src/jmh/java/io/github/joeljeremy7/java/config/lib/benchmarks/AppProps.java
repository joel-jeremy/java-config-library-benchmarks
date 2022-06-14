package io.github.joeljeremy7.java.config.lib.benchmarks;

import io.github.joeljeremy7.externalizedproperties.core.ExternalizedProperty;

public interface AppProps {
    @ExternalizedProperty("test1")
    String test1();

    @ExternalizedProperty("test2")
    String test2();

    @ExternalizedProperty("test3")
    String test3();

    @ExternalizedProperty("test4")
    String test4();

    @ExternalizedProperty("test5")
    String test5();

    @ExternalizedProperty("test6")
    String test6();

    @ExternalizedProperty("test7")
    String test7();

    @ExternalizedProperty("test8")
    String test8();

    @ExternalizedProperty("test9")
    String test9();

    @ExternalizedProperty("test10")
    String test10();

    @ExternalizedProperty("testInt1")
    int testInt1();

    @ExternalizedProperty("testInt2")
    int testInt2();

    @ExternalizedProperty("testInt3")
    int testInt3();

    @ExternalizedProperty("testInt4")
    int testInt4();

    @ExternalizedProperty("testInt5")
    int testInt5();

    @ExternalizedProperty("testInt6")
    int testInt6();

    @ExternalizedProperty("testInt7")
    int testInt7();

    @ExternalizedProperty("testInt8")
    int testInt8();

    @ExternalizedProperty("testInt9")
    int testInt9();
    
    @ExternalizedProperty("testInt10")
    int testInt10();

    @ExternalizedProperty("variable.${test1}")
    String testVariable1();

    @ExternalizedProperty("variable.${test2}")
    String testVariable2();

    @ExternalizedProperty("variable.${test3}")
    String testVariable3();

    @ExternalizedProperty("variable.${test4}")
    String testVariable4();

    @ExternalizedProperty("variable.${test5}")
    String testVariable5();

    @ExternalizedProperty("variable.${test6}")
    String testVariable6();

    @ExternalizedProperty("variable.${test7}")
    String testVariable7();

    @ExternalizedProperty("variable.${test8}")
    String testVariable8();

    @ExternalizedProperty("variable.${test9}")
    String testVariable9();

    @ExternalizedProperty("variable.${test10}")
    String testVariable10();
}
