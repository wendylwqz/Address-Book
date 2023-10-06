package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationListTest {
    private LocationList testLocationList;
    private Location location1;
    private Location location2;
    private Location location3;
    private Location location4;

    @BeforeEach
    void runBefore() {
        testLocationList = new LocationList("rat");
        location1 = new Location("13022 101 ST, city, state, country", "testing",
                1.01, "for testing");
        location2 = new Location(null, null, 0, null);
        location3 = new Location("address", "t", 5, "tt");
        location4 = new Location("rat", "r", 3, "at");
    }

    @Test
    void testConstructor () {
        assertEquals(0, testLocationList.locationListSize());
    }

    @Test
    void testAddLocation() {
        testLocationList.addLocationList(location1);
        assertEquals(1, testLocationList.locationListSize());
        Location index0 = testLocationList.get(0);
        assertEquals("13022 101 ST, city, state, country", index0.getAddress());
        assertEquals(1.01, index0.getRating());
        assertEquals("testing", index0.getTags());
        assertEquals("for testing", index0.getPs());

        testLocationList.addLocationList(location2);
        assertEquals(2,testLocationList.locationListSize());
        Location index1 = testLocationList.get(1);
        assertNull(index1.getPs());
        assertNull(index1.getAddress());
        assertNull(index1.getTags());
        assertEquals(0, index1.getRating());
    }

    @Test
    void testGetName() {
        assertEquals("rat", testLocationList.getName());
    }

    @Test
    void testRemoveLocation() {
        testLocationList.addLocationList(location1);
        testLocationList.addLocationList(location3);
        testLocationList.removeLocationList("13022 101 ST, city, state, country");
        assertEquals(1, testLocationList.locationListSize());
        assertEquals(location3, testLocationList.get(0));

        testLocationList.removeLocationList("address");
        assertEquals(0, testLocationList.locationListSize());

        testLocationList.addLocationList(location1);
        testLocationList.addLocationList(location3);
        testLocationList.addLocationList(location4);
        testLocationList.removeLocationList("rat");
        assertEquals(location1, testLocationList.get(0));
        assertEquals(location3, testLocationList.get(1));
    }

    @Test
    void testFindLocation() {
        assertNull(testLocationList.findLocation("222"));

        testLocationList.addLocationList(location1);
        assertNull(testLocationList.findLocation("2222"));
        assertEquals(location1, testLocationList.findLocation("13022 101 ST, city, state, country"));

        testLocationList.addLocationList(location3);
        testLocationList.addLocationList(location4);
        assertEquals(location4, testLocationList.findLocation("rat"));
        assertNull(testLocationList.findLocation("eee"));
    }

    @Test
    void testReturnList() {
        assertEquals(0, testLocationList.returnLocationList().size());

        testLocationList.addLocationList(location1);
        testLocationList.addLocationList(location2);
        assertEquals(2, testLocationList.returnLocationList().size());
        Location index0 = testLocationList.get(0);
        assertEquals("13022 101 ST, city, state, country", index0.getAddress());
        assertEquals(1.01, index0.getRating());
        assertEquals("testing", index0.getTags());
        assertEquals("for testing", index0.getPs());
        Location index1 = testLocationList.get(1);
        assertNull(index1.getPs());
        assertNull(index1.getAddress());
        assertNull(index1.getTags());
        assertEquals(0, index1.getRating());
    }
}
