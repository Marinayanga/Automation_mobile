package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListsPageObject extends MainPageObject {
    public static final String
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TPL = "//*[@text='{TITLE}']";
    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    private static String getSavedArticleXpathTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
    public void openFolderByName(String name_of_folder){
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(By.xpath(folder_name_xpath),
                "Cannot find folder by name" + name_of_folder,
                5);
    }

    public void waitForArticleApearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathTitle(article_title);
        this.waitForElementPresent(By.xpath(article_xpath),
                "Saved article is still not present with title "+ article_title,
                5);
    }
    public void waitForArticleDissapearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathTitle(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath),
                "Saved article is still present with title "+ article_title,
                5);
    }
    public void swipeByArticleToDelete(String article_title){
        this.waitForArticleApearByTitle(article_title);
        String article_xpath = getSavedArticleXpathTitle(article_title);
        this.swipeElementToLeft(By.xpath(article_xpath),
                "cannot find saved articles",
                5);
        this.waitForArticleDissapearByTitle(article_title);
    }


}
