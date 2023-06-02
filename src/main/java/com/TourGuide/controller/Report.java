package com.TourGuide.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.TourGuide.database.Database;
import com.TourGuide.model.ReportTicket;
import com.TourGuide.model.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
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
        final var session = request.getSession();

        final var userId = request.getParameter("userId");
        if (userId != null && !userId.isEmpty()) { // Remove the logical NOT operator here
            session.setAttribute("userId", userId.trim());
            final var user = new Cookie("userId", userId.trim());
            user.setMaxAge(60 * 60);
            response.addCookie(user);
        }

        final var postId = request.getParameter("postId");
        if (postId != null && !postId.isEmpty()) { // Remove the logical NOT operator here
            session.setAttribute("postId", postId.trim());
            final var post = new Cookie("postId", postId.trim());
            post.setMaxAge(60 * 60);
            response.addCookie(post);
        }

        // response.getWriter().println("userId value: " + userId + "\npostId value: " +
        // postId);

        response.setContentType("text/html");
        request.getRequestDispatcher("report.jsp").forward(request, response);
    }

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final var stats = new Status();

        final var session = request.getSession();

        if (session.getAttribute("userId") == null) {
            response.sendRedirect("/Tour");
        }

        if (session.getAttribute("postId") == null) {
            response.sendRedirect("/Tour");
        }

        final var userId = (String) session.getAttribute("userId");
        final var postId = (String) session.getAttribute("postId");

        // String userId = "";
        // String postId = "";

        // // Check if the "remember me" cookie is present
        // Cookie[] cookies = request.getCookies();
        // if (cookies != null) {
        // for (Cookie cookie : cookies) {
        // if (cookie.getName().equals("userId"))
        // userId = cookie.getValue();
        // if (cookie.getName().equals("postId"))
        // userId = cookie.getValue();
        // }
        // }

        var content = "<p>Form Submission Failed</p><p>Please fill in all the required fields and try again.</p>";
        content += "<ul>";

        final var dateString = request.getParameter("time");
        if (dateString == null || dateString.isEmpty()) {
            stats.setDate(false);
            content += "<li> The Date </li>";
        }

        final var phone = request.getParameter("phone");
        if (phone == null || phone.isEmpty()) {
            stats.setNumber(false);
            content += "<li> The phone number </li>";
        }

        final var country = request.getParameter("country");
        if (country == null || country.isEmpty()) {
            stats.setCountry(false);
            content += "<li> The country</li>";
        }

        final var city = request.getParameter("city");
        if (city == null || city.isEmpty()) {
            stats.setCity(false);
            content += "<li> The city </li>";
        }

        final var mediaUrl = request.getParameter("media");
        if (mediaUrl == null || mediaUrl.isEmpty()) {
            content += "<li> The Media Url </li>";
        }

        final var violationType = request.getParameter("violation");
        if (violationType == null || violationType.isEmpty()) {
            stats.setViolation(false);
            content += "<li> The violation type </li>";
        }

        if (!stats.isDate() || !stats.isNumber() || !stats.isCountry() || !stats.isCity() || !stats.isViolation()) {
            content += "</ul>";
            request.setAttribute("content", content);
            request.getRequestDispatcher("response.jsp").forward(request, response);
            return;
        }

        content = "<p>Thank you for submitting the form.</p>";
        content += "<ul>";
        content += "<li>" + userId + "</li>";
        content += "<li>" + postId + "</li>";
        content += "<li>" + dateString + "</li>";
        content += "<li>" + phone + "</li>";
        content += "<li>" + country + "</li>";
        content += "<li>" + city + "</li>";
        content += "<li>" + mediaUrl + "</li>";
        content += "<li>" + violationType + "</li>";
        content += "</ul>";

        request.setAttribute("content", content);
        request.getRequestDispatcher("response.jsp").forward(request, response);

        var sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        var db = Database.getInstance();
        var table = db.getTable("reports");
        table.addData(new ReportTicket(
                userId,
                postId,
                date,
                phone,
                country,
                city,
                mediaUrl,
                violationType));
    }

    protected void doPut(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }
}
