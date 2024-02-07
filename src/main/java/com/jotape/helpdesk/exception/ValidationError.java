package com.jotape.helpdesk.exception;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    private List<FildeMessage> errors = new ArrayList<>();

    public ValidationError() {
        super();
    }

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public List<FildeMessage> getErrors() {
        return errors;
    }

    public void
    addError(String fildName, String message) {

        this.errors.add(new FildeMessage(fildName, message));
    }
}
