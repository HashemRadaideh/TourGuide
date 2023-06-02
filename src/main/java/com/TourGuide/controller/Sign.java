package com.TourGuide.controller;

import java.io.IOException;

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
        var session = request.getSession();
        session.invalidate();

        // Delete the "loggedIn" cookie
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

        if (username.equals("user") && password.equals("1234")) {
            response.sendRedirect("/Home");
        } else if (username.equals("admin") && password.equals("1234")) {
            response.sendRedirect("/Admin");
        } else {
            request.setAttribute("msg", "Authentication failure.");

            final var dispatcher = request.getRequestDispatcher("sign.jsp#in");
            dispatcher.forward(request, response);
            return;
        }

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
    }

    protected void doPut(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doDelete(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
    }
}
