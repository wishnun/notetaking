package com.wishnu.notetaking.web;

import com.wishnu.notetaking.entity.Note;
import com.wishnu.notetaking.service.NoteService;
import lombok.AllArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Controller
@AllArgsConstructor
public class AppController {

    NoteService noteService;


    @GetMapping({"/","/index"})
    public String index(Model model) {
        getAllNotes(model);
        return "index";
    }

    private void getAllNotes(Model model) {
        List<Note> notes = noteService.getAllNotes();
        Collections.reverse(notes);
        model.addAttribute("notes", notes);
    }

    @PostMapping("/save-note")
    public String saveNotes(@RequestParam("image") MultipartFile file,
                            @RequestParam String description,
                            @RequestParam(required = false) String publish,
                            @RequestParam(required = false) String upload,
                            Model model) throws IOException {

        if (publish != null && publish.equals("Publish")) {
            saveNote(description, model);
            getAllNotes(model);
            return "redirect:/";
        }
        // After save fetch all notes again
        return "index";
    }

    private void saveNote(String description, Model model) {
        if (description != null && !description.trim().isEmpty()) {
            //We need to translate markup to HTML
            Node document = Parser.builder().build().parse(description.trim());
            String html = HtmlRenderer.builder().build().render(document);
            noteService.saveNote(html);
            //After publish you need to clean up the textarea
            model.addAttribute("description", "");
        }
    }
}
