package com.TourGuide.model;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class TourModel {
    public String getTourDetails() {
        StringBuilder content = new StringBuilder();

        final String url = "jdbc:mysql://localhost:3306/";
        final String db_username = "admin";
        final String db_password = "password";
        final String database = "TourGuide";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url + database, db_username, db_password);

            Statement statement = connection.createStatement();

            String query = "SELECT p.*, u.username, " +
                    "COUNT(CASE WHEN p.status = 1 THEN 1 END) AS trafficViolations, " +
                    "COUNT(CASE WHEN p.status = 2 THEN 1 END) AS stopViolations, " +
                    "COUNT(CASE WHEN p.status = 3 THEN 1 END) AS jaywalkingViolations, " +
                    "COUNT(CASE WHEN p.status = 4 THEN 1 END) AS litteringViolations " +
                    "FROM post p JOIN user u ON p.ownerid = u.id GROUP BY p.countryname, p.cityname";
            ResultSet resultSet = statement.executeQuery(query);

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

            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            return "Error: " + e.getMessage();
        }

        return content.toString();
    }
}
