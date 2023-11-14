package com.huzz.crawler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//https://zhuanlan.zhihu.com/p/504965276?utm_id=0
public class SimpleWebScraper {
    public static void main(String[] args) {
        // 设置 ChromeDriver 路径
        System.setProperty("webdriver.chrome.driver", "D:\\ProgramFile\\chromedriver-win64\\chromedriver.exe");

        // 创建 ChromeDriver 对象
        WebDriver driver = new ChromeDriver();

        try {
            // 打开 Google 搜索页面
            driver.get("https://www.google.com");

            // 在搜索框中输入关键词 "Selenium"
            WebElement searchBox = driver.findElement(By.name("q"));
            searchBox.sendKeys("Selenium");
            searchBox.submit();

            // 等待一些时间，确保搜索结果加载完成
            Thread.sleep(3000);

            // 输出搜索结果的标题
            System.out.println("Search Results:");
            java.util.List<WebElement> searchResults = driver.findElements(By.cssSelector("h3"));
            for (WebElement result : searchResults) {
                System.out.println(result.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭浏览器
            driver.quit();
        }
    }
}
