package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> notFoundException(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors();
        List<DadosErroValidacao> list = erros.stream().map(DadosErroValidacao::new).toList();
        return ResponseEntity.badRequest().body(list);
    }

}
