package com.wishnu.notetaking.repositories;

import com.wishnu.notetaking.entity.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NoteRepository extends MongoRepository<Note,String> {
}
