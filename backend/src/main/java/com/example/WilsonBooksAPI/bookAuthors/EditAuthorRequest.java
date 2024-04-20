package com.example.WilsonBooksAPI.bookAuthors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditAuthorRequest {
    private Long id;
    private String authorName;
    private String bio;
}

