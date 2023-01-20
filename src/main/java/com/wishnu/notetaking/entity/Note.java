package com.wishnu.notetaking.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "notes")
public class Note implements Serializable {
    @Id
    private String id;
    private String description;

    @Override
    public String toString() {
        return description;
    }
}
