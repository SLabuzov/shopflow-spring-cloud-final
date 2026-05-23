package by.sample.shopflow.catalog.web;

import by.sample.shopflow.catalog.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFound(ResourceNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle("Resource Not Found");
        problem.setType(URI.create("https://shopflow.com/errors/not-found"));
        problem.setProperty("errorCode", ex.getErrorCode());
        return problem;
    }

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail unpredictableException(RuntimeException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR");
        problem.setTitle("INTERNAL SERVER ERROR");
        problem.setType(URI.create("https://shopflow.com/errors/internal-server-error"));
        problem.setProperty("errorCode", HttpStatus.INTERNAL_SERVER_ERROR);
        return problem;
    }
}
