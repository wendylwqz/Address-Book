package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LocationTest {
    private Location testLocation;
    private Location testLocation2;

    @BeforeEach
    void runBefore () {
        testLocation = new Location("13022 101 ST, city, state, country", "testing",
                1.01, "for testing");
        testLocation2 = new Location(null, null, 0, null);
    }

    @Test
    void testConstructor () {
        assertEquals(1.01, testLocation.getRating());
        assertEquals("13022 101 ST, city, state, country", testLocation.getAddress());
        assertEquals("testing", testLocation.getTags());
        assertEquals("for testing", testLocation.getPs());

        assertEquals(0, testLocation2.getRating());
        assertNull(testLocation2.getTags());
        assertNull(testLocation2.getPs());
        assertNull(testLocation2.getAddress());
    }

    @Test
    void testChangeTags() {
        testLocation2.changeTags("ratt");
        assertEquals("ratt", testLocation2.getTags());
    }

    @Test
    void testChangeAddress() {
        testLocation.changeAddress("111 st");
        assertEquals("111 st", testLocation.getAddress());
    }

    @Test
    void testChangePs() {
        testLocation2.changePs("nno");
        assertEquals("nno", testLocation2.getPs());
    }

    @Test
    void testChangeRating() {
        testLocation2.changeRating(2.);
        assertEquals(2,testLocation2.getRating());
    }
}