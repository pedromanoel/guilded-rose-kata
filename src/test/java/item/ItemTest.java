package item;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ItemTest {

    private Item item;

    @Before
    public void setUp() throws Exception {
        this.item = new Item("A name", 5, 7);
    }

    @Test
    public void itemSetsValuesFromConstructor() throws Exception {
        assertThat(item.name(), is("A name"));
        assertThat(item.sellIn(), is(5));
        assertThat(item.quality(), is(7));
    }

    @Test
    public void canSetValuesFromSetters() throws Exception {
        item.name("Another name");
        item.sellIn(20);
        item.quality(30);

        assertThat(item.name(), is("Another name"));
        assertThat(item.sellIn(), is(20));
        assertThat(item.quality(), is(30));
    }
}
