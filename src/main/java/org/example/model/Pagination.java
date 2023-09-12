package org.example.model;

import java.util.List;

public class Pagination<T> {
    private List<T> items;
    private int itemsPerPage;
    private int currentPage;

    public Pagination(List<T> items, int itemsPerPage) {
        this.items = items;
        this.itemsPerPage = itemsPerPage;
        this.currentPage = 1;
    }

    public List<T> getCurrentPageItems() {
        int startIndex = (currentPage - 1) * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, items.size());
        return items.subList(startIndex, endIndex);
    }

    public void nextPage() {
        if (hasNextPage()) {
            currentPage++;
        }
    }

    public void previousPage() {
        if (hasPreviousPage()) {
            currentPage--;
        }
    }

    public boolean hasNextPage() {
        return (currentPage * itemsPerPage) < items.size();
    }

    public boolean hasPreviousPage() {
        return currentPage > 1;
    }
}

