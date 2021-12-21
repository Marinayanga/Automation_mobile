package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {

        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://*[@type='XCUIElementTypeSearchField'][2]";
        SEARCH_CANCEL_BUTTON = "id:Close";
        //SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}'']";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    }

    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
