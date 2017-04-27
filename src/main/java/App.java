import item.Item;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class App {
    
    private List<Item> items;

    public static void main(String[] args) {
        System.out.println("OMGHAI!");

        App app = new App();
        app.items = Arrays.asList(
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 2, 0),
                new Item("Elixir of the Mongoose", 5, 7),
                new Item("Sulfuras, Hand of Ragnaros", 0, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Conjured Mana Cake", 3, 6)
        );

        app.UpdateQuality();
    }

    private void UpdateQuality() {
        for (int i = 0; i < items.size(); i++) {
            if (!Objects.equals(items.get(i).name(), "Aged Brie") && !Objects.equals(items.get(i).name(), "Backstage passes to a TAFKAL80ETC concert")) {
                if (items.get(i).quality() > 0) {
                    if (!Objects.equals(items.get(i).name(), "Sulfuras, Hand of Ragnaros")) {
                        items.get(i).quality(items.get(i).quality() - 1);
                    }
                }
            } else {
                if (items.get(i).quality() < 50) {
                    items.get(i).quality(items.get(i).quality() + 1);

                    if (Objects.equals(items.get(i).name(), "Backstage passes to a TAFKAL80ETC concert")) {
                        if (items.get(i).sellIn() < 11) {
                            if (items.get(i).quality() < 50) {
                                items.get(i).quality(items.get(i).quality() + 1);
                            }
                        }

                        if (items.get(i).sellIn() < 6) {
                            if (items.get(i).quality() < 50) {
                                items.get(i).quality(items.get(i).quality() + 1);
                            }
                        }
                    }
                }
            }

            if (!Objects.equals(items.get(i).name(), "Sulfuras, Hand of Ragnaros")) {
                items.get(i).sellIn(items.get(i).sellIn() - 1);
            }

            if (items.get(i).sellIn() < 0) {
                if (!Objects.equals(items.get(i).name(), "Aged Brie")) {
                    if (!Objects.equals(items.get(i).name(), "Backstage passes to a TAFKAL80ETC concert")) {
                        if (items.get(i).quality() > 0) {
                            if (!Objects.equals(items.get(i).name(), "Sulfuras, Hand of Ragnaros")) {
                                items.get(i).quality(items.get(i).quality() - 1);
                            }
                        }
                    } else {
                        items.get(i).quality(items.get(i).quality() - items.get(i).quality());
                    }
                } else {
                    if (items.get(i).quality() < 50) {
                        items.get(i).quality(items.get(i).quality() + 1);
                    }
                }
            }
        }
    }
}
