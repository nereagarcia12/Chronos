package com.chronos.adservice.dto;

public class FilterSearchDto {

    private String word;
    private Integer categoryId;


    public FilterSearchDto(String word, Integer categoryId) {
        this.word = word;
        this.categoryId = categoryId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}
