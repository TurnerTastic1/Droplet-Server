package com.TCorp.FitNetServer.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Getter
public class CustomException extends java.lang.RuntimeException {

        private final HttpStatus httpStatus;
        private final List<String> errors;
        private final Object data;

        public CustomException(HttpStatus httpStatus, String message) {
            this(httpStatus, message, Collections.singletonList(message), null);
        }

        public CustomException(HttpStatus httpStatus, String message, List<String> errors) {
            this(httpStatus, message, errors, null);
        }

        public CustomException(HttpStatus httpStatus, String message, List<String> errors, Object data) {
            super(message);
            this.httpStatus = httpStatus;
//            this.message = message;
            this.errors = errors;
            this.data = data;
        }

}
