package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class BrokenLinks {

    public static void main(String[] args) {
        String url;
        HttpURLConnection con = null;
        // Define a web driver
        WebDriver driver;

        // Launching the browser
        driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");

        // fetch all the elements with //a
        List<WebElement> links = driver.findElements(By.tagName("a"));
        for (WebElement link : links) {
            url = link.getAttribute("href");
            System.out.println(url);
            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            try {
                con = (HttpURLConnection) (new URL(url)).openConnection();
                con.setRequestMethod("HEAD");
                con.connect();
                int response = con.getResponseCode();
                if (response >= 400) {
                    System.out.println(url + " is a broken url");
                } else {
                    System.out.println(url + " is a valid url");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
