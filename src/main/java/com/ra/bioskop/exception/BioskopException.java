package com.ra.bioskop.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class BioskopException {

    public static RuntimeException throwException(ExceptionType exceptionType, HttpStatus statusCode, String msg) {
        switch (exceptionType) {
            case INVALID_EMAIL:
                return new EmailValidateException(statusCode, msg);
            case DUPLICATE_ENTITY:
                return new DuplicateEntityException(statusCode, msg);
            case FILM_NOT_FOUND:
                return new FilmNotFoundException(statusCode, msg);
            case NOT_FOUND:
                return new EntityNotFoundException(statusCode, msg);
            default:
                return new RuntimeException(msg);
        }
    }

    @Setter
    @Getter
    public static class StudioNotFoundException extends RuntimeException {
        private HttpStatus statusCode;
        private String msg;
        public StudioNotFoundException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }

    @Setter
    @Getter
    public static class DuplicateEntityException extends RuntimeException {
        private HttpStatus statusCode;
        private String msg;
        public DuplicateEntityException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }

    @Setter
    @Getter
    public static class EntityNotFoundException extends RuntimeException {
        private HttpStatus statusCode;
        private String msg;
        public EntityNotFoundException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }
    @Setter
    @Getter
    public static class EmailValidateException extends RuntimeException {
        private HttpStatus statusCode;
        private String msg;
        public EmailValidateException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }

    @Setter
    @Getter
    public static class FilmNotFoundException extends RuntimeException {
        private HttpStatus statusCode;
        private String msg;
        public FilmNotFoundException(HttpStatus statusCode, String msg) {
            super(msg);
            this.statusCode = statusCode;
        }
    }
}
