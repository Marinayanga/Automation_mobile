package tests;

import lib.CoreTestCase;
import lib.UI.ArticlePageObject;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {
    @Test
    public void testChangeScreenOrientation() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String title_after_rotation = ArticlePageObject.getArticleTitle();
        assertEquals("Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_rotation);
        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        assertEquals("Article title has been changed after screen rotation",
                title_before_rotation,
                title_after_second_rotation);

    }

    @Test
    public void testCheckSearchArticleInBackground() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);;

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        this.BackgroundApp(5);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");


    }
}
