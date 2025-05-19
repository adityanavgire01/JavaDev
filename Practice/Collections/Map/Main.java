import java.util.*;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("apple", 100);
        map.put("banana", 33);
        map.put("pineapple", 2);
        map.put("grapes", 99);
        map.put("pear", 455);

        // for (Map.Entry<String, Integer> entry : map.entrySet()) {
        // System.out.println(entry.getKey() + ": " + entry.getValue());
        // }

        // int count = map.get("pineapple");
        // System.out.println(map.get("pineapple"));

        // gets value of key if it exist else returns default value
        // int count = map.getOrDefault("pineapple", 0);

        // if (map.containsKey("pineapple")) {
        // System.out.println("I'm here!");
        // }

        // for (Map.Entry<String, Integer> entry : map.entrySet()) {
        // System.out.println(entry.getKey() + ": " + entry.getValue());
        // }
        // map.remove("pineapple");
        // System.out.println();
        // for (Map.Entry<String, Integer> entry : map.entrySet()) {
        // System.out.println(entry.getKey() + ": " + entry.getValue());
        // }

        // for (String key : map.keySet()) {
        // System.out.println(key);
        // }

        // for (int value : map.values()) {
        // System.out.println(value);
        // }

        // for (Map.Entry<String, Integer> entry : map.entrySet()) {
        // System.out.println(entry.getKey() + ": " + entry.getValue());
        // }

    }
}