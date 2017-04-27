package item;

public class Item {
    private int quality;
    private int sellIn;
    private String name;

    public Item() {
        this(null, 0, 0);
    }

    public Item(String name) {
        this.name = name;
    }

    public Item(String name, int sellIn) {
        this(name, sellIn, 0);
    }

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public int sellIn() {
        return sellIn;
    }

    public int quality() {
        return quality;
    }

    public String name() {
        return this.name;
    }

    public void name(String name) {
        this.name = name;
    }

    public void sellIn(int sellIn) {
        this.sellIn = sellIn;
    }

    public void quality(int quality) {
        this.quality = quality;
    }
}
