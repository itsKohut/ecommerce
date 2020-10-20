package projarq.trabalho.com.br.ecommerce.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import projarq.trabalho.com.br.ecommerce.json.response.ErrorResponse;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse.ErrorResponseBuilder erro = ErrorResponse.builder();
        erro.httpStatus(HttpStatus.BAD_REQUEST.value());
        erro.mensagem(ex.getMessage());
        erro.timeStamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro.build());

    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(DisabledException ex) {
        ErrorResponse.ErrorResponseBuilder erro = ErrorResponse.builder();
        erro.httpStatus(HttpStatus.BAD_REQUEST.value());
        erro.mensagem(ex.getMessage());
        erro.timeStamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro.build());

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(BadCredentialsException ex) {
        ErrorResponse.ErrorResponseBuilder erro = ErrorResponse.builder();
        erro.httpStatus(HttpStatus.BAD_REQUEST.value());
        erro.mensagem(ex.getMessage());
        erro.timeStamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro.build());
    }
}