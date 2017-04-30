import item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class App {

    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String AGED_BRIE = "Aged Brie";
    private static final String CONJURED = "Conjured";
    private static final String SULFURAS_HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";

    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        App app = new App();
        List<Item> items = Arrays.asList(
                new Item("+5 Dexterity Vest", 10, 20),
                new Item(AGED_BRIE, 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item(SULFURAS_HAND_OF_RAGNAROS, 0, 80),
                new Item(BACKSTAGE_PASSES, 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        );

        app.updateQuality(items);
    }

    List<Item> updateQuality(List<Item> items) {
        ArrayList<Item> returnItems = new ArrayList<>(items);

        for (Item item : returnItems) {
            if (item.name().equals(SULFURAS_HAND_OF_RAGNAROS)) {
                continue;
            }

            if (increasesQualityWithTime(item)) {
                increaseQuality(item);
            } else {
                decreaseQuality(item);
            }
            
            item.sellIn(item.sellIn() - 1);
        }

        return returnItems;
    }

    private void increaseQuality(Item item) {
        if (item.quality() >= 50) {
            return;
        }

        item.quality(item.quality() + getIncrease(item));
    }

    private void decreaseQuality(Item item) {
        if (item.quality() <= 0) {
            return;
        }

        item.quality(item.quality() - getDecrease(item));
    }

    private int getIncrease(Item item) {
        int increase = 1;

        if (item.name().equals(BACKSTAGE_PASSES)) {
            if (item.sellIn() < 11) {
                increase = 2;
            }

            if (item.sellIn() < 6) {
                increase = 3;
            }
        }

        return increase;
    }

    private int getDecrease(Item item) {
        int decrease = item.sellIn() > 0 ? 1 : 2;

        if (CONJURED.equals(item.name())) {
            decrease *= 2;
        }

        if (item.name().equals(BACKSTAGE_PASSES) && item.sellIn() == 0) {
            decrease = item.quality();
        }

        return decrease;
    }

    private boolean increasesQualityWithTime(Item item) {
        String name = item.name();

        boolean validBackstagePass = BACKSTAGE_PASSES.equals(name) && item.sellIn() > 0;

        return AGED_BRIE.equals(name) || validBackstagePass;
    }
}
