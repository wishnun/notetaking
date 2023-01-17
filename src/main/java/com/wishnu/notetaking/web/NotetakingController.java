package com.wishnu.notetaking.web;

import com.wishnu.notetaking.model.Note;
import com.wishnu.notetaking.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class NotetakingController {

    NoteService noteService;

    @GetMapping("/note")
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/note/{id}")
    public Note getNote(@PathVariable UUID id) {
        return noteService.getNote(id);
    }

    @PostMapping("/note")
    @ResponseStatus(HttpStatus.CREATED)
    public Note saveNote(@RequestBody Note note) {
        noteService.saveNote(note);
        return noteService.getNote(note.getId());
    }
}
