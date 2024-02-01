package com.huzz.screen;

import org.apache.commons.math3.analysis.function.Abs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 截图使用png格式比较清晰
 */
public class Main {
    public static int SCREEN_WIDTH = 1920;
    public static int SCREEN_HEIGHT = 1080;

    public static int ICON_WIDTH = 76;   //1-76 2-152 4-304
    public static int ICON_HEIGHT = 100;  //1-100

    public static int X_NUM = SCREEN_WIDTH/ICON_WIDTH;
    public static int Y_NUM = SCREEN_HEIGHT/ICON_HEIGHT;

    public static void main(String[] args) {
        try {
            //imageSplitter();
            renderScreen();
            if(true) return;

            //截取屏幕内容
            Robot robot = new Robot();
            // 获取屏幕的大小
            System.out.println(Toolkit.getDefaultToolkit().getScreenSize());
            Thread.sleep(3000);
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            // 截取屏幕的图像
            BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
            // 将图像保存到文件
            ImageIO.write(screenFullImage, "png", new File("D:\\ideaProject\\mh\\screen\\"+System.currentTimeMillis()+".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //图片切分
    public static void imageSplitter(){
        try {
            // 读取原始图片
            BufferedImage originalImage = ImageIO.read(new File("D:\\ideaProject\\mh\\screen\\test.png"));
            // 获取图片的宽度和高度
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            System.out.println(width+" "+height);
            int splitWidth = 76;
            int splitHeight = 100;

            String dir = "D:\\ideaProject\\mh\\screen\\"+System.currentTimeMillis()+"\\";
            new File(dir).mkdirs();
            for(int x=0;x<X_NUM;x++){
                for(int y=0;y<Y_NUM;y++){
                    int xp = x*splitWidth;
                    int yp = y*splitHeight;
                    System.out.println("xp:"+xp+" yp:"+yp);
                    BufferedImage splitImage = originalImage.getSubimage(xp, yp, splitWidth, splitHeight);
                    // 将切分后的图片保存到文件
                    ImageIO.write(splitImage, "png", new File(dir+x+"_" + y + ".png"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void renderScreen(){
        String dir = "D:\\ideaProject\\mh\\screen\\1706768269272\\";
        File fileDir = new File(dir);
        File[] imgArr = fileDir.listFiles();
        List<JFrame> list = new ArrayList<>();
        for(File img:imgArr){
            String name = img.getName();
            name = name.substring(0,name.lastIndexOf("."));
            String[] names = name.split("_");
            int x = Integer.valueOf(names[0]);
            int y = Integer.valueOf(names[1]);
            System.out.println(name);
            System.out.println(x);
            System.out.println(y);
            JFrame jFrame = showImg(x*ICON_WIDTH,y*ICON_HEIGHT,dir+img.getName());
            list.add(jFrame);
        }
        for(JFrame j:list){
            j.dispose();
        }

    }

    private static JFrame showImg(int x,int y,String path){
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(ICON_WIDTH,ICON_HEIGHT);
        jFrame.setUndecorated(true);
        jFrame.setMenuBar(null);
        // 使用JLabel显示图片
        ImageIcon imageIcon = new ImageIcon(path);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder()); // 设置边框为空
        jFrame.add(imageLabel);
        jFrame.setVisible(true);
        jFrame.setLocation(x,y);
        return jFrame;
    }
}
