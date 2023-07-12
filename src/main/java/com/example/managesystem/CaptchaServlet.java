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

@WebServlet("/captcha")
public class CaptchaServlet extends HttpServlet {
    private int width = 90;
    private int height = 35;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("image/jpeg");

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bufferedImage.createGraphics();
        g.setFont(new Font("Arial", Font.BOLD, 25));

        g.setColor(Color.LIGHT_GRAY); // 设置新的背景颜色
        g.fillRect(0, 0, width, height); // 使用新的背景颜色填充图片

        Random r = new Random();
        String captcha = "";
        for (int i = 0; i < 4; i++) {
            char c = (char) (r.nextBoolean() ? r.nextInt(26) + 97 : r.nextInt(10) + 48);
            captcha += c;
            g.setColor(new Color(50 + r.nextInt(100), 50 + r.nextInt(100), 50 + r.nextInt(100)));
            g.drawString(c + "", i * (width / 4), height - 5);
        }

        HttpSession session = req.getSession(true);
        session.setAttribute("captcha", captcha);

        g.dispose();
        ImageIO.write(bufferedImage, "jpeg", resp.getOutputStream());
    }
}

