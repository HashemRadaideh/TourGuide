package com.TourGuide.model;

import java.util.Date;
import java.util.Objects;

/**
 * Report
 */
public class ReportTicket {
    private String userId;
    private String postId;
    private Date date;
    private String phoneNumber;
    private String country;
    private String city;
    private String mediaUrl;
    private String violationType;

    private String reportId;

    private String hashStrings(String str1, String str2) {
        int hash = 17; // Initial hash value

        // Add the hash code of the first string
        hash = 31 * hash + Objects.hashCode(str1);

        // Add the hash code of the second string
        hash = 31 * hash + Objects.hashCode(str2);

        // Convert the hash value to a string
        return String.valueOf(hash);
    }

    public String getReportId() {
        return this.reportId;
    }

    public ReportTicket(String userId, String postId, Date date, String phoneNumber, String country, String city,
            String mediaUrl, String violationType) {
        this.country = country;
        this.userId = userId;
        this.postId = postId;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.mediaUrl = mediaUrl;
        this.violationType = violationType;
        this.reportId = hashStrings(userId, postId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String sender) {
        this.userId = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String content) {
        this.postId = content;
    }

}
