package com.campbackend.input;

import lombok.Data;

@Data
public class PageInput {
private String sort;
private int pageNumber;
private int pageSize;
}
