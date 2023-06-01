package com.TourGuide.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Report")
public class Report extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Report() {
        super();
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("report.jsp").forward(request, response);
    }

    private class Status {
        private boolean isNumber = false;

        public boolean isNumber() {
            return isNumber;
        }

        public void setNumber(final boolean isNumber) {
            this.isNumber = isNumber;
        }

        private boolean isCountry = false;

        public boolean isCountry() {
            return isCountry;
        }

        public void setCountry(final boolean isCountry) {
            this.isCountry = isCountry;
        }

        private boolean isCity = false;

        public boolean isCity() {
            return isCity;
        }

        public void setCity(final boolean isCity) {
            this.isCity = isCity;
        }

        private boolean isViolation = false;

        public boolean isViolation() {
            return isViolation;
        }

        public void setViolation(final boolean isViolation) {
            this.isViolation = isViolation;
        }
    }

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final var stats = new Status();

        var content = "<h1>Form Submission Failed</h1><p>Please fill in all the required fields and try again.</p>";
        content += "<ul>";

        final var phoneNumber = request.getParameter("phoneNumber");
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            stats.setNumber(true);
            content += "<li>" + phoneNumber + "</li>";
        }

        final var country = request.getParameter("country");
        if (country == null || country.isEmpty()) {
            stats.setCountry(true);
            content += "<li>" + country + "</li>";
        }

        final var city = request.getParameter("city");
        if (city == null || city.isEmpty()) {
            stats.setCity(true);
            content += "<li>" + city + "</li>";
        }

        final var violationType = request.getParameter("violationType");
        if (violationType == null || violationType.isEmpty()) {
            stats.setViolation(true);
            content += "<li>" + violationType + "</li>";
        }

        content += "</ul>";

        if (stats.isNumber() || stats.isCountry() || stats.isCity() || stats.isViolation()) {
            request.setAttribute("dynamicContent", content);
            request.getRequestDispatcher("response.jsp").forward(request, response);
            return;
        }

        content = "<h1>Form Submission Successful</h1><p>Thank you for submitting the form.</p>";

        request.setAttribute("dynamicContent", content);
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }

    protected void doPut(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }
}
