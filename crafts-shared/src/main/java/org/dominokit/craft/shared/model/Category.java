package org.dominokit.craft.shared.model;

public class Category {
    private final String title;
    private final String token;

    public Category(String title, String token) {
        this.title = title;
        this.token = token;
    }

    public String getTitle() {
        return title;
    }

    public String getToken() {
        return token;
    }
}
