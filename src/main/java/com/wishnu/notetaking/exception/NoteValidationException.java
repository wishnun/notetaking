package com.wishnu.notetaking.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoteValidationException extends  RuntimeException{

    public NoteValidationException(String message) {
        super(message);
    }

    public NoteValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteValidationException(Throwable cause) {
        super(cause);
    }

}
