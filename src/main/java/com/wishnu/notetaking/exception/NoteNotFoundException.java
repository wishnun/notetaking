package com.wishnu.notetaking.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoteNotFoundException extends   RuntimeException{
    public NoteNotFoundException(String message) {
        super(message);
    }

    public NoteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteNotFoundException(Throwable cause) {
        super(cause);
    }
}
