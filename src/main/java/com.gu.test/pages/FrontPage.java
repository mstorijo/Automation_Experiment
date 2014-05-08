package com.gu.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FrontPage {
    private WebDriver driver;
    private String frontPageURL;


    public FrontPage(WebDriver driver, String frontPageURL){
        this.driver = driver;
        this.frontPageURL = frontPageURL;
        driver.get(frontPageURL);
    }


    public FrontPage goToEdition(String edition)
    {
      driver.findElement(By.cssSelector("[data-link-name=\"switch to " + edition + " edition\"]")).click();
      return this;
    }

    public Article goToFirstArticle(WebDriver driver){
        driver.findElement(By.xpath("/descendant::a[@data-link-name=\"article\"][1]")).click();
        return new Article(driver);

    }

    public Article goToArticleInTopStories(WebDriver driver){
        driver.findElement(By.xpath("/descendant::section[@data-component=\"top-stories\"]//a[@data-link-name=\"article\"][1]")).click();
        return new Article(driver);
    }
}
