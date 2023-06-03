package com.TourGuide.model;

/**
 * Post
 */
public class Post {
    private String username;
    private String content;
    private int reportCount;

    public Post(String username, String content, boolean visible, String category) {
        this.username = username;
        this.content = content;
    }

    public void addReport() {
        this.reportCount++;
    }

    public int getReportCount() {
        return reportCount;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }

}
