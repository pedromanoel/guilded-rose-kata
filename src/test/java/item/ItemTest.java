package item;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemTest {
    @Test
    public void itemHasSellInValue() throws Exception {
        Item item = new Item();

        assertThat(item.sellIn(), is(0));
    }

    @Test
    public void itemHasQualityValue() throws Exception {
        Item item = new Item();

        assertThat(item.quality(), is(0));
    }

    @Test
    public void itemHasName() throws Exception {
        String name = "A name";
        Item item = new Item(name);

        assertThat(item.name(), is(name));
    }

    @Test
    public void itemSetsSellInValueInConstructor() throws Exception {
        Item item = new Item("A name", 5);

        assertThat(item.sellIn(), is(5));
    }

    @Test
    public void itemSetsQualityValueInConstructor() throws Exception {
        Item item = new Item("A name", 5, 7);

        assertThat(item.quality(), is(7));
    }
}
