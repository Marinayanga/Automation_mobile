package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTION_ADD_TO_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_USED_LIST_NAME,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            CHANGE_LANGUAGE,
            CLOSE_POP_UP_BUTTON;

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
           return title_element.getAttribute("name");
        }
    }
        public void swipeToFooter() {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT,
                    "can not find the and of article",
                    40);
        }else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
        }

        public void addArticleToMyList (String name_of_folder){
            this.waitForElementAndClick(OPTIONS_BUTTON,
                    "Cannot find button to open article options",
                    10);
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

        public void addArticleToUsedList () {
            this.waitForElementAndClick(OPTIONS_BUTTON,
                    "Cannot find button to open article options",
                    10);
            this.waitForElementPresent(CHANGE_LANGUAGE,
                    "Cannot find Change language",
                    5);
            this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find option to add article options",
                    10);
        }

        public void closeArticle () {
            this.waitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                    "Cannot close article by X",
                    5);
        }

        public void closeArticlePopUp(){
        this.waitForElementAndClick(CLOSE_POP_UP_BUTTON, "Cannot find popup", 10);
        }
   /* }
    public String assertElementPresent() {
        WebElement title_element = driver.findElement(TITLE);
        return title_element.getAttribute("text");
    }*/


        public void addArticleToMySaved(){
           this.waitForElementAndClick(OPTION_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 10);
        }
    }
