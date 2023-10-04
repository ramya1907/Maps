import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    static HashMap<Integer, Location> locations = new HashMap<>();

    static {

        FileLogger studentFileLog = new FileLogger();
        ConsoleLogger consoleLog = new ConsoleLogger();

        /**
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * put each location in the locations HashMap using temporary empty hashmaps for exits
         */

        String[] locationArray;
        String delimiter = ",";
        studentFileLog.log("Available locations:");
        consoleLog.log("Available locations:");
        try (FileReader locationFile = new FileReader(LOCATIONS_FILE_NAME);
             BufferedReader locationReader = new BufferedReader(locationFile))
        {
            String lineReader1 = locationReader.readLine();

            while (lineReader1 != null) {
                locationArray = lineReader1.split(delimiter, 2);

                int location = Integer.parseInt(locationArray[0]);
                String description = locationArray[1];

                studentFileLog.log(String.format("%d: %s", location, description));
                consoleLog.log(String.format("%d: %s", location, description));

                LinkedHashMap<String, Integer> tempHM = new LinkedHashMap<>();
                Location locValues = new Location(location, description, tempHM);
                locations.put(location, locValues);

                lineReader1 = locationReader.readLine();
            }

        } catch (IOException error) {
            error.printStackTrace();
        }


        /**
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * for each location, create a new location object and add its exit
         */
        String[] directionArray;
        studentFileLog.log("Available directions:");
        consoleLog.log("Available directions:");
        try (FileReader directionFile = new FileReader(DIRECTIONS_FILE_NAME);
             BufferedReader directionReader = new BufferedReader(directionFile))
        {
            String lineReader2 = directionReader.readLine();

            while (lineReader2 != null) {

                directionArray = lineReader2.split(delimiter, 3);

                int location = Integer.parseInt(directionArray[0]);
                String direction = directionArray[1];
                int destination = Integer.parseInt(directionArray[2]);

                LinkedHashMap<String, Integer> exitHM = new LinkedHashMap<>();

                Location newLocation = new Location(location ,locations.get(location).getDescription(), exitHM);

                locations.get(location).addExit(direction,destination);

                studentFileLog.log(String.format("%d: %s: %d", location, direction, destination));
                consoleLog.log(String.format("%d: %s: %d", location, direction, destination));

                lineReader2 = directionReader.readLine();
            }
        }
        catch (IOException error) {
            error.printStackTrace();
        }

    }

    /**
     * implement all methods for Map
     *
     * @return
     */
    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.isEmpty();

    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);

    }

    @Override
    public boolean containsValue(Object value) {

        return locations.containsValue(value);

    }

    @Override
    public Location get(Object key) {

        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {

        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {

        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

        locations.putAll(m);
    }

    @Override
    public void clear() {

        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
