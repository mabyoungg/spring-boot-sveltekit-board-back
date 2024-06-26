package com.ll.likelionspringboottestmedium.global.exceptionHandlers;

import com.ll.likelionspringboottestmedium.global.exceptions.GlobalException;
import com.ll.likelionspringboottestmedium.global.rq.Rq;
import com.ll.likelionspringboottestmedium.global.rsData.RsData;
import com.ll.likelionspringboottestmedium.standard.base.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
@RequiredArgsConstructor
public class GlobalApiExceptionHandler {
    private final Rq rq;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RsData<Empty>> handle(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.valueOf(400);
        rq.setStatusCode(400);

        return new ResponseEntity<>(RsData.of("400-1", ex.getMessage()), status);
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus
    public ResponseEntity<RsData<Empty>> handle(GlobalException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.getRsData().getStatusCode());
        rq.setStatusCode(ex.getRsData().getStatusCode());

        return new ResponseEntity<>(ex.getRsData(), status);
    }
}
