package challenge2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class challenge2 {

    public WebDriver driver;

    @BeforeSuite
    public void startSuite() throws Exception {

    }

    @AfterSuite
    public void stopSuite() throws Exception {
        System.out.println("Finito!!!");
    }

    @BeforeClass
    public void startClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "./bin/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterClass
    public void stopClass() {
        driver.quit();
    }

    @BeforeMethod()
    public void beforeMethod() throws Exception {

    }

    @AfterMethod()
    public void afterMethod() {

    }

    @Test
    public void goToCopart() {
        driver.get("https://www.copart.com");
    }

    @Test
    public void searchForExotics() {
        WebElement search = driver.findElement(By.id("input-search"));
        search.sendKeys("exotics");
        search.sendKeys(Keys.ENTER);
    }

    @Test
    public void verifyPorscheIsInListOfCars() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.cssSelector("#serverSideDataTable > tbody > tr")));
        List<WebElement> makes = driver.findElements(By.cssSelector("span[data-uname='lotsearchLotmake']"));
        boolean foundPorsche = false;
        for (WebElement make: makes) {
            String text = make.getText();
            if (make.getText().equalsIgnoreCase("Porsche")) foundPorsche = true;
            if (foundPorsche) break;
        }
        Assert.assertTrue(foundPorsche);
    }
}
