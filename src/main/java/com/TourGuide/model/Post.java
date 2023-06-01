package com.TourGuide.model;

/**
 * Post
 */
public class Post {
    private String username;
    private String content;
    private boolean visible;
    private String category;

    public Post(String username, String content, boolean visible, String category) {
        this.username = username;
        this.content = content;
        this.visible = visible;
        this.category = category;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

    public boolean isVisible() {
        return visible;
    }

    public String getCategory() {
        return category;
    }
}
