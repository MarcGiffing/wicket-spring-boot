package com.giffing.wicket.spring.boot.example.repository;

public abstract class DefaultFilter implements Filter {

    private Sort sort;

    private boolean ascending;

    @Override
    public Sort sort() {
        return sort;
    }

    @Override
    public boolean isAscending() {
        return ascending;
    }

    @Override
    public void setSort(Sort sort, boolean ascending) {
        this.sort = sort;
        this.ascending = ascending;
    }

}
