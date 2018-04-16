package com.example.lledent.outerspacemanager;

import java.util.List;

/**
 * Created by lledent on 16/04/2018.
 */

public class SearchesListResponse {
    private int size;
    private List<Search> searches;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Search> getSearches() {
        return searches;
    }

    public void setSearches(List<Search> searches) {
        this.searches = searches;
    }

    public SearchesListResponse(int size, List<Search> searches) {
        this.size = size;
        this.searches = searches;
    }
}
