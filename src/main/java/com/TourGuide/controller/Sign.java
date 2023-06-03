package com.TourGuide.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Sign")
public class Sign extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Sign() {
        super();
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        final var session = request.getSession();
        session.invalidate();

        // Delete the "loggedIn" cookie
        final Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (final Cookie cookie : cookies) {
                if (cookie.getName().equals("loggedIn")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        request.getRequestDispatcher("sign.jsp").forward(request, response);
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        final var username = request.getParameter("username");
        final var password = request.getParameter("password");
        final var remember = request.getParameter("remember");
        final var session = request.getSession();

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("msg", "Authentication failure.");

            final var dispatcher = request.getRequestDispatcher("sign.jsp#in");
            dispatcher.forward(request, response);
            return;
        }

        final var url = "jdbc:mysql://localhost:3306/";
        final var db_username = "admin";
        final var db_password = "password";
        final var database = "TourGuide";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            final var connection = DriverManager.getConnection(url + database, db_username, db_password);

            final var prompt = connection.createStatement();

            final var query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password
                    + "'";
            final var loggedIn = prompt.executeQuery(query);

            if (loggedIn.next()) { // Check if result set has rows
                if (remember != null) {
                    session.setAttribute("loggedIn", remember.trim());
                    final var rememberMe = new Cookie("rememberMe", remember.trim());
                    session.setAttribute("loggedUser", username.trim());
                    final var name = new Cookie("username", username.trim());

                    rememberMe.setMaxAge(60 * 60 * 24 * 365);
                    name.setMaxAge(60 * 60 * 24 * 365);

                    response.addCookie(rememberMe);
                    response.addCookie(name);
                }

                session.setAttribute("userId", loggedIn.getInt("id"));

                final var logedIn = new Cookie("userId", "" + loggedIn.getInt("id"));
                logedIn.setMaxAge(60 * 60 * 24 * 365);

                response.sendRedirect("/Home");

                loggedIn.close();
                prompt.close();
                connection.close();
                return;
            }

            loggedIn.close();

            final var adminQuery = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password
                    + "'";

            final var adminResult = prompt.executeQuery(adminQuery);

            if (adminResult.next()) {
                if (remember != null) {
                    session.setAttribute("loggedIn", remember.trim());
                    final var rememberMe = new Cookie("rememberMe", remember.trim());
                    session.setAttribute("loggedUser", username.trim());
                    final var name = new Cookie("username", username.trim());

                    rememberMe.setMaxAge(60 * 60 * 24 * 365);
                    name.setMaxAge(60 * 60 * 24 * 365);

                    response.addCookie(rememberMe);
                    response.addCookie(name);
                }

                session.setAttribute("adminId", adminResult.getInt("id"));

                response.sendRedirect("/Admin");

                adminResult.close();
                prompt.close();
                connection.close();
                return;
            }

            adminResult.close();

            request.setAttribute("msg", "Authentication failure.");

            request.getRequestDispatcher("sign.jsp#in").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }

    protected void doPut(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }
}
