package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.SearchPageObject;

public class iOSSearchPageObject extends SearchPageObject {
    static {

        SEARCH_INIT_ELEMENT = "id:Search Wikipedia";
        SEARCH_INPUT = "xpath://*[@type='XCUIElementTypeSearchField']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        //SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[@name='Java (programming language)']/../XCUIElementTypeStaticText[@name='Object-oriented programming language']";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name,'{SUBSTRING}')]/..";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";
        SEARCH_CLEAR_BUTTON = "id:clear mini";
    }

    public iOSSearchPageObject(AppiumDriver driver) {
        super(driver);
    }
}
