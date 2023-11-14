package com.huzz.yyl;

import javax.swing.*;
import java.awt.*;

public class SunsetSceneExample extends JFrame {

    public SunsetSceneExample() {
        setTitle("Sunset Scene Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw sky gradient
        drawSky(g2d);

        // Draw sun
        drawSun(g2d);

        // Draw landscape
        drawLandscape(g2d);
    }

    private void drawSky(Graphics2D g2d) {
        Color skyStartColor = new Color(255, 165, 0); // Orange
        Color skyEndColor = new Color(0, 0, 128); // Dark Blue

        GradientPaint gradient = new GradientPaint(0, 0, skyStartColor, 0, getHeight(), skyEndColor);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawSun(Graphics2D g2d) {
        int sunSize = 80;
        int sunX = getWidth() / 2 - sunSize / 2;
        int sunY = getHeight() / 2 - sunSize / 2;

        g2d.setColor(Color.YELLOW);
        g2d.fillOval(sunX, sunY, sunSize, sunSize);
    }

    private void drawLandscape(Graphics2D g2d) {
        // You can customize the landscape here
        g2d.setColor(new Color(34, 139, 34)); // Green
        g2d.fillRect(0, getHeight() / 2, getWidth(), getHeight() / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SunsetSceneExample();
            }
        });
    }
}
