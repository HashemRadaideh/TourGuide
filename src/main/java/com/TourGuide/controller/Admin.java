package com.TourGuide.controller;

import java.io.IOException;

import com.TourGuide.database.Database;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// import com.TourGuide.database.*;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Admin() {
        super();
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        var db = Database.getInstance();
        var table = db.getTable("posts");

        var content = new StringBuilder();

        content.append("<section id=\"user-reports\" class=\"container\">");
        for (int i = 0; i < 10; i++) {
            content.append(
                    "<div style=\"display: flex; justify-content: space-between; align-items: center; margin-bottom: 10px;\">");
            content.append("<span>" + (i + 1) + ". hello, world!</span>");

            content.append("<form method=\"put\" action=\"/report\" style=\"margin-left: 10px;\">");
            content.append("<input type=\"hidden\" name=\"index\" value=\"" + (i + 1) + "\" />");

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
