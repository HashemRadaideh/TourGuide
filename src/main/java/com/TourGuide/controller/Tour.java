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

            final var statement = connection.createStatement();

            final var query = "SELECT p.*, u.username, " +
                    "COUNT(CASE WHEN p.status = 1 THEN 1 END) AS trafficViolations, " +
                    "COUNT(CASE WHEN p.status = 2 THEN 1 END) AS stopViolations, " +
                    "COUNT(CASE WHEN p.status = 3 THEN 1 END) AS jaywalkingViolations, " +
                    "COUNT(CASE WHEN p.status = 4 THEN 1 END) AS litteringViolations " +
                    "FROM post p JOIN user u ON p.ownerid = u.id GROUP BY p.countryname, p.cityname";
            final var resultSet = statement.executeQuery(query);

            content.append("<div class=\"block\">");
            while (resultSet.next()) {
                content.append("<h2>u/" + resultSet.getString("username") + "</h2>");
                content.append("<p>" + resultSet.getString("content") + "</p>");

                content.append(
                        "<h1>" + resultSet.getString("cityname") + ", " + resultSet.getString("countryname") + "</h1>");

                int litteringViolations = resultSet.getInt("littercount");
                String cleanliness = (litteringViolations >= 10) ? "Dirty" : "Clean";
                content.append("<p>Cleanliness: " + cleanliness + "</p>");

                int trafficViolations = resultSet.getInt("trafficcount");
                int stopViolations = resultSet.getInt("stopcount");
                String safety = ((trafficViolations >= 10) || (stopViolations >= 10)) ? "Dangerous" : "Safe";
                content.append("<p>Safety: " + safety + "</p>");

                int jaywalkingViolations = resultSet.getInt("jaywalkcount");
                String sanity = (jaywalkingViolations >= 10) ? "Insane" : "Sane";
                content.append("<p>Sanity of Residents: " + sanity + "</p>");

                content.append("<a href=\"/Report?postId=" + resultSet.getInt("id") + "\" class=\"button\">Report</a>");
            }
            content.append("</div>");

            request.setAttribute("content", content.toString());
            request.getRequestDispatcher("tour.jsp").forward(request, response);

            resultSet.close();
            statement.close();
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
