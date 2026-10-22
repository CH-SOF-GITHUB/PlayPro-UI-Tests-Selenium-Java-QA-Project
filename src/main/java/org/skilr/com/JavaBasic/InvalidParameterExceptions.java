package org.skilr.com.JavaBasic;

public class InvalidParameterExceptions extends RuntimeException {
    public InvalidParameterExceptions(String s, Throwable t) {
        // Chaining Exception: t
        super(s, t);
    }
}
