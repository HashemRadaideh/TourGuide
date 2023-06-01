<jsp:directive.page language="java" import="com.TourGuide.database.*" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" />

<jsp:scriptlet>
    String redirectURL;

    // Check if the user has an active session
    if (session.getAttribute("loggedIn") != null) {
    redirectURL = "/Home";
    } else {
    // Check if the "remember me" cookie is present
    Cookie[] cookies = request.getCookies();
    boolean rememberMeCookieExists = false;
    if (cookies != null) {
    for (Cookie cookie : cookies) {
    if (cookie.getName().equals("rememberMe") && cookie.getValue().equals("true")) {
    rememberMeCookieExists = true;
    break;
    }
    }
    }

    redirectURL = rememberMeCookieExists ? "/Home" : "/Sign";
    }

    response.sendRedirect(redirectURL);
</jsp:scriptlet>
