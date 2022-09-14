package com.ra.nontonfilm.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class NontonFilmException {


    public static RuntimeException throwException(ExceptionType exceptionType, String msg) {
        if(ExceptionType.USER_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(msg);
        } else if(ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(msg);
        }
        return new RuntimeException(msg);
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

    public enum ExceptionType {
        USER_NOT_FOUND, DUPLICATE_ENTITY
    }
}
