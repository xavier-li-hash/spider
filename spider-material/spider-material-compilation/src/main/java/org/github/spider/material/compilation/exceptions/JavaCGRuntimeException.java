package org.github.spider.material.compilation.exceptions;

/**
 * @description:
 */
public class JavaCGRuntimeException extends RuntimeException {
    public JavaCGRuntimeException() {
    }

    public JavaCGRuntimeException(String message) {
        super(message);
    }

    public JavaCGRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JavaCGRuntimeException(Throwable cause) {
        super(cause);
    }

    public JavaCGRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
