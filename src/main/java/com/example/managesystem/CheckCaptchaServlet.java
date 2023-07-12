package com.example.managesystem;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
@WebServlet("/checkCaptcha")
public class CheckCaptchaServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String captcha = req.getParameter("captcha");
        HttpSession session = req.getSession();
        String correctCaptcha = (String) session.getAttribute("captcha");
        resp.getWriter().write(captcha.equals(correctCaptcha) ? "true" : "false");
    }
}

