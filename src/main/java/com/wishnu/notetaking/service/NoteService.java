package com.wishnu.notetaking.service;

import com.wishnu.notetaking.model.Note;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    List<Note> getAllNotes();
    Note getNote(UUID id);
    void saveNote(Note note);
    Note updateNote(Note note);
}
