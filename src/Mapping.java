import java.util.HashMap;
import java.util.Scanner;

public class Mapping {

    public static final int INITIAL_LOCATION = 95;
    static LocationMap locationMap = new LocationMap();

    /**
      a vocabulary HashMap to store all directions a user can go
     */
    HashMap<String, String> vocabulary = new HashMap<>();

    FileLogger studentFileLog = new FileLogger();
    ConsoleLogger consoleLog = new ConsoleLogger();


    public Mapping() {

        /**
         * the vocabulary HashMap <Key, Value> with all directions.
         */
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("EAST", "E");
        vocabulary.put("WEST", "W");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
    }

    public void mapping() {

        Scanner userInput = new Scanner(System.in);
        Location playerLocation = new Location(INITIAL_LOCATION, locationMap.get(INITIAL_LOCATION).getDescription(),
                locationMap.get(INITIAL_LOCATION).getExits());

        boolean isExit = false;

        while (!isExit) {

            /**
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            studentFileLog.log(playerLocation.getDescription());
            consoleLog.log(playerLocation.getDescription());

            /**
             * verify if the location is exit
             */
            if (playerLocation.getLocationId() == 0) {
                isExit = true;
                break;
            } else
                isExit = false;

            /**
             * get a map of the exits for the location
             */

            locationMap.get(playerLocation.getLocationId()).getExits();
            StringBuilder appendExits = new StringBuilder();

            for (String key : locationMap.get(playerLocation.getLocationId()).getExits().keySet()) {
                appendExits.append(key);
                appendExits.append(", ");
            }
            studentFileLog.log("Available exits are " + appendExits);
            consoleLog.log("Available exits are " + appendExits);

            /** TODO
             * input a direction and ensure that the input is converted to uppercase
             */
            String directionInput = userInput.nextLine().trim().toUpperCase();

            /**
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the keys
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */
            boolean directionValid = false;
            String delimiter = " ";
            String[] inputArray = directionInput.split(delimiter);
            String playerDirection = "";

            for (int i = 0; i < inputArray.length; i++) {

                playerDirection = inputArray[i].trim();

                if (vocabulary.containsKey(playerDirection)) {
                    directionValid = true;
                    playerDirection = vocabulary.get(playerDirection);
                    break;
                } else if (!directionValid) {
                    for (String value : vocabulary.values()) {
                        if (directionInput.equals(value)) {
                            directionValid = true;
                            break;
                        }
                    }
                } else if (!directionValid) {

                    for (String key : vocabulary.keySet()) {
                        if (playerDirection.contains(key)) {
                            directionValid = true;
                            playerDirection = vocabulary.get(key);
                            break;
                        }
                    }
                } else directionValid = false;
            }

            if (directionValid) {
                for (String exit : playerLocation.getExits().keySet()) {
                    if (playerDirection.equals(exit)) {
                        directionValid = true;
                        break;
                    } else
                        directionValid = false;
                }
            }

            /**
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             */

            if (directionValid) {

                int newLocationId = locationMap.get(playerLocation.getLocationId()).getExits().get(playerDirection);

                playerLocation = new Location(newLocationId, locationMap.get(newLocationId).getDescription(),
                        locationMap.get(newLocationId).getExits());
            } else {
                studentFileLog.log("You cannot go in that direction");
                consoleLog.log("You cannot go in that direction");
            }
        }
    }


    public static void main(String[] args) {
        /**
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping playGame = new Mapping();
        playGame.mapping();
    }
}

