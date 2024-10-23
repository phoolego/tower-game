package backend.poc.tower_game.controller;

import backend.poc.tower_game.constant.ResponseConstant;
import backend.poc.tower_game.factory.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @Autowired
    private ResponseFactory responseFactory;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUnexpectedError(HttpServletRequest request, Exception ex) {
        log.error("Unexpected Exception [{}]", ex.getMessage());
        return responseFactory.error(HttpStatus.INTERNAL_SERVER_ERROR, ResponseConstant.GENERAL_ERROR_CODE);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        errors.forEach(log::error);
        return responseFactory.error(HttpStatus.BAD_REQUEST, ResponseConstant.SUCCESS_CODE, errors);
    }
}