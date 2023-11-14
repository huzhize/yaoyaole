package com.huzz.yyl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoolSceneExample extends JFrame {

    private List<Star> stars;
    private List<ShootingStar> shootingStars;

    public CoolSceneExample() {
        setTitle("Cool Scene Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        stars = new ArrayList<>();
        shootingStars = new ArrayList<>();

        // Generate random stars
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(getWidth());
            int y = random.nextInt(getHeight());
            int size = random.nextInt(3) + 1;
            stars.add(new Star(x, y, size));
        }

        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateScene();
                repaint();
            }
        });
        timer.start();

        setVisible(true);
    }

    private void updateScene() {
        // Update star twinkling
        for (Star star : stars) {
            star.twinkle();
        }

        // Add occasional shooting stars
        Random random = new Random();
        if (random.nextDouble() < 0.02) {
            int startX = random.nextInt(getWidth());
            int startY = random.nextInt(getHeight() / 2);
            shootingStars.add(new ShootingStar(startX, startY));
        }

        // Update shooting stars
        for (ShootingStar shootingStar : shootingStars) {
            shootingStar.move();
        }

        // Remove shooting stars that have moved off-screen
        shootingStars.removeIf(ShootingStar::isOffScreen);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw background
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw stars
        g2d.setColor(Color.WHITE);
        for (Star star : stars) {
            g2d.fillOval(star.getX(), star.getY(), star.getSize(), star.getSize());
        }

        // Draw shooting stars
        g2d.setColor(Color.CYAN);
        for (ShootingStar shootingStar : shootingStars) {
            g2d.drawLine(shootingStar.getX(), shootingStar.getY(),
                    shootingStar.getX() + shootingStar.getLength(), shootingStar.getY());
        }
    }

    private class Star {
        private int x;
        private int y;
        private int size;
        private boolean twinkling;

        public Star(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.twinkling = false;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSize() {
            return size;
        }

        public void twinkle() {
            if (Math.random() < 0.02) {
                twinkling = !twinkling;
            }
        }
    }

    private class ShootingStar {
        private int x;
        private int y;
        private int length;

        public ShootingStar(int x, int y) {
            this.x = x;
            this.y = y;
            this.length = 30;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getLength() {
            return length;
        }

        public void move() {
            x += 5;
            y += 2;
        }

        public boolean isOffScreen() {
            return x > getWidth() || y > getHeight();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CoolSceneExample();
            }
        });
    }
}
