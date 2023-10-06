package ui;

import model.LocationList;
import model.Location;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Places {
    private Scanner input;
    private LocationList myList;
    private Location locationM;
    private static final String JSON_STORE = "./data/LocationList.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the Places app
    public Places() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPlaces();
    }

    // MODIFIES: this
    // EFFECTS: initializes my list of Locations
    private void init() {
        myList = new LocationList("rat's places");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MUTATES: this
    // EFFECTS: processes user input
    public void runPlaces() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nBYE to you too >:(");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    protected void processCommand(String command) {
        if (command.equals(";)")) {
            doAdd();
        } else if (command.equals(";(")) {
            doRemove();
        } else if (command.equals(":]")) {
            doModify();
        } else if (command.equals("lemme see")) {
            doView();
        } else if (command.equals("save")) {
            saveLocationList();
        } else if (command.equals("load")) {
            loadLocationList();
        } else {
            System.out.println("what..?");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nwhat do you want?");
        System.out.println("\t;) -> add new location");
        System.out.println("\t;( -> remove existing location");
        System.out.println("\t:] -> modify location specifications");
        System.out.println("\tlemme see -> view all my locations");
        System.out.println("\tsave -> save progress");
        System.out.println("\tload -> load saved Location List");
        System.out.println("\tquit -> quit");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    protected void doAdd() {
        System.out.print("whats the address?");
        String addyA = input.next();
        System.out.println("how would you classify this place?");
        String tagA = input.next();
        System.out.println("how would you rate this place out of 5?");
        double ratingA = input.nextDouble();
        System.out.println("any notes you want to add?");
        String psA = input.next();
        Location locationA = new Location(addyA, tagA, ratingA, psA);
        myList.addLocationList(locationA);
        System.out.println("Aight gochu.\n");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    protected void doRemove() {
        System.out.print("Are you sure you want to remove? \nIf you HAVE to, I suppose I can't stop you. \nIf "
                + "even my pathetic begging could not change your COLD mind, \ngo ahead. \nPut in the address of the "
                + "location you wish to remove.");
        String addyR = input.next();
        myList.removeLocationList(addyR);
        System.out.println("So sad. Just letting you know you can always add the location back in the main menu.\n");
    }

    private void doView() {
        ArrayList<Location> places;
        places = myList.returnLocationList();
        int i = 1;
        for (Location location : places) {
            System.out.println(i + ") " + location.getAddress());
            double rating = location.getRating();
            System.out.println("Rating: " + rating + " / 5.0");
            System.out.println("Tags: " + location.getTags());
            System.out.println("P.S. " + location.getPs());
            i++;
        }
        int numPlaces = places.size();
        if (numPlaces <= 1) {
            System.out.println("okay so there is " + numPlaces
                        + " place in your list, do better.");
        } else {
            System.out.println("okay so there are " + numPlaces
                        + " places in your list");
        }
    }

    private void doModify() {
        if (myList.locationListSize() == 0) {
            System.out.println("you got nothing to edit bae. Empty-Mpty-Mpt-Mt.");
        } else {
            System.out.println("which address do you want to change?");
            String addressM = input.next();
            locationM = myList.findLocation(addressM);
            if (locationM == null) {
                System.out.println("this address is not in your list. YET");
            } else {
                System.out.println("location found! what do you want to change?");
                System.out.println("\ta -> address");
                System.out.println("\tt -> tag");
                System.out.println("\tr -> rating");
                System.out.println("\tp  -> P.S.");

                String edit = input.next();
                chooseEdit(edit);
            }
        }
    }

    private void chooseEdit(String command) {
        switch (command) {
            case "a":
                doEditAddress();
                break;
            case "t":
                doTag();
                break;
            case "p":
                doPs();
                break;
            case "r":
                doRating();
                break;
            default:
                System.out.println("what you doin...?");
                break;
        }
    }

    private void doEditAddress() {
        System.out.println("what do you want to change the address to?");
        String newAddy = input.next();
        locationM.changeAddress(newAddy);
        System.out.println("as you wish");
    }

    private void doPs() {
        System.out.println("what do you want to change the P.S. note to?");
        String newPs = input.next();
        locationM.changePs(newPs);
        System.out.println("k");
    }

    private void doTag() {
        System.out.println("what do you want to change the tags to?");
        String newTags = input.next();
        System.out.println("say please.");
        String x = input.next();
        while (!x.equals("please")) {
            System.out.println("i said, say please.");
            x = input.next();
        }
        locationM.changeTags(newTags);
        System.out.println("good.");
    }

    private void doRating() {
        System.out.println("what do you want too change the rating to?");
        Double newRating = input.nextDouble();
        locationM.changeRating(newRating);
        System.out.println("mk");
    }

    private void saveLocationList() {
        try {
            jsonWriter.open();
            jsonWriter.write(myList);
            jsonWriter.close();
            System.out.println("you may leave in peace.");
        } catch (FileNotFoundException e) {
            System.out.println("sorry can't save :)");
        }
    }

    private void loadLocationList() {
        try {
            myList = jsonReader.read();
            System.out.println("got " + myList.getName() + " from" + JSON_STORE);
        } catch (IOException e) {
            System.out.println("sorry can't read file " + JSON_STORE);
        }
    }
}

