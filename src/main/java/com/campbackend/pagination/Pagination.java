package com.campbackend.pagination;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class Pagination<T>{
private int pageNumber;
private int pageSize;
private long total;
private List<T> content;
}
