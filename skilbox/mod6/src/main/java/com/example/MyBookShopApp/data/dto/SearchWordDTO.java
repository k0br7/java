package com.example.MyBookShopApp.data.dto;

public class SearchWordDTO {
    private String example;

    public SearchWordDTO() {
    }

    public SearchWordDTO(String example) {
        this.example = example;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
