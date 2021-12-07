package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {
    public static final String
        SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text,'Search…')]",
        SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
        SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']";
    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }
   /* Templates methods*/
    public static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* Templates methods*/

    public void initSearchInput(){
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find the Wiki search page",5);
    }
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }
    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }
    public void clickCancelSearch(){
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button and click", 5);
    }

    public void typeSearchLine(String value){
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT),value,"Cannot find the element and type into search input",5);
    }
    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring "+ substring ,5);
    }
    public void clickByArticleWithSubstring(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath),"Cannot find and click search result with substring "+ substring ,10);
    }
    public int getAmountOfFoundArticles(){

        this.waitForElementPresent(By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find enything by request ",
                15);
        return this.getAmountofElements(
                By.xpath(SEARCH_RESULT_ELEMENT));

    }
    public void waitForEmptyResultLabel(){
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);

    }
    public void assertThereIsNotResultOfSearch(){
        this.AssertElementNotPresent(
        By.xpath(SEARCH_RESULT_ELEMENT),
                "We found some elements by requests ");
    }

    public void assertSearchInputContent(String search_input_text){
        this.assertElementHasText(By.xpath(SEARCH_INIT_ELEMENT), search_input_text,
                "Texts in input are not similar",5);
    }

    public void takeFreshSearchScreen(){
        this.waitForElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT),"list is not empty",
                5);
    }



}
