package persistence;

import model.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkThingy(String name, String address, String tags, String ps, double rating, Location location) {
        assertEquals(address, location.getAddress());
        assertEquals(tags, location.getTags());
        assertEquals(ps, location.getPs());
        assertEquals(rating, location.getRating());
    }
}
