package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTION_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_USED_LIST_NAME = "xpath://*[@resource-id='org.wikipedia:id/item_container']//*[@text= '{USED_LIST_NAME}']",
            MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
            CHANGE_LANGUAGE= "xpath://*[@text='Change language']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(FOOTER_ELEMENT,
                "can not find the and of article",
                20);
    }

    public void addArticleToMyList(String name_of_folder) {
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5);
        this.waitForElementPresent(CHANGE_LANGUAGE,
                "Cannot find Change language",
                5);

        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article options",
                5);
        this.waitForElementAndClick(ADD_TO_MY_LIST_OVERLAY,
                "Cannot find button Got It",
                5);
        this.waitForElementAndClear(MY_LIST_NAME_INPUT,
                "Cannot find input to set list name",
                5);
        this.waitForElementAndSendKeys(MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into list name input", 5);
        this.waitForElementAndClick(MY_LIST_OK_BUTTON,
                "Cannot find button OK",
                5);

    }

    public void addArticleToUsedList() {
        this.waitForElementAndClick(OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5);
        this.waitForElementPresent(CHANGE_LANGUAGE,
                "Cannot find Change language",
                5);
        this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article options",
                5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                "Cannot close article by X",
                5);
    }
    public String assertElementPresent() {
        WebElement title_element = driver.findElement(By.id("org.wikipedia:id/view_page_title_text"));
        return title_element.getAttribute("text");
    }
}
