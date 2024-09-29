package zerobase.weather.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // ControllerAdvice : 전역의 예외처리
public class GlobalExceptionHandler { // 클래스 안에 전역의 예외가 모이도록해서
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // ( 500번대 에러, 서버내부에 문제가 있다. )
    @ExceptionHandler(Exception.class) // ExceptionHandler 가 클래스 안에 모인 예외를 핸들링한다.
    public Exception handleAllExceptions() {
        System.out.println("error from GlobalExceptionHandler");
        return new Exception();
    }
}
