package com.example.BFF.exception;

import com.example.BFF.exception.exceptions.CustomGraphQLException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomGraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
  @Override
  protected @NotNull GraphQLError resolveToSingleError(
      @NotNull Throwable ex, @NotNull DataFetchingEnvironment env) {
    if (ex instanceof CustomGraphQLException customEx) {
      log.error("**/ Custom GraphQL exception occurred with message: {}", ex.getMessage());
      return buildGraphQLError(customEx, env);
    }

    log.error("**/ Unexpected exception occurred: {}", ex.getMessage());
    return GraphqlErrorBuilder.newError()
        .errorType(ErrorType.INTERNAL_ERROR)
        .message("An unexpected error occurred")
        .build();
  }

  private GraphQLError buildGraphQLError(CustomGraphQLException ex, DataFetchingEnvironment env) {
    return GraphqlErrorBuilder.newError()
        .errorType(getErrorTypeByStatusCode(ex.getStatusCode()))
        .message(ex.getMessage())
        .path(env.getExecutionStepInfo().getPath())
        .location(env.getField().getSourceLocation())
        .build();
  }

  private ErrorType getErrorTypeByStatusCode(int statusCode) {
    return switch (statusCode) {
      case 400 -> ErrorType.BAD_REQUEST;
      case 401 -> ErrorType.UNAUTHORIZED;
      case 403 -> ErrorType.FORBIDDEN;
      case 404 -> ErrorType.NOT_FOUND;
      default -> ErrorType.INTERNAL_ERROR;
    };
  }
}
