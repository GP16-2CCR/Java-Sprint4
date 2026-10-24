package br.com.motiva.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.motiva.service.NaoEncontradoException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(NaoEncontradoException.class)
    public ResponseEntity<ErroResponse> naoEncontrado(NaoEncontradoException ex) {
        return resposta(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> regraNegocio(IllegalArgumentException ex) {
        return resposta(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResponse> integridade(DataIntegrityViolationException ex) {
        return resposta(HttpStatus.BAD_REQUEST, "Operação inválida: o registro está vinculado a outro dado.");
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErroResponse> requisicaoInvalida(Exception ex) {
        return resposta(HttpStatus.BAD_REQUEST, "Requisição inválida.");
    }

    private ResponseEntity<ErroResponse> resposta(HttpStatus status, String mensagem) {
        return ResponseEntity.status(status).body(new ErroResponse(status.value(), mensagem));
    }
}
