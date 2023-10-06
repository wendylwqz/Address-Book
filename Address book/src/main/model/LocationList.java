package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class LocationList implements Writable {
    private ArrayList<Location> locationList;
    private String name;

    // EFFECTS: construct empty LocationList
    public LocationList(String name) {
        this.name = name;
        locationList = new ArrayList<>();
    }

    // REQUIRES: no duplicate addresses in LocationList AND location is NOT in LocationList
    // MUTATES: this
    // EFFECTS: add location to LocationList
    public void addLocationList(Location location) {
        locationList.add(location);
    }

    // REQUIRES: address is in LocationList
    // MUTATES: this
    // EFFECTS: remove location from LocationList by finding the address
    public void removeLocationList(String address) {
        ArrayList<Location> tempList = new ArrayList<>();
        for (Location location : locationList) {
            String a = location.getAddress();
            if (!a.equals(address)) {
                tempList.add(location);
            }
        }
        locationList = tempList;
    }

    public String getName() {
        return name;
    }

    // REQUIRES: location is in LocationList
    // EFFECTS: find the location through input address, if found, return location, other return null
    public Location findLocation(String address) {
        int i = 0;
        for (Location location: locationList) {
            String locationAddress = location.getAddress();
            if (address.equals(locationAddress)) {
                return locationList.get(i);
            }
            i++;
        }
        return null;
    }

    // EFFECTS: returns the size of LocationList
    public int  locationListSize() {
        return locationList.size();
    }

    // EFFECTS: returns the list of locations
    public ArrayList<Location> returnLocationList() {
        return locationList;
    }

    // EFFECTS: get the x-th location in LocationList
    public Location get(int x) {
        return locationList.get(x);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("things", thingsToJson());
        json.put("name", name);
        return json;
    }

    private JSONArray thingsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Location location : locationList) {
            jsonArray.put(location.toJson());
        }
        return jsonArray;
    }
}
