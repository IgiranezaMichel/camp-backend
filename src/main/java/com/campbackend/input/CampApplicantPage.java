package com.campbackend.input;

import java.util.List;

import com.campbackend.modal.CampApplicant;
import com.campbackend.pagination.Pagination;

public class CampApplicantPage extends Pagination<CampApplicant>{

    public CampApplicantPage(int pageNumber, int pageSize, long total, List<CampApplicant> content) {
        super(pageNumber, pageSize, total, content);
    }

}
