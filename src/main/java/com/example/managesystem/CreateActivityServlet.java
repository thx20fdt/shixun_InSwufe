package com.example.managesystem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;

@WebServlet(name = "CreateActivity", value = "/CreateActivity")
public class CreateActivityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String ANAME = request.getParameter("ANAME");
        String ACONTENT = request.getParameter("ACONTENT");
        Date BINGINTIME = new Date();
        Date ENDTIME = new Date(request.getParameter("ENDTIME"));

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
