package ru.coreclass.fronttaskservice;

import java.util.List;

public class ResponseDTO {
    private List<ExampleDTO> result;
    private Integer pages;

    public ResponseDTO(List<ExampleDTO> result, Integer pages) {
        this.result = result;
        this.pages = pages;
    }

    public List<ExampleDTO> getResult() {
        return result;
    }

    public void setResult(List<ExampleDTO> result) {
        this.result = result;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
