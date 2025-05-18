import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> cities = new ArrayList<>();
        cities.add("Mumbai3");
        cities.add("Kolkata");
        cities.add("Chennai");

        // cities.add("Delhi");

        int index = cities.indexOf("Chennai");
        if (index != -1) {
            cities.set(index, "Blueberry");
        }

        for (String city : cities) {
            System.out.println(city);
        }
        System.out.println();
        cities.add(3, "San Jose");

        for (String city : cities) {
            System.out.println(city);
        }

        System.out.println();
        cities.add(4, "Mumbai2");

        Collections.sort(cities);

        for (String city : cities) {
            System.out.println(city);
        }

        Collections.reverse(cities);
        System.out.println();
        for (String city : cities) {
            System.out.println(city);
        }

    }
}