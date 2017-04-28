import item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        App app = new App();
        List<Item> items = Arrays.asList(
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        );

        app.updateQuality(items);
    }

    List<Item> updateQuality(List<Item> items) {
        ArrayList<Item> returnItems = new ArrayList<>(items);

        for (Item item : returnItems) {
            if (item.name().equals("Sulfuras, Hand of Ragnaros")) {
                continue;
            }

            if (decreasesQualityWithTime(item)) {
                decreaseQuality(item);
            } else {
                increaseQuality(item);
            }

            item.sellIn(item.sellIn() - 1);

            if (item.sellIn() >= 0) {
                continue;
            }

            if (item.name().equals("Aged Brie")) {
                continue;
            }

            if (Objects.equals(item.name(), "Backstage passes to a TAFKAL80ETC concert")) {
                item.quality(item.quality() - item.quality());

                continue;
            }

            decreaseQuality(item);
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

        item.quality(item.quality() - getDecrease());
    }

    private int getIncrease(Item item) {
        int increase = 1;

        if (item.name().equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (item.sellIn() < 11) {
                increase = 2;
            }

            if (item.sellIn() < 6) {
                increase = 3;
            }
        }

        return increase;
    }

    private int getDecrease() {
        return 1;
    }

    private boolean decreasesQualityWithTime(Item item) {
        boolean decreases;

        decreases = Stream.of("Aged Brie", "Backstage passes to a TAFKAL80ETC concert")
                .noneMatch(i -> item.name().equals(i));

        return decreases;
    }
}
