package persistence;

import model.Location;
import model.LocationList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            LocationList wr = new LocationList("My work room");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            LocationList wr = new LocationList("rat's places");
            JsonWriter writer = new JsonWriter("./data/test0.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/test0.json");
            wr = reader.read();
            assertEquals("rat's places", wr.getName());
            assertEquals(0, wr.locationListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            LocationList wr = new LocationList("rat's places");
            wr.addLocationList(new Location("1", "1", 1, "1"));
            wr.addLocationList(new Location("2", "2", 2, "2"));
            JsonWriter writer = new JsonWriter("./data/test2.json");
            writer.open();
            writer.write(wr);
            writer.close();

            JsonReader reader = new JsonReader("./data/test2.json");
            wr = reader.read();
            assertEquals("rat's places", wr.getName());
            List<Location> thingies = wr.returnLocationList();
            assertEquals(2, thingies.size());
            wr.addLocationList(new Location("1", "1", 1, "1"));
            wr.addLocationList(new Location("2", "2", 2, "2"));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
