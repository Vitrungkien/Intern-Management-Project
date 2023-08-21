package me.jwt_spring.ThrowException;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userNotFound) {
    }

}
