package com.ra.nontonfilm.exception;

import org.springframework.stereotype.Component;

@Component
public class NontonFilmException {

    public static RuntimeException throwException(ExceptionType exceptionType, String msg) {
        switch (exceptionType) {
            case USER_NOT_FOUND:
                return new EntityNotFoundException(msg);
            case INVALID_EMAIL:
                return new EmailValidateException(msg);
            case DUPLICATE_ENTITY:
                return new DuplicateEntityException(msg);
            case FILM_NOT_FOUND:
                return new FilmNotFoundException(msg);
            default:
                return new RuntimeException(msg);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String msg) {
            super(msg);
        }
    }

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String msg) {
            super(msg);
        }
    }

    public static class EmailValidateException extends RuntimeException {
        public EmailValidateException(String msg) {
            super(msg);
        }
    }

    public static class FilmNotFoundException extends RuntimeException {
        public FilmNotFoundException(String msg) {
            super(msg);
        }
    }


}
