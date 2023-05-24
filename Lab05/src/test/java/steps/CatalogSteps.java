package steps;

import net.thucydides.core.annotations.Step;
import org.junit.jupiter.api.Assertions;
import pages.CatalogPage;

public class CatalogSteps {

    CatalogPage catalogPage;

    @Step
    public void searchForAnItem(String keyword){
        catalogPage.searchItem(keyword);
    }

    @Step
    public void checkSearchedItem(String keyword){ Assertions.assertTrue(catalogPage.showList().contains(keyword)); }

    @Step
    public void checkNumberOfItems(int count){
        Assertions.assertEquals(catalogPage.getNumberOfItems(), count);}

    @Step
    public void checkWishlist(String keyword){
        Assertions.assertEquals(catalogPage.checkWishlist(), keyword);
    }
    @Step
    public void addItemsToWishlist(){
        catalogPage.addToWishlist();
    }

    @Step
    public void countWishlistItems(int count){
        Assertions.assertEquals(catalogPage.countWishlistItems(), count);
    }

    @Step
    public void removeFromWishlist(){
        catalogPage.removeFromWishlist();
    }
}
