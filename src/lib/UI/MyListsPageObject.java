package lib.UI;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

abstract public class MyListsPageObject extends MainPageObject {
    protected static String
        FOLDER_BY_NAME_TPL,
        SAVED_ARTICLE_SCREEN,
        ARTICLE_BY_TITLE_TPL;
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
        this.waitForElementAndClick(folder_name_xpath,
                "Cannot find folder by name" + name_of_folder,
                5);
    }

    public void waitForArticleApearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathTitle(article_title);
        this.waitForElementPresent(article_xpath,
                "Saved article is still not present with title "+ article_title,
                10);
    }
    public void waitForArticleDissapearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathTitle(article_title);
        this.waitForElementNotPresent(article_xpath,
                "Saved article is still present with title "+ article_title,
                5);
    }
    public void swipeByArticleToDelete(String article_title){
        this.waitForArticleApearByTitle(article_title);
        String article_xpath = getSavedArticleXpathTitle(article_title); //пересмотреть http://webinars-b.stqa.ru/mobile_automation_java/v1_430257/lesson4/06-testSaveFirstArticle.mp4 с 12 минуты
        this.swipeElementToLeft(article_xpath, "cannot find saved articles");
        if(Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        this.waitForArticleDissapearByTitle(article_title);
    }

    public void waitForSavedArticlesScreen(){
        this.waitForElementPresent(SAVED_ARTICLE_SCREEN, "Can not open saved article screen",10);
    }


}
