import item.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class AppTest {

    public static final String ITEM_NAME = "An item";
    private App app;
    private List<Item> items;

    @Before
    public void setUp() throws Exception {
        app = new App();
        items = new ArrayList<>();
    }

    @Test
    public void qualityAndSellInDecreaseByOne() throws Exception {
        addAnItem(10, 20);

        List<Item> returnItems = app.updateQuality(items);

        assertThat(returnItems, hasSize(1));
        Item updatedItem = returnItems.get(0);

        assertThat(updatedItem.name(), equalTo(ITEM_NAME));
        assertThat(updatedItem.sellIn(), equalTo(9));
        assertThat(updatedItem.quality(), equalTo(19));
    }

    @Test
    public void qualityDecreasesByTwoWhenSellInIsZero() throws Exception {
        addAnItem(0, 10);

        List<Item> updatedItems = app.updateQuality(items);

        assertThat(updatedItems.get(0).quality(), equalTo(8));
    }

    @Test
    public void qualityDoesNotDecreaseBelowZero() throws Exception {
        addAnItem(0, 0);

        List<Item> updatedItems = app.updateQuality(items);

        assertThat(updatedItems.get(0).quality(), equalTo(0));
    }

    @Test
    public void agedBrieIncreasesInQuality() throws Exception {
        addAnItem("Aged Brie", 20, 10);
        addAnItem("Aged Brie", 0, 15);

        List<Item> updateQuality = app.updateQuality(this.items);

        assertThat(updateQuality.get(0).quality(), equalTo(11));
        assertThat(updateQuality.get(1).quality(), equalTo(16));
    }

    @Test
    public void qualityDoesNotIncreaseOverFifty() throws Exception {
        addAnItem("Aged Brie", 10, 50);

        List<Item> updateQuality = app.updateQuality(this.items);
        assertThat(updateQuality.get(0).quality(), equalTo(50));
    }

    @Test
    public void sulfurasDoesNotLoseQualityNorSellIn() throws Exception {
        addAnItem("Sulfuras, Hand of Ragnaros", 20, 10);

        List<Item> updateQuality = app.updateQuality(this.items);

        assertThat(updateQuality.get(0).quality(), equalTo(10));
        assertThat(updateQuality.get(0).sellIn(), equalTo(20));
    }

    @Test
    public void backstageQualityIncreasesByOneBeforeTenDays() throws Exception {
        addAnItem("Backstage passes to a TAFKAL80ETC concert", 11, 10);

        List<Item> updateQuality = app.updateQuality(this.items);
        assertThat(updateQuality.get(0).quality(), equalTo(11));
    }

    @Test
    public void backstageQualityIncreasesByTwoBeforeTenDaysOrLess() throws Exception {
        addAnItem("Backstage passes to a TAFKAL80ETC concert", 10, 10);

        List<Item> updateQuality = app.updateQuality(this.items);
        assertThat(updateQuality.get(0).quality(), equalTo(12));
    }

    @Test
    public void backstageQualityIncreasesByThreeBeforeFiveDaysOrLess() throws Exception {
        addAnItem("Backstage passes to a TAFKAL80ETC concert", 5, 10);

        List<Item> updateQuality = app.updateQuality(this.items);
        assertThat(updateQuality.get(0).quality(), equalTo(13));
    }

    @Test
    public void backstageQualityIsZeroAfterTheShow() throws Exception {
        addAnItem("Backstage passes to a TAFKAL80ETC concert", 0, 10);

        List<Item> items = app.updateQuality(this.items);
        assertThat(items.get(0).quality(), equalTo(0));
    }

    @Test
    public void conjuredDecreasesByTwoWhenSellInGreaterThanZero() throws Exception {
        addAnItem("Conjured", 1, 10);

        assertThat(app.updateQuality(this.items).get(0).quality(), equalTo(8));
    }

    @Test
    public void conjuredDecreasesByFourWhenSellInLessThanZero() throws Exception {
        addAnItem("Conjured", 0, 10);

        assertThat(app.updateQuality(this.items).get(0).quality(), equalTo(6));
    }

    private void addAnItem(String name, int sellIn, int quality) {
        items.add(new Item(name, sellIn, quality));

    }

    private void addAnItem(int sellIn, int quality) {
        addAnItem(ITEM_NAME, sellIn, quality);
    }
}