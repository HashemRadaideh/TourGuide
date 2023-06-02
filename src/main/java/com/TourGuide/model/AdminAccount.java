package com.TourGuide.model;

/**
 * Admin
 */
public class AdminAccount {
    private static final AdminAccount instance = new AdminAccount();

    private AdminAccount() {
    }

    public static AdminAccount getInstance() {
        return instance;
    }

}
