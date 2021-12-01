import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("AutomationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/skyeng/Desktop/JavaAppiumAutomation/Automation_mobile/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find the element",
                5);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java", 15);
    }

    @Test
    public void TestCancelSearch() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find the 'Search wikipedia' input",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find the element",
                5);
        waitForElementAndClear(By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find the X button",
                5);
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5);
    }

    @Test
    public void TestCompareArticleTitle() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find the element",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find the Java oop result",
                5);
        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "cannot find article title",
                15);
        String article_title = title_element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void TestCompareSearchTitle() {
        assertElementHasText(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Search Wikipedia",
                "We see unexpected text",
                5

        );

    }

    @Test
    public void TestSearchFewArticles() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "table",
                "Cannot find the element",
                5);
        countOfArticles(
                By.xpath(("//*[org.wikipedia:id/search_results_list]/*[org.wikipedia:id/page_list_item_container]")),
                "You have only 1 or 0 result",
                5);
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find the X button",
                5);
        waitForElementNotPresent(
                By.id("//*[@resource-id='org.wikipedia:id/page_list_item_container']"),
                "list is not empty",
                5);
    }

    @Test
    public void TestSwipeArticle() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find the element",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find the Appium result",
                5);
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "cannot find article title",
                15);
        swipeUpToFindElement(By.xpath("//*[@text='View page in browser']"),
                "can not find element in footer",
                20);
    }

    @Test
    public void saveFirstArticleToMyList() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find the element",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find the Java oop result",
                5);
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "cannot find article title",
                15);
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5);
        waitForElementPresent(By.xpath("//*[@text='Change language']"),
                "Cannot find Change language",
                5);

        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article options",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button Got It",
                5);
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set list name",
                5);

        String name_of_folder = "Learning programming";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into list name input", 5);
        waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot find button OK",
                5);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article by X",
                5);
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My List",
                5);
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My List",
                5);
        waitForElementAndClick(By.xpath("//*[@text='" + name_of_folder + "']"),
                "Cannot find My reading List",
                5);
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "cannot find saved articles",
                5);
        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"),
                "cannot delete saved article",
                5);

    }

    @Test
    public void testAmountOfNonEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        String search_line = "Linkin Park Discography";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find the element",
                5);
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(By.xpath(search_result_locator),
                "Cannot find enuthing by request " + search_line,
                15);
        int amount_of_search_result = getAmountofElements(
                By.xpath(search_result_locator));
        Assert.assertTrue("We found too few results",
                amount_of_search_result > 0);
    }

    @Test
    public void testAmountOfEmptySearch() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        String search_line = "Java";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find the element",
                5);
        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        String empty_result_label = "//*[@text='No results found']";
        waitForElementPresent(By.xpath(empty_result_label),
                "Cannot find empty result label by request " + search_line,
                15);
        AssertElementNotPresent(
                By.xpath(search_result_locator),
                "We found some elements by requests " + search_line);

    }

    @Test
    public void testChangeScreenOrientation() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        String search_line = "Java";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find 'Object-oriented programming language'" + search_line,
                15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find the Java oop result",
                5);

        String title_before_rotation = waitForElementandGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);
        driver.rotate(ScreenOrientation.LANDSCAPE);
        String title_after_rotation = waitForElementandGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);
        Assert.assertEquals("Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);
        driver.rotate(ScreenOrientation.PORTRAIT);
        String title_after_second_rotation = waitForElementandGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15);
        Assert.assertEquals("Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);
    }

    @Test
    public void testCheckSearchArticleInBackground() {
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find the element",
                5);
        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find the Java oop result ",
                5);

        driver.runAppInBackground(5);

        waitForElementPresent(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find the Java oop result after returning from background",
                5);
    }

    @Test
    public void saveTwoArticlesinOneFolder(){
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find the element",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find the Java oop result",
                5);
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "cannot find article title",
                15);
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5);
        waitForElementPresent(By.xpath("//*[@text='Change language']"),
                "Cannot find Change language",
                5);

        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article options",
                5);
        waitForElementAndClick(By.id("org.wikipedia:id/onboarding_button"),
                "Cannot find button Got It",
                5);
        waitForElementAndClear(By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set list name",
                5);

        String name_of_folder = "Test folder";
        waitForElementAndSendKeys(By.id("org.wikipedia:id/text_input"),
                name_of_folder,
                "Cannot put text into list name input", 5);
        waitForElementAndClick(By.xpath("//*[@text='OK']"),
                "Cannot find button OK",
                5);
        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article by X",
                5);


        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);

        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                "Linkin Park",
                "Cannot find the element",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='American rock band']"),
                "Cannot find the Linkin park result",
                5);
        String second_article_title = waitForElementandGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15

        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "cannot find article title",
                15);
        waitForElementAndClick(By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Cannot find button to open article options",
                5);
        waitForElementPresent(By.xpath("//*[@text='Change language']"),
                "Cannot find Change language",
                5);
        waitForElementAndClick(By.xpath("//*[@text='Add to reading list']"),
                "Cannot find option to add article options",
                5);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/item_container']//*[@text= '" + name_of_folder + "']"),
                "Cannot find button to open article options",
                5);

        waitForElementAndClick(By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot close article by X",
                5);
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My List",
                5);
        waitForElementAndClick(By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find navigation button to My List",
                5);
        waitForElementAndClick(By.xpath("//*[@text= '" + name_of_folder + "']"),
                "Cannot find My reading List",
                5);
        swipeElementToLeft(By.xpath("//*[@text='Java (programming language)']"),
                "cannot find saved articles",
                5);
        waitForElementNotPresent(By.xpath("//*[@text='Java (programming language)']"),
                "cannot delete saved article",
                5);
        waitForElementAndClick(
                By.xpath("//*[@text='Linkin Park']"),
                "Cannot find article Linlin Park",
                5
        );
        Assert.assertEquals("Wrong article was deleted","Linkin Park", second_article_title);

    }

    @Test
    public void testForTitle(){
        waitForElementAndClick(By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find the Wiki search page",
                5);
        String search_line = "Java";
        waitForElementAndSendKeys(By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find 'Object-oriented programming language'" + search_line,
                15);
        waitForElementAndClick(By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find the Java oop result",
                5);
        assertElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "The article has no title",15);

    }


    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message) {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, 5);
        element.sendKeys(value);
        return element;

    }


    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;

    }

    private boolean assertElementHasText(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        String search_title = element.getAttribute("text");
        Assert.assertEquals(
                "We see unexpected text",
                value,
                search_title);
        return true;

    }

    private boolean countOfArticles(By by, String error_message, long timeoutInSeconds) {
        //WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        List elements = driver.findElements(by);
        boolean check = false;
        if (elements.size() > 1) {
            check = true;
        }
        return check;
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();

    }

    protected void swipeUpQuick() {
        swipeUp(2000);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes) {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find the element by swipingUp.\n" + error_message, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message, int max_swipes) {
        WebElement element = waitForElementPresent(by, error_message, 10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;
        TouchAction action = new TouchAction(driver);
        action.press(right_x, middle_y).waitAction(300).moveTo(left_x, middle_y).release().perform();


    }

    private int getAmountofElements(By by) {
        List elements = driver.findElements(by);
        return elements.size();

    }

    private void AssertElementNotPresent(By by, String error_message) {
        int amount_of_elements = getAmountofElements(by);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    private String waitForElementandGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private void assertElementPresent(By by, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = driver.findElement(by);
        Assert.assertTrue (element.getAttribute(attribute) !=null);
    };


}
