package dev.warrant.exception;

public class WarrantException extends Exception {

    public WarrantException(String msg) {
        super(msg);
    }

    public WarrantException(Exception e) {
        super(e);
    }
}
