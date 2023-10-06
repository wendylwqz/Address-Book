package persistence;

import model.Location;
import model.LocationList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public LocationList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWorkRoom(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private LocationList parseWorkRoom(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        LocationList wr = new LocationList(name);
        addThingies(wr, jsonObject);
        return wr;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addThingies(LocationList wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("things");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(wr, nextThingy);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addThingy(LocationList wr, JSONObject jsonObject) {
        String address = jsonObject.getString("address");
        String ps = String.valueOf(jsonObject.getString("ps"));
        String tags = String.valueOf(jsonObject.getString("tags"));
        Double rating = Double.valueOf(jsonObject.getDouble("rating"));
        Location thingy = new Location(address, tags, rating, ps);
        wr.addLocationList(thingy);
    }
}
