package com.aziis98.dare.system;

public class APIException extends RuntimeException {

    public APIException() {
        super();
    }

    public APIException(String s) {
        super(s);
    }

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIException(Throwable cause) {
        super(cause);
    }

}
