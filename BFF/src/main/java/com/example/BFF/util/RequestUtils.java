package com.example.BFF.util;

import com.example.BFF.dto.ExceptionDto;
import com.example.BFF.exception.exceptions.CustomGraphQLException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RequestUtils {

  private final WebClient webClient;

  public <T> T postRequest(String uri, Object requestBody, Class<T> responseType) {
    return webClient
        .post()
        .uri(uri)
        .bodyValue(requestBody)
        .retrieve()
        .onStatus(this::isErrorStatus, this::handleError)
        .bodyToMono(responseType)
        .block();
  }

  public <T> T getRequest(String uri, Class<T> responseType, Object... uriVariables) {
    return webClient
        .get()
        .uri(uri, uriVariables)
        .retrieve()
        .onStatus(this::isErrorStatus, this::handleError)
        .bodyToMono(responseType)
        .block();
  }

  private Mono<? extends Throwable> handleError(ClientResponse response) {
    return response
        .bodyToMono(ExceptionDto.class)
        .map(
            error -> new CustomGraphQLException(response.statusCode().value(), error.getMessage()));
  }

  private boolean isErrorStatus(HttpStatusCode status) {
    return status.is4xxClientError() || status.is5xxServerError();
  }
}
