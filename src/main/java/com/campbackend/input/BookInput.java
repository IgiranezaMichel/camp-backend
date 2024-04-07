package com.campbackend.input;

import com.campbackend.modal.Book;

import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
public class BookInput extends Book{
@Getter @Setter
private String base64File;
@Getter @Setter
private String base64Cover;
@Getter @Setter
private UUID levelId;
}
