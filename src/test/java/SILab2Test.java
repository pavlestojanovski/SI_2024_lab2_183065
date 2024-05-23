import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    private List<Item> createItems(Item... items) {
        return new ArrayList<>(Arrays.asList(items));
    }

    @Test
    void testCheckCart_NullItemsList_ThrowsException() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 1));
        assertTrue(exception.getMessage().contains("allItems list can't be null"));


        assertTrue(SILab2.checkCart(new ArrayList<>(), 0));
        assertFalse(SILab2.checkCart(new ArrayList<>(), -1));
    }

    @Test
    void testCheckCart_BarcodeAndDiscount() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(createItems(new Item(" ", null, 20, 0.5f)), 1));
        assertTrue(exception.getMessage().contains("No barcode!"));

        exception = assertThrows(RuntimeException.class, () -> SILab2.checkCart(createItems(new Item("Pavle", "0a34b", 20, 0.5f)), 1));
        assertTrue(exception.getMessage().contains("Invalid character in item barcode!"));


        assertFalse(SILab2.checkCart(createItems(new Item("", "12345", 400, 0.5f)), 2));
        assertFalse(SILab2.checkCart(createItems(new Item("", "12345", 200, -2f)), 2));


        assertTrue(SILab2.checkCart(createItems(new Item("", "01234", 300, 0.5f)), 150));
        assertFalse(SILab2.checkCart(createItems(new Item("", "01234", 100, 0.5f)), 2));
    }

}
