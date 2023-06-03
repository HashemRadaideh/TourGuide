package com.TourGuide.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SignModel {

    public void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("loggedIn")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    break;
                }
            }
        }
    }

    public void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");
        HttpSession session = request.getSession();

        if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            request.setAttribute("msg", "Authentication failure.");
            request.getRequestDispatcher("sign.jsp#in").forward(request, response);
            return;
        }

        String url = "jdbc:mysql://localhost:3306/";
        String db_username = "admin";
        String db_password = "password";
        String database = "TourGuide";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url + database, db_username, db_password);
            Statement prompt = connection.createStatement();

            String query = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
            ResultSet loggedIn = prompt.executeQuery(query);

            if (loggedIn.next()) {
                if (loggedIn.getInt("blocked") == 1) {
                    request.setAttribute("msg", "User has been blocked.");
                    request.getRequestDispatcher("sign.jsp#in").forward(request, response);
                    loggedIn.close();
                    prompt.close();
                    connection.close();
                    return;
                }

                if (remember != null) {
                    session.setAttribute("loggedIn", remember.trim());
                    Cookie rememberMe = new Cookie("rememberMe", remember.trim());
                    session.setAttribute("loggedUser", username.trim());
                    Cookie name = new Cookie("username", username.trim());

                    rememberMe.setMaxAge(60 * 60 * 24 * 365);
                    name.setMaxAge(60 * 60 * 24 * 365);

                    response.addCookie(rememberMe);
                    response.addCookie(name);
                }

                session.setAttribute("userId", loggedIn.getInt("id"));
                Cookie logedIn = new Cookie("userId", "" + loggedIn.getInt("id"));
                logedIn.setMaxAge(60 * 60 * 24 * 365);

                response.sendRedirect("/Home");

                loggedIn.close();
                prompt.close();
                connection.close();
                return;
            }

            loggedIn.close();

            String adminQuery = "SELECT * FROM admin WHERE username = '" + username + "' AND password = '" + password
                    + "'";

            ResultSet adminResult = prompt.executeQuery(adminQuery);

            if (adminResult.next()) {
                if (remember != null) {
                    session.setAttribute("loggedIn", remember.trim());
                    Cookie rememberMe = new Cookie("rememberMe", remember.trim());
                    session.setAttribute("loggedUser", username.trim());
                    Cookie name = new Cookie("username", username.trim());

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
}
