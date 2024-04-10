package com.campbackend.pagination;

import java.util.List;

import com.campbackend.modal.AccountHolder;

public class AccountHolderPage extends Pagination<AccountHolder>{

    public AccountHolderPage(int pageNumber, int pageSize, long total, List<AccountHolder> content) {
        super(pageNumber, pageSize, total, content);
    }

}
