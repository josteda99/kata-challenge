package com.kata.bank_challenge.common;
import org.springframework.http.HttpStatus;

public class BankException extends Exception {

    private final AppError error;
    private final HttpStatus httpCode;
    private final String customMessage;

    public BankException(AppError error) {
        this(error, HttpStatus.BAD_REQUEST, "");
    }

    public BankException(AppError error, HttpStatus httpCode) {
        this(error, httpCode, "");
    }

    public BankException(AppError error, HttpStatus httpCode, String customMessage) {
        super(error.code() + (customMessage != null && !customMessage.isBlank()
                ? " " + customMessage
                : ""));
        this.error = error;
        this.httpCode = httpCode != null ? httpCode : HttpStatus.BAD_REQUEST;
        this.customMessage = customMessage != null ? customMessage : "";
    }

    public AppError getError() {
        return error;
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public String getCustomMessage() {
        return customMessage;
    }
}