package com.TourGuide.controller;

import java.io.IOException;

import com.TourGuide.model.TourModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Tour")
public class TourController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TourController() {
        super();
    }

    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        TourModel tourModel = new TourModel();
        String content = tourModel.getTourDetails();
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
