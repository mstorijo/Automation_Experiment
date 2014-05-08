package com.gu.test.Common;

import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import com.gu.test.HttpMock;
import com.gu.test.pages.Article;
import com.gu.test.pages.FrontPage;
import com.gu.test.TestRunner;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserTrackingTest {
    WebDriver driver;
    private TestRunner testRunner;
    private FrontPage fronts;
    private Article firstArticle;
    private HttpMock httpMock;

    @Before
    public void setUp() throws Exception {
//
//        String PROXY = "localhost:8080";
//        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
//        proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY).setSocksProxy(PROXY);
//        DesiredCapabilities cap = new DesiredCapabilities();
//        cap.setCapability(CapabilityType.PROXY, proxy);
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        testRunner = new TestRunner(driver);
        fronts = testRunner.goToFronts(driver);
     }


    @Test
    public void theCorrectTrackingInformationShouldBeSentForArticle() throws Exception {

        httpMock = new HttpMock();
        httpMock.start();

        firstArticle = fronts.goToArticleInTopStories(driver);
        List<LoggedRequest> requests = httpMock.findAllRequestsTo("ophan.theguardian.com");
        String dataComponent = "top-stories";

        boolean contains = false;
        for (LoggedRequest request : requests) {
            if (request.getUrl().contains("referringComponent=" + dataComponent)) {
                contains = true;
            }
        }
        Assert.assertTrue("Failure: Tracking Not Found", contains);


        httpMock.stop();
    }


    @After
    public void tearDown() throws Exception {
        testRunner.endTest(driver);

    }
}