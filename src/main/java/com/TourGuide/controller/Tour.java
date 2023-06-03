package com.TourGuide.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Tour")
public class Tour extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Tour() {
        super();
    }

protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html");

    final var content = new StringBuilder();

    final var url = "jdbc:mysql://localhost:3306/";
    final var db_username = "admin";
    final var db_password = "password";
    final var database = "TourGuide";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");

        final var connection = DriverManager.getConnection(url + database, db_username, db_password);

        final var prompt = connection.createStatement();

        final var query = "SELECT p.*, u.username FROM post p JOIN user u ON p.ownerid = u.id";
        final var post = prompt.executeQuery(query);

        while (post.next()) {
            content.append("<div class=\"block\">");
            content.append("<h1>" + post.getString("username") + "</h1>"); // Display username
            content.append("<p>" + post.getString("content") + "</p>");
            content.append("<a href=\"/Report?postId=" + post.getInt("id") + "\" class=\"button\">Report</a>");
            content.append("</div>");
        }

        request.setAttribute("content", content.toString());
        request.getRequestDispatcher("tour.jsp").forward(request, response);

        post.close();
        prompt.close();
        connection.close();
    } catch (ClassNotFoundException | SQLException e) {
        response.getWriter().println("Error: " + e.getMessage());
    }
}

    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPut(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }
}
