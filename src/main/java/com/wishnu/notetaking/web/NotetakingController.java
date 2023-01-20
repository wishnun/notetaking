package com.wishnu.notetaking.web;

import com.wishnu.notetaking.entity.Note;
import com.wishnu.notetaking.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class NotetakingController {

    NoteService noteService;

    @GetMapping("/note")
    public List<Note> getAllNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        model.addAttribute("notes", notes);
        return notes;
    }

    @GetMapping("/note/{id}")
    public Note getNote(@PathVariable String id) {
        return noteService.getNote(id);
    }


    @PostMapping("/note")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNote(@RequestBody String description) {
        noteService.saveNote(description);
    }
}
