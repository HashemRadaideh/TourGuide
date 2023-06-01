package com.TourGuide.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// import com.TourGuide.database.*;

@WebServlet("/Tour")
public class Tour extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Tour() {
        super();
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // var db = Database.getInstance();
        // var table = db.getTable("Content");
        // var out = response.getWriter();

        var content = new StringBuilder();

        content.append("<div class=\"block\">");
        for (int i = 0; i < 10; i++) {
            content.append("<h1>Hello, World!</h1>");
            content.append("<a href=\"/Report\" class=\"button\">Report</a>");
        }
        content.append("</div>");

        request.setAttribute("content", content);
        request.getRequestDispatcher("tour.jsp").forward(request, response);
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
