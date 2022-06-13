package io.github.joeljeremy7.java.config.lib.benchmarks.owner;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources("classpath:AppProps.properties")
public interface AppProps extends Config {
    @Key("test1")
    String test1();

    @Key("test2")
    String test2();

    @Key("test3")
    String test3();

    @Key("test4")
    String test4();

    @Key("test5")
    String test5();

    @Key("test6")
    String test6();

    @Key("test7")
    String test7();

    @Key("test8")
    String test8();

    @Key("test9")
    String test9();

    @Key("test10")
    String test10();

    @Key("testInt1")
    int testInt1();

    @Key("testInt2")
    int testInt2();

    @Key("testInt3")
    int testInt3();

    @Key("testInt4")
    int testInt4();

    @Key("testInt5")
    int testInt5();

    @Key("testInt6")
    int testInt6();

    @Key("testInt7")
    int testInt7();

    @Key("testInt8")
    int testInt8();

    @Key("testInt9")
    int testInt9();
    
    @Key("testInt10")
    int testInt10();

    @Key("variable.${test1}")
    String testVariable1();

    @Key("variable.${test2}")
    String testVariable2();

    @Key("variable.${test3}")
    String testVariable3();

    @Key("variable.${test4}")
    String testVariable4();

    @Key("variable.${test5}")
    String testVariable5();

    @Key("variable.${test6}")
    String testVariable6();

    @Key("variable.${test7}")
    String testVariable7();

    @Key("variable.${test8}")
    String testVariable8();

    @Key("variable.${test9}")
    String testVariable9();

    @Key("variable.${test10}")
    String testVariable10();
}
