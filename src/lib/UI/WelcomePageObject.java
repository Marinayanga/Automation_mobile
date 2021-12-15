package lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {

    private static final String
            STEP_LEARN_MORE_LINK = "//*[@name='Learn more about Wikipedia']",
            STEP_NEW_WAYS_TO_EXPLORE = "New ways to explore",
            STEP_FOR_ADD_OR_EDIT_PREFERRED_LANGUAGE= "//*[@name='Add or edit preferred languages']",
            STEP_FOR_LEARNED_MORE_ABOUT_DATA_COLLECTED = "//*[@name='Learn more about data collected']",
            NEXT_LINK="//*[@name='Next']",
            GET_STARTED_BUTTON = "//*[@name='Get started']";



    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(By.xpath(STEP_LEARN_MORE_LINK), "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void waitForNewWaysToExplore() {
        this.waitForElementPresent(By.id(STEP_NEW_WAYS_TO_EXPLORE), "Cannot find 'New ways to explore' button", 10);
    }

    public void waitForAddOrEditPreferredLanguages() {
        this.waitForElementPresent(By.xpath( STEP_FOR_ADD_OR_EDIT_PREFERRED_LANGUAGE), "Cannot find 'Add or edit preferred languages' button", 10);
    }

    public void waitForLearnedMoreAboutDataCollected() {
        this.waitForElementPresent(By.xpath( STEP_FOR_LEARNED_MORE_ABOUT_DATA_COLLECTED), "Cannot find 'Learn more about data collected' button", 10);
    }

    public void clickNextButton() {
        this.waitForElementAndClick(By.xpath(NEXT_LINK), "Cannot find 'Next' button", 10);
    }

    public void clickGetStartedButton() {
        this.waitForElementAndClick(By.xpath(GET_STARTED_BUTTON), "Cannot find 'Get started' button", 10);
    }

}
