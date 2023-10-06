package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new Places();
        } catch (FileNotFoundException e) {
            System.out.println("can't run; file not found");
        }
    }
}
