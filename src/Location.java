import java.util.LinkedHashMap;
import java.util.Map;

public class Location {

    private final int LOCATION_ID;
    private final String DESCRIPTION;
    private final LinkedHashMap<String, Integer> EXITS = new LinkedHashMap<>();


    public Location(int locationId, String description, Map<String, Integer> exits) {

        LOCATION_ID = locationId;
        DESCRIPTION = description;

        /**
         * if exits are not null, set the exit
         * otherwise, set the exit HashMap to (Q,0)
         */

        if (exits.isEmpty())
            EXITS.put("Q", 0);
        else {
            EXITS.putAll(exits);
        }
    }

    protected void addExit(String direction, int location) {
        EXITS.put(direction, location);
    }

    public int getLocationId() {
        return LOCATION_ID;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public Map<String, Integer> getExits() {
        LinkedHashMap<String, Integer> copyExits = new LinkedHashMap<>();
        copyExits.putAll(EXITS);
        return copyExits;
    }
}
