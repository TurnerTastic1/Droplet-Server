package com.TCorp.FitNetServer.api.exception;

import com.TCorp.FitNetServer.api.response.ResponseGlobal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController()
@RestControllerAdvice
public class GlobalExceptionHandler implements ErrorController {

    public GlobalExceptionHandler() {
    }

    @RequestMapping("/error")
    public ResponseEntity<ResponseGlobal> handleError(HttpServletRequest request) {
        HttpStatus httpStatus = getHttpStatus(request);
        String message = getErrorMessage(request, httpStatus);

        Map<String, Object> dataBody = new LinkedHashMap<>();
        dataBody.put("errorMessages", Collections.singletonList(message));
        dataBody.put("defaultMessage", message);

        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("errors", Collections.singletonList(dataBody));

        return ResponseEntity.status(httpStatus).body(
                ResponseGlobal.builder()
                        .code(httpStatus.value())
                        .message(message)
                        .status(false)
                        .timestamp(System.currentTimeMillis())
                        .data(response)
                        .build()
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseGlobal> handleError(HttpRequestMethodNotSupportedException e) {
        String message = e.getMessage();

        Map<String, Object> dataBody = new LinkedHashMap<>();
        dataBody.put("errorMessages", Collections.singletonList(message));
        dataBody.put("defaultMessage", message);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "It seems you're using the wrong HTTP method");
        response.put("errors", Collections.singletonList(dataBody));

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                ResponseGlobal.builder()
                        .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .message(message)
                        .status(false)
                        .timestamp(System.currentTimeMillis())
                        .data(response)
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseGlobal> handleError(MethodArgumentNotValidException e) {
        Map<String, Object> dataBody = new LinkedHashMap<>();
        dataBody.put("errorMessages", e.getBindingResult().getAllErrors());
        dataBody.put("defaultMessage", e.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Validation failed");
        response.put("errors", Collections.singletonList(dataBody));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ResponseGlobal.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Validation failed")
                        .status(false)
                        .timestamp(System.currentTimeMillis())
                        .data(response)
                        .build()
        );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseGlobal> handleError(CustomException e) {
        Map<String, Object> dataBody = new LinkedHashMap<>();
        dataBody.put("errorMessages", e.getErrors());
        dataBody.put("defaultMessage", e.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("message", e.getMessage());
        response.put("errors", Collections.singletonList(dataBody));

        return ResponseEntity.status(e.getHttpStatus()).body(
                ResponseGlobal.builder()
                        .code(e.getHttpStatus().value())
                        .message(e.getMessage())
                        .status(false)
                        .timestamp(System.currentTimeMillis())
                        .data(response)
                        .build()
        );
    }



    private HttpStatus getHttpStatus(HttpServletRequest request) {

        //get the standard error code set by Spring Context
        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            return HttpStatus.valueOf(status);
        }

        // maybe we're the one that trigger the redirect
        // with the code param
        String code = request.getParameter("code");
        if (code != null && !code.isBlank()) {
            return HttpStatus.valueOf(code);
        }

        //default fallback
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    private String getErrorMessage(HttpServletRequest request, HttpStatus httpStatus) {

        //get the error message set by Spring context
        // and return it if it's not null
        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        if (message != null && !message.isEmpty()) {
            return message;
        }

        //if the default message is null,
        //let's construct a message based on the HTTP status
        message = switch (httpStatus) {
            case NOT_FOUND -> "The resource does not exist";
            case INTERNAL_SERVER_ERROR -> "Something went wrong internally";
            case FORBIDDEN -> "Permission denied";
            case TOO_MANY_REQUESTS -> "Too many requests";
            default -> httpStatus.getReasonPhrase();
        };

        return message;
    }
}
