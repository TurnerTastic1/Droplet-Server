package com.TCorp.FitNetServer.api.exception;

import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

public class RuntimeException extends java.lang.RuntimeException {

        private HttpStatus httpStatus;
        private List<String> errors;
        private Object data;

        public RuntimeException(HttpStatus httpStatus, String message) {
            this(httpStatus, message, Collections.singletonList(message), null);
        }

        public RuntimeException(HttpStatus httpStatus, String message, List<String> errors) {
            this(httpStatus, message, errors, null);
        }

        public RuntimeException(HttpStatus httpStatus, String message, List<String> errors, Object data) {
            super(message);
            this.httpStatus = httpStatus;
//            this.message = message;
            this.errors = errors;
            this.data = data;
        }

        public HttpStatus getHttpStatus() {
        return httpStatus;
    }

        public List<String> getErrors() {
        return errors;
    }

        public Object getData() {
            return data;
        }

}
