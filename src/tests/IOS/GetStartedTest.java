package tests.IOS;

import lib.IOSTestCase;
import lib.UI.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends IOSTestCase {
    @Test
    public void testPassThroughWelcome(){
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWaysToExplore();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLanguages();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnedMoreAboutDataCollected();
        WelcomePage.clickGetStartedButton();





    }
}
