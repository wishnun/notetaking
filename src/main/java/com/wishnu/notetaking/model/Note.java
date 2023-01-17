package com.wishnu.notetaking.model;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note implements Serializable {
    private UUID id;
    private String description;
}
