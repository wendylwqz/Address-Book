package persistence;

import model.Location;
import model.LocationList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            LocationList wr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/test0.json");
        try {
            LocationList wr = reader.read();
            assertEquals("rat's places", wr.getName());
            assertEquals(0, wr.locationListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/test2.json");
        try {
            LocationList wr = reader.read();
            assertEquals("rat's places", wr.getName());
            List<Location> thingies = wr.returnLocationList();
            assertEquals(2, thingies.size());
            checkThingy("rat's places", "1", "1", "1", 1, thingies.get(0));
            checkThingy("rat's places", "2", "2", "2", 2, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
