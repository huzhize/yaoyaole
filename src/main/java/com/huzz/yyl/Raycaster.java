package com.huzz.yyl;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Raycaster extends JPanel {

    private ArrayList<Ray> rays;
    private static Random random;

    public Raycaster() {
        rays = new ArrayList<>();
        random = new Random();
        // 添加10个随机射线的初始位置和方向
        for (int i = 0; i < 10; i++) {
            double x = random.nextDouble() * getWidth();
            double y = random.nextDouble() * getHeight();
            double angle = 2.0 * Math.PI * random.nextDouble();
            double speed = random.nextDouble() * 5 + 1;
            System.out.println(x+" "+y+" "+ angle+" "+speed);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        // 绘制射线
        for (Ray ray : rays) {
            ray.draw(g2d);
        }
        // 更新射线位置并重新绘制画面
        rays.forEach(ray -> ray.update(getBounds()));
        repaint();
    }

    private static class Ray {
        private double x;
        private double y;
        private double angle;
        private double speed;
        private Color color;

        public Ray(double x, double y, double angle, double speed) {
            this.x = x;
            this.y = y;
            this.angle = angle;
            this.speed = speed;
            this.color = Color.BLUE;
        }

        public void draw(Graphics2D g2d) {
            g2d.setColor(color);
            g2d.drawLine((int) x, (int) y, (int) (x + speed * Math.cos(angle)), (int) (y + speed * Math.sin(angle)));
        }

        public void update(Rectangle bounds) {
            x += speed * Math.cos(angle);
            y += speed * Math.sin(angle);
            // 如果射线超出窗口范围，重新设置射线的初始位置和方向
            if (x + speed * Math.cos(angle) < 0 || x + speed * Math.cos(angle) > bounds.width || y + speed * Math.sin(angle) < 0 || y + speed * Math.sin(angle) > bounds.height) {
                double newX = random.nextDouble() * bounds.width;
                double newY = random.nextDouble() * bounds.height;
                double newAngle = 2.0 * Math.PI * random.nextDouble();
                double newSpeed = random.nextDouble() * 5 + 1;
                x = newX;
                y = newY;
                angle = newAngle;
                speed = newSpeed;
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Raycaster");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new Raycaster());
        frame.setVisible(true);
    }
}