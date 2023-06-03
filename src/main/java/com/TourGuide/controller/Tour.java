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

        var content = new StringBuilder();

        var url = "jdbc:mysql://localhost:3306/";
        var username = "admin";
        var password = "password";
        var database = "TourGuide";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            var connection = DriverManager.getConnection(url + database, username, password);

            var prompt = connection.createStatement();

            var query = "SELECT * FROM post";
            var post = prompt.executeQuery(query);

            while (post.next()) {
                content.append("<div class=\"block\">");
                content.append("<h1>" + post.getString("username") + "</h1>");
                content.append("<p>" + post.getString("content") + "</p>");
                content.append("<a href=\"/Report?postId=" + post.getInt("id") +
                        "\" class=\"button\">Report</a>");
                content.append("</div>");
            }

            request.setAttribute("content", content);
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
