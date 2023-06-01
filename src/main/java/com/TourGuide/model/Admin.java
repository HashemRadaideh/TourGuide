package com.TourGuide.actor;

/**
 * Admin
 */
public class Admin {
    private static final Admin instance = new Admin();

    private Admin() {
    }

    public static Admin getInstance() {
        return instance;
    }

}
