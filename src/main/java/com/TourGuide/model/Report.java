package com.TourGuide.model;

import java.util.Date;

/**
 * Report
 */
public class Report {
    private String sender;
    private Date date;
    private String phoneNumber;
    private String country;
    private String city;
    private String violationType;
    private String mediaUrl;
    private String content;

    public Report(String sender, String content, Date date, String phoneNumber, String country, String city,
            String violationType, String mediaUrl) {
        this.country = country;
        this.sender = sender;
        this.content = content;
        this.date = date;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.violationType = violationType;
        this.mediaUrl = mediaUrl;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
