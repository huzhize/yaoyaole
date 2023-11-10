package com.huzz.yyl;

import javax.swing.*;

public class Spider {
    public static void main(String[] args) throws Exception{
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(10, 10);
        jFrame.setUndecorated(true);
        jFrame.setMenuBar(null);
        // 使用JLabel显示图片
        ImageIcon imageIcon = new ImageIcon("D:\\test\\20.png");
        JLabel imageLabel = new JLabel(imageIcon);

        imageLabel.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空
        jFrame.add(imageLabel);
        jFrame.setVisible(true);

        int a = 200/2;
        int x=0;int y=400;
        int time = 0;
        int jiange = 5;
        for(int i=0;i<1000;i++){
            jFrame.setLocation(x,y);
            Thread.sleep(jiange);
            time+=jiange;
            y += time*time/5000;//
        }

    }
}
