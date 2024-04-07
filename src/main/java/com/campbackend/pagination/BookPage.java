package com.campbackend.pagination;

import java.util.List;

import com.campbackend.modal.Book;

public class BookPage extends Pagination<Book>{

    public BookPage(int pageNumber, int pageSize, long total, List<Book> content) {
        super(pageNumber, pageSize, total, content);
    }

}
