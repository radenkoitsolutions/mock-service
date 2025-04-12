package com.example.mock.data.exception;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // Този статус ще бъде върнат при хвърляне на изключението
public class MissingFileException extends Exception {

    private final HttpStatus status;

    // Конструктор с параметри, които ще използваш за съобщение и допълнителни аргументи
    @JsonCreator
    public MissingFileException(
            @JsonProperty("message") String message,
            @JsonProperty("args") Object... args) {
        super(String.format(message, args));  // Форматира съобщението, ако има аргументи
        this.status = HttpStatus.CONFLICT;  // Задава се статус код за конфликта
    }

    // Може да добавиш getter за статус кода, ако искаш да използваш този статус в други части на приложението
    public HttpStatus getStatus() {
        return status;
    }
}
