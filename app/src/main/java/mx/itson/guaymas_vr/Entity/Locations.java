package mx.itson.guaymas_vr.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Locations {


    public static final List<Location> ITEMS = new ArrayList<Location>();


    public static final Map<String, Location> ITEM_MAP = new HashMap<String, Location>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createLocation(i));
        }
    }

    private static void addItem(Location location) {
        ITEMS.add(location);
        ITEM_MAP.put(location.id, location);
    }

    private static Location createLocation(int position) {
        return new Location(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Location: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


    public static class Location {
        public final String id;
        public final String content;
        public final String details;

        public Location(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
