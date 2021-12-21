package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "xpath://*[@name='Learn more about Wikipedia']",
            STEP_NEW_WAYS_TO_EXPLORE = "id:New ways to explore",
            STEP_FOR_ADD_OR_EDIT_PREFERRED_LANGUAGE = "xpath://*[@name='Add or edit preferred languages']",
            STEP_FOR_LEARNED_MORE_ABOUT_DATA_COLLECTED = "xpath://*[@name='Learn more about data collected']",
            NEXT_LINK = "xpath://*[@name='Next']",
            GET_STARTED_BUTTON = "xpath://*[@name='Get started']",
            SKIP = "xpath://XCUIElementTypeButton[@name='Skip']";


    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExplore() {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE, "Cannot find 'New ways to explore' button", 10);
    }

    public void waitForAddOrEditPreferredLanguages() {
        this.waitForElementPresent(STEP_FOR_ADD_OR_EDIT_PREFERRED_LANGUAGE, "Cannot find 'Add or edit preferred languages' button", 10);
    }

    public void waitForLearnedMoreAboutDataCollected() {
        this.waitForElementPresent(STEP_FOR_LEARNED_MORE_ABOUT_DATA_COLLECTED, "Cannot find 'Learn more about data collected' button", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(NEXT_LINK, "Cannot find 'Next' button", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find 'Get started' button", 10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find and skip button", 15);
    }

}
