package com.huzz.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class SinaWeiboImageDownloader {
    public static void main(String[] args) {
        // 设置 ChromeDriver 路径
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // 创建 ChromeDriver 对象
        WebDriver driver = new ChromeDriver();

        try {
            // 打开新浪微博用户主页
            String weiboUserPageUrl = "https://weibo.com/your_user_name";
            driver.get(weiboUserPageUrl);

            // 等待一些时间，确保页面加载完成
            Thread.sleep(3000);

            // 获取用户主页中的图片链接
            List<WebElement> imageElements = driver.findElements(By.cssSelector("img"));
            for (WebElement imageElement : imageElements) {
                String imageUrl = imageElement.getAttribute("src");
                downloadImage(imageUrl, "D:/img/");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭浏览器
            driver.quit();
        }
    }

    private static void downloadImage(String imageUrl, String destinationPath) {
        try {
            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            try (BufferedInputStream in = new BufferedInputStream(connection.getInputStream());
                 FileOutputStream out = new FileOutputStream(destinationPath + getImageFileName(imageUrl))) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer, 0, 1024)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
                System.out.println("Downloaded: " + imageUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getImageFileName(String imageUrl) {
        // 从图片链接中提取文件名
        return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
    }
}

