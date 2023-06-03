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

        var content = new StringBuilder();

        var url = "jdbc:mysql://localhost:3306/";
        var username = "admin";
        var password = "password";
        var database = "TourGuide";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            var connection = DriverManager.getConnection(url + database, username, password);

            var prompt = connection.createStatement();

            var query = "SELECT * FROM report";
            var resultSet = prompt.executeQuery(query);

            content.append("<section id=\"user-reports\" class=\"container\">");
            while (resultSet.next()) {
                content.append(
                        "<div style=\"display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;\">");
                content.append("<span>" + resultSet.getString("reportid") + "</span>");

                content.append("<form method=\"put\" action=\"/Report\" style=\"margin-left: 10px;\">");
                content.append(
                        "<input type=\"hidden\" name=\"postid\" value=\"" + resultSet.getString("postid") + "\" />");

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
            request.getRequestDispatcher("admin.jsp").forward(request, response);

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
