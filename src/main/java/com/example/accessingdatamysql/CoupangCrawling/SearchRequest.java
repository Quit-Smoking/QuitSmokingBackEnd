package com.example.accessingdatamysql.CoupangCrawling;

public class SearchRequest {
    private String keyword;
    private Integer limit;
    private Integer page;

    public String getKeyword() {
        return keyword;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getPage() {
        return page;
    }
}
