package mx.itson.guaymas_vr.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Locations {


    public static final List<Location> ITEMS = new ArrayList<Location>();


    public static final Map<String, Location> ITEM_MAP = new HashMap<String, Location>();


    public static void addItem(String file, String title, String description) {
        Location location = new Location(file, title, description);
        ITEMS.add(location);
        ITEM_MAP.put(location.file, location);
    }


    public static class Location {
        public final String file;
        public final String title;
        public final String description;

        Location(String file, String title, String description) {
            this.file = file;
            this.title = title;
            this.description = description;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
