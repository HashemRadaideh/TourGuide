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
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminController() {
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

            final var query = "SELECT r.reportid, r.postid, u.username, p.content, r.country, r.city, r.mediaurl, r.violationtype, r.reportdate "
                    +
                    "FROM report r " +
                    "JOIN user u ON r.userid = u.id " +
                    "JOIN post p ON r.postid = p.id " +
                    "WHERE r.isactive = 1 AND r.reportdate >= CURDATE() - INTERVAL 1 MONTH";
            final var resultSet = prompt.executeQuery(query);

            content.append("<section id=\"user-reports\" class=\"container\">");
            while (resultSet.next()) {
                content.append("<div style=\"margin-bottom: 10px;\">");

                content.append("<span>Post Content: " + resultSet.getString("content") + "</span><br>");

                content.append("<span>Violation Type: " + resultSet.getString("violationtype") + "</span><br>");

                content.append("<span>Reported by: " + resultSet.getString("username") + "</span><br>");

                content.append("<form method=\"post\" action=\"/Admin\" style=\"margin-top: 10px;\">");

                content.append("<input type=\"hidden\" name=\"reportid\" value=\"" +
                        resultSet.getString("reportid") + "\" />");

                content.append("<input type=\"hidden\" name=\"postid\" value=\"" +
                        resultSet.getString("postid") + "\" />");

                content.append("<input type=\"hidden\" name=\"violationType\" value=\"" +
                        resultSet.getString("violationtype") + "\" />");

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
        response.setContentType("text/html");

        final var url = "jdbc:mysql://localhost:3306/";
        final var username = "admin";
        final var password = "password";
        final var database = "TourGuide";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final var connection = DriverManager.getConnection(url + database, username, password);

            final var prompt = connection.createStatement();

            final var decision = request.getParameter("decision");
            final var reportId = Integer.parseInt(request.getParameter("reportid"));
            final var postid = Integer.parseInt(request.getParameter("postid"));
            final var violationType = request.getParameter("violationType");

            if (decision.equals("accept")) {
                final var updateQuery = getUpdateQueryForViolationType(violationType, postid);
                prompt.executeUpdate(updateQuery);
            }

            final var updateReportQuery = "UPDATE report SET isactive = 0 WHERE reportid = " + reportId;
            prompt.executeUpdate(updateReportQuery);

            final var falseViolationCountQuery = "SELECT COUNT(*) AS falseViolationCount " +
                    "FROM report " +
                    "WHERE userid = (SELECT userid FROM report WHERE reportid = " + reportId + ") " +
                    "AND violationtype = 'false' AND isactive = 1";
            final var resultSet = prompt.executeQuery(falseViolationCountQuery);
            resultSet.next();
            final var falseViolationCount = resultSet.getInt("falseViolationCount");
            resultSet.close();

            if (falseViolationCount >= 3) {
                final var blockUserQuery = "UPDATE user SET blocked = 1 WHERE id = (SELECT userid FROM report WHERE reportid = "
                        + reportId + ")";
                prompt.executeUpdate(blockUserQuery);
            }

            prompt.close();
            connection.close();

            response.sendRedirect("/Admin#user-reports");
        } catch (ClassNotFoundException | SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    private String getUpdateQueryForViolationType(String violationType, int postid) {
        switch (violationType) {
            case "red-light":
                return "UPDATE post SET trafficcount = trafficcount + 1 WHERE id = " + postid;
            case "stop-sign":
                return "UPDATE post SET stopcount = stopcount + 1 WHERE id = " + postid;
            case "jaywalking":
                return "UPDATE post SET jaywalkcount = jaywalkcount + 1 WHERE id = " + postid;
            case "littering":
                return "UPDATE post SET littercount = littercount + 1 WHERE id = " + postid;
            default:
                throw new IllegalArgumentException("Invalid violation type: " + violationType);
        }
    }

    protected void doPut(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }

}
