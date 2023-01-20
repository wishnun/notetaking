package com.wishnu.notetaking.service;

import com.wishnu.notetaking.entity.Note;

import java.util.List;

public interface NoteService {

    List<Note> getAllNotes();
    Note getNote(String id);
    void saveNote(String description);
    Note updateNote(Note note);
}
