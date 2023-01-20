package com.wishnu.notetaking.service;

import com.wishnu.notetaking.entity.Note;
import com.wishnu.notetaking.exception.NoteValidationException;
import com.wishnu.notetaking.repositories.NoteRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements  NoteService{

    NoteRepository noteRepository;

    @Override
    public List<Note> getAllNotes() {
        List<Note> notes = noteRepository.findAll();
        Collections.reverse(notes);
        return notes;
    }

    @Override
    public Note getNote(String id) {
        return noteRepository.findById(id).stream().findFirst().orElse(null);
    }

    @Override
    public void saveNote(String description) {
        if(StringUtils.isBlank(description)){
            throw new NoteValidationException("Description must not be empty");
        }
        Node document = Parser.builder().build().parse(description.trim());
        String html = HtmlRenderer.builder().build().render(document);
        noteRepository.save(new Note(null, html));
    }

    @Override
    public Note updateNote(Note note) {
       noteRepository.save(note);
       return noteRepository.findById(note.getId()).stream().findFirst().orElse(null);
    }
}
