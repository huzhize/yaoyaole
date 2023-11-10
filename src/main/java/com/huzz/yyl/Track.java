package com.huzz.yyl;

import java.util.Random;

public class Track {
    private static Random random;
    public static void main(String[] args) {
        random = new Random();
        for (int i = 0; i < 10; i++) {
            double x = random.nextDouble() *200;
            double y = random.nextDouble() *200;
            double angle = 2.0 * Math.PI * random.nextDouble();
            double speed = random.nextDouble() * 5 + 1;
            System.out.println(x+" "+y+" "+ angle+" "+speed);
        }
    }

}
