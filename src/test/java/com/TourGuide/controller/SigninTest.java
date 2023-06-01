package com.TourGuide.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class SigninTest {
    Sign signin;
    HttpServletRequest request;
    HttpServletResponse response;

    RequestDispatcher dispatcher;
    HttpSession session;

    @BeforeEach
    void setup() {
        signin = new Sign();

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        dispatcher = mock(RequestDispatcher.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        // when(request.getRequestDispatcher("index.jsp")).thenReturn(dispatcher);

        // signin.doGet(request, response);

        // verify(request, times(1)).getRequestDispatcher("index.jsp");
        // verify(dispatcher, times(1)).forward(request, response);
    }

    @Test
    public void testDoPost() throws ServletException, IOException {
        // when(request.getSession()).thenReturn(session);
        // when(request.getContextPath()).thenReturn("/Signin");

        // signin.doPost(request, response);

        // verify(response, times(1)).setContentType("text/html");
        // verify(response.getWriter(), times(1)).append("Served at: /app<h1>Bye,
        // World!</h1>");
    }
}
