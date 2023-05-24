package pages;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CatalogPage extends PageObject {

    @FindBy(id = "search")
    private WebElementFacade searchBar;

    @FindBy(xpath = "//a[normalize-space()='HOODIE price range of $55-$70']")
    private WebElementFacade searchResult;

    @FindBy(css = ".item.product.product-item")
    private List<WebElementFacade> catalogItems;

    @FindBy(css = "div[class='block-content'] div[class='empty']")
    private WebElementFacade checkWishlist;

    @FindBy(css = "div[class='product-addto-links'] a[class='action towishlist']")
    private WebElementFacade addWishlist;

    @FindBy(css = ".products-grid.wishlist .product-item")
    private List<WebElementFacade> countWishlistItems;

    @FindBy(css = "a[title='Remove This Item']")
    private WebElementFacade removeWishlist;

    public void searchItem(String keyword){ searchBar.typeAndEnter(keyword); }

    public String showList(){ return searchResult.getText().toLowerCase(Locale.ROOT);}

    public int getNumberOfItems() {
        return catalogItems.size();
    }

    public String checkWishlist(){
        return checkWishlist.getText();
    }

    public void addToWishlist(){
        catalogItems.get(0).click();
        addWishlist.click();
    }

    public int countWishlistItems(){
        return countWishlistItems.size();
    }

    public void removeFromWishlist(){
        removeWishlist.click();
    }
}
