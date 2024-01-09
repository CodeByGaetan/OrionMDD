package com.openclassrooms.mddapi.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PageResponse<T> {
    public List<T> items;

    public Integer totalItems;
}
