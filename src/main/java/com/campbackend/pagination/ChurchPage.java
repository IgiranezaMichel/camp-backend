package com.campbackend.pagination;

import java.util.List;

import com.campbackend.modal.Church;

public class ChurchPage extends Pagination<Church>{

    public ChurchPage(int pageNumber, int pageSize, long total, List<Church> content) {
        super(pageNumber, pageSize, total, content);
    }

}
