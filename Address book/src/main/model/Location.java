package model;

import org.json.JSONObject;
import persistence.Writable;

public class Location implements Writable {
    private String address;     // address of the place
    private String tags;        // class of the location (foods, hated places, parks, etc.)
    private String ps;          // anything needs to be noted (no pets allowed, insufferable parking spots, etc.)
    private double rating;         // out of 5, for whatever purpose (good, price, how desired, crowded, etc.)

    // REQUIRES: address != null,
    //           class != null
    //           0 <= rating <= 5
    // EFFECTS: constructs a location with given address, rating, type, and ps
    public Location(String address, String tags, double rating, String ps) {
        this.address = address;
        this.tags = tags;
        this.rating = rating;
        this.ps = ps;
    }

    // MUTATES: this
    // EFFECTS: changes the rating of location
    public void changeRating(Double rating) {
        this.rating = rating;
    }

    // MUTATES: this
    // EFFECTS: changes the type of the location
    public void changeTags(String tags) {
        this.tags = tags;
    }

    // MUTATES: this
    // EFFECTS: changes the address of the location
    public void changeAddress(String address) {
        this.address = address;
    }

    // MUTATES: this
    // EFFECTS: changes the ps of the location
    public void changePs(String ps) {
        this.ps = ps;
    }

    // EFFECTS: get p.s.
    public String getPs() {
        return this.ps;
    }

    // EFFECTS: get rating
    public double getRating() {
        return this.rating;
    }

    // EFFECTS: get address
    public String getAddress() {
        return this.address;
    }

    // EFFECTS: get tags
    public String getTags() {
        return this.tags;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("address", address);
        json.put("tags", tags);
        json.put("ps", ps);
        json.put("rating", rating);
        return json;
    }
}
