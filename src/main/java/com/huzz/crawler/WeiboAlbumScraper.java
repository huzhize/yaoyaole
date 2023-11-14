package com.huzz.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class WeiboAlbumScraper {
    public static void main(String[] args) {
        // 设置 ChromeDriver 路径
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // 创建 ChromeDriver 对象
        WebDriver driver = new ChromeDriver();

        try {
            // 打开新浪微博登录页面
            driver.get("https://passport.weibo.cn/signin/login");

            // 输入用户名和密码，并点击登录按钮
            WebElement usernameInput = driver.findElement(By.name("username"));
            WebElement passwordInput = driver.findElement(By.name("password"));
            WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

            usernameInput.sendKeys("your_username");
            passwordInput.sendKeys("your_password");
            loginButton.click();

            // 等待一些时间，确保登录成功
            Thread.sleep(5000);

            // 进入用户主页的相册
            driver.get("https://weibo.com/your_user_name/photos");

            // 等待一些时间，确保页面加载完成
            Thread.sleep(5000);

            // 获取相册中的照片链接
            List<WebElement> imageElements = driver.findElements(By.cssSelector(".photo_wrap img"));
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
        // 实现图片下载逻辑，与前面的示例相似
        // ...
    }
}

