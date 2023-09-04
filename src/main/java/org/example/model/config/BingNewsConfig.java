package org.example.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.model.Category;

import java.util.List;

public class BingNewsConfig {
    private List<Category> categories;

    @JsonProperty("categories")
    public List<Category> getCategories() { return categories; }
    @JsonProperty("categories")
    public void setCategories(List<Category> value) { this.categories = value; }
}
