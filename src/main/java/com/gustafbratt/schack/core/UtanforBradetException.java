package com.gustafbratt.schack.core;

public class UtanforBradetException extends Exception {
    public UtanforBradetException(String s) {
    }

    public UtanforBradetException() {
    }

    //Ta bort för sämre prestanda men bättre stack traces.
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
