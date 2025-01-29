package br.com.cmdev.data.jpa.exception;

import org.springframework.http.HttpStatus;

public record ResponseErrorMessage(HttpStatus status, String exception, String message, String cause) {
}
