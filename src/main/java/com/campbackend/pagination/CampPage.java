package com.campbackend.pagination;

import java.util.List;

import com.campbackend.modal.Camp;

public class CampPage extends Pagination<Camp>{

    public CampPage(int pageNumber, int pageSize, long total, List<Camp> content) {
        super(pageNumber, pageSize, total, content);
    }

}
