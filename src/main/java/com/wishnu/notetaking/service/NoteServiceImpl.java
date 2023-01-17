package com.wishnu.notetaking.service;

import com.wishnu.notetaking.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements  NoteService{

    // temp in memory
    public List<Note> notes = new ArrayList<>();

    @Override
    public List<Note> getAllNotes() {
        return notes;
    }

    @Override
    public Note getNote(UUID id) {
        return notes.stream().filter(note-> note.getId().compareTo(id) == 0).findFirst().orElse(null);
    }

    @Override
    public void saveNote(Note note) {
        note.setId(UUID.randomUUID());
        notes.add(note);
    }

    @Override
    public Note updateNote(Note note) {
        List<Note> updated = notes.stream().filter(e -> (e.getId().compareTo(note.getId()) != 0)).collect(Collectors.toList());
        updated.add(note);
        return note;
    }
}
