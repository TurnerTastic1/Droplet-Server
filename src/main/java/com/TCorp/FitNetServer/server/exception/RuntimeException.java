package com.TCorp.FitNetServer.server.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class RuntimeException extends java.lang.RuntimeException {

        private HttpStatus httpStatus;
        private String message;
        private List<String> errors;

        public RuntimeException(String message) {
            this.message = message;
        }

        public RuntimeException(String message, HttpStatus httpStatus) {
            this.message = message;
            this.httpStatus = httpStatus;
        }

        public RuntimeException(String message, HttpStatus httpStatus, List<String> errors) {
            this.message = message;
            this.httpStatus = httpStatus;
            this.errors = errors;
        }

        public String getMessage() {
            return message;
        }

        public HttpStatus getHttpStatus() {
        return httpStatus;
    }

        public List<String> getErrors() {
        return errors;
    }

}
