package com.campbackend.pagination;

import java.util.List;

import com.campbackend.modal.Duty;

public class DutyPage extends Pagination<Duty>{

    public DutyPage(int pageNumber, int pageSize, long total, List<Duty> content) {
        super(pageNumber, pageSize, total, content);
    }

}
