package com.TourGuide.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Admin() {
        super();
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        final var content = new StringBuilder();

        final var url = "jdbc:mysql://localhost:3306/";
        final var username = "admin";
        final var password = "password";
        final var database = "TourGuide";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final var connection = DriverManager.getConnection(url + database, username, password);

            final var prompt = connection.createStatement();

            final var query = "SELECT r.reportid, r.postid, u.username, p.content, r.country, r.city, r.mediaurl, r.violationtype "
                    +
                    "FROM report r " +
                    "JOIN user u ON r.userid = u.id " +
                    "JOIN post p ON r.postid = p.id";
            final var resultSet = prompt.executeQuery(query);

            content.append("<section id=\"user-reports\" class=\"container\">");
            while (resultSet.next()) {
                content.append(
                        "<div style=\"margin-bottom: 10px;\">");

                // Display the post content
                content.append("<span>Post Content: " + resultSet.getString("content") + "</span><br>");

                // Display the violation type
                content.append("<span>Violation Type: " + resultSet.getString("violationtype") + "</span><br>");

                // Display the reporter username
                content.append("<span>Reported by: " + resultSet.getString("username") + "</span><br>");

                content.append("<form method=\"post\" action=\"/Admin\" style=\"margin-top: 10px;\">");

                // Pass the report ID and post ID as hidden input fields
                content.append("<input type=\"hidden\" name=\"reportid\" value=\"" +
                        resultSet.getString("reportid") + "\" />");

                content.append("<input type=\"hidden\" name=\"postid\" value=\"" +
                        resultSet.getString("postid") + "\" />");

                content.append(
                        "<label><input type=\"radio\" name=\"decision\" value=\"accept\" required /> Accept </label>");

                content.append(
                        "<label><input type=\"radio\" name=\"decision\" value=\"refuse\" required /> Refuse </label>");

                content.append("<input type=\"submit\" value=\"submit\" />");
                content.append("</form>");
                content.append("</div>");
            }
            content.append("</section>");

            content.append("<section id=\"another-tab\" class=\"container\">" +
                    "Bye, World!" +
                    "</section>");

            request.setAttribute("content", content);
            request.getRequestDispatcher("admin.jsp#user-reports").forward(request, response);

            resultSet.close();
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
