package com.ra.bioskop.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BioskopException {

    BioskopException() {}

    public static RuntimeException throwException(ExceptionType exceptionType, HttpStatus statusCode, String msg) {
        return switch (exceptionType) {
            case INVALID_EMAIL -> new EmailValidateException(statusCode, msg);
            case DUPLICATE_ENTITY -> new DuplicateEntityException(statusCode, msg);
            case FILM_NOT_FOUND -> new FilmNotFoundException(statusCode, msg);
            case NOT_FOUND -> new EntityNotFoundException(statusCode, msg);
            default -> new RuntimeException(msg);
        };
    }

    @Setter
    @Getter
    public static class StudioNotFoundException extends RuntimeException {
        private final HttpStatus statusCode;

        public StudioNotFoundException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }

    @Setter
    @Getter
    public static class DuplicateEntityException extends RuntimeException {
        private final HttpStatus statusCode;

        public DuplicateEntityException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }

    @Setter
    @Getter
    public static class EntityNotFoundException extends RuntimeException {
        private final HttpStatus statusCode;

        public EntityNotFoundException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }

    @Setter
    @Getter
    public static class EmailValidateException extends RuntimeException {
        private final HttpStatus statusCode;
        public EmailValidateException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }

    @Setter
    @Getter
    public static class FilmNotFoundException extends RuntimeException {
        private final HttpStatus statusCode;

        public FilmNotFoundException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }
}
