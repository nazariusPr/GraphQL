package com.example.BFF.scalar;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import org.jetbrains.annotations.NotNull;

public class LocalDateTimeScalar {
  private static final DateTimeFormatter FORMATTER =
      DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

  public static GraphQLScalarType createLocalDateTimeScalar() {
    return GraphQLScalarType.newScalar()
        .name("LocalDateTime")
        .description("Custom scalar for handling LocalDateTime in format 'dd-MM-yyyy HH:mm'")
        .coercing(
            new Coercing<LocalDateTime, String>() {
              @Override
              public String serialize(
                  @NotNull Object dataFetcherResult,
                  @NotNull GraphQLContext graphQLContext,
                  @NotNull Locale locale) {
                if (dataFetcherResult instanceof LocalDateTime dateTime) {
                  return FORMATTER.format(dateTime);
                }
                throw new CoercingSerializeException("Expected a LocalDateTime object.");
              }

              @Override
              public LocalDateTime parseValue(
                  @NotNull Object input,
                  @NotNull GraphQLContext graphQLContext,
                  @NotNull Locale locale) {
                if (input instanceof String dateTimeStr) {
                  try {
                    return LocalDateTime.parse(dateTimeStr, FORMATTER);
                  } catch (DateTimeParseException e) {
                    throw new CoercingParseValueException(
                        "Invalid LocalDateTime format. Expected 'dd-MM-yyyy HH:mm'.", e);
                  }
                }
                throw new CoercingParseValueException("Expected a String value for LocalDateTime.");
              }

              @Override
              public LocalDateTime parseLiteral(
                  @NotNull Value input,
                  @NotNull CoercedVariables variables,
                  @NotNull GraphQLContext graphQLContext,
                  @NotNull Locale locale) {
                if (input instanceof StringValue stringValue) {
                  try {
                    return LocalDateTime.parse(stringValue.getValue(), FORMATTER);
                  } catch (DateTimeParseException e) {
                    throw new CoercingParseLiteralException(
                        "Invalid LocalDateTime literal. Expected 'dd-MM-yyyy HH:mm'.", e);
                  }
                }
                throw new CoercingParseLiteralException(
                    "Expected a StringValue for LocalDateTime literal.");
              }
            })
        .build();
  }
}
