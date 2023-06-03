package com.TourGuide.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;

import com.TourGuide.util.Status;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Report")
public class ReportController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReportController() {
        super();
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final var session = request.getSession();

        // HACK: tried to store the userId again in the session using the cookie but it
        // didn't work
        // var userId = "";
        // Cookie[] cookies = request.getCookies();
        // if (cookies != null) {
        // for (Cookie cookie : cookies) {
        // if (cookie.getName().equals("userId")) {
        // userId = cookie.getValue();
        // break;
        // }
        // }
        // }

        // if (userId != null && !userId.isEmpty()) {
        // session.setAttribute("postId", userId.trim());
        // final var post = new Cookie("postId", userId.trim());
        // post.setMaxAge(60 * 60 * 24 * 365);
        // response.addCookie(post);
        // }

        final var postId = request.getParameter("postId");
        if (postId != null && !postId.isEmpty()) {
            session.setAttribute("postId", postId.trim());
            final var post = new Cookie("postId", postId.trim());
            post.setMaxAge(60 * 60);
            response.addCookie(post);
        }

        response.setContentType("text/html");
        request.getRequestDispatcher("report.jsp").forward(request, response);
    }

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final var stats = new Status();

        final var session = request.getSession();

        // FIXME: couldn't get the userId from the session or the cookie for some reason
        final var userId = "1";
        // final var userId = (String) session.getAttribute("userId");
        final var postId = (String) session.getAttribute("postId");

        if (session.getAttribute("postId") == null) {
            response.sendRedirect("/Tour");
            return;
        }

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

        final var url = "jdbc:mysql://localhost:3306/";
        final var username = "admin";
        final var password = "password";
        final var database = "TourGuide";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final var connection = DriverManager.getConnection(url + database, username, password);

            final var sql = "INSERT INTO report (userid, postid, date, phonenumber, country, city, mediaurl, violationtype) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            final var statement = connection.prepareStatement(sql);

            statement.setInt(1, Integer.parseInt(userId));
            statement.setInt(2, Integer.parseInt(postId));
            statement.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
            statement.setString(4, phone);
            statement.setString(5, country);
            statement.setString(6, city);
            statement.setString(7, mediaUrl);
            statement.setString(8, violationType);

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }

        content = "<p>Thank you for submitting the report.</p>";

        request.setAttribute("content", content);
        request.getRequestDispatcher("response.jsp").forward(request, response);
    }

    protected void doPut(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }
}
