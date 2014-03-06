/**
 * Created by torilau on 03/03/14.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Created by torilau on 28/02/14.
 */

public class simpleTest {
    private WebDriver driver;
    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
    }

// Search using keyword through Google search


    @Test
    public void testtestclass() throws Exception {
        //Open Home Page
        driver.get("http://www.google.com");
        //Enter text in search box
        driver.findElement(By.name("q")).sendKeys("selenium");
        Thread.sleep(1000);
        //Click Search button
        driver.findElement(By.name("btnG")).click();
        Thread.sleep(10000);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
}

