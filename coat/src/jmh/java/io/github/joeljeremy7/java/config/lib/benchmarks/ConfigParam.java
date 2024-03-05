package io.github.joeljeremy7.java.config.lib.benchmarks;

import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import javax.annotation.processing.Generated;

@Generated(
    value = "de.poiu.coat.processor.codegeneration.CodeGenerator",
    date = "2024-03-04T22:29:49.748122957+01:00"
)
public enum ConfigParam implements de.poiu.coat.ConfigParam {
  TEST1("test1", java.lang.String.class, null, null, true, null, null),

  TEST_INT1("testInt1", int.class, null, null, true, null, null);

  private final String key;

  private final Class type;

  private final Class collectionType;

  private final String defaultValue;

  private final boolean mandatory;

  private final Class converter;

  private final Class listParser;

  private ConfigParam(final String key, final Class type, final Class collectionType,
      final String defaultValue, final boolean mandatory, final Class converter,
      final Class listParser) {
    this.key = key;
    this.type = type;
    this.collectionType = collectionType;
    this.defaultValue = defaultValue;
    this.mandatory = mandatory;
    this.converter = converter;
    this.listParser = listParser;
  }

  @Override
  public String key() {
    return this.key;
  }

  @Override
  public Class type() {
    return this.type;
  }

  @Override
  public Class collectionType() {
    return this.collectionType;
  }

  @Override
  public String defaultValue() {
    return this.defaultValue;
  }

  @Override
  public boolean mandatory() {
    return this.mandatory;
  }

  @Override
  public Class converter() {
    return this.converter;
  }

  @Override
  public Class listParser() {
    return this.listParser;
  }
}
