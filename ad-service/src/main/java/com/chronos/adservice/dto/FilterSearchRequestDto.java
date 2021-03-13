package com.chronos.adservice.dto;

import java.util.Objects;

public class FilterSearchRequestDto {

    private String word;
    private Integer categoryId;

    public FilterSearchRequestDto(String word, Integer categoryId) {
        this.word = word;
        this.categoryId = categoryId;
    }

    public String getWord() {
        return word;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilterSearchRequestDto that = (FilterSearchRequestDto) o;
        return Objects.equals(word, that.word) && Objects.equals(categoryId, that.categoryId);
    }
}
