

import java.util.*;;

public class LearnArrayList {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>();

        l1.add("Aditya");
        l1.add("Pradeep");
        l1.add("Navgire");
        l1.add("Rocks!");

        System.out.println(l1.get(0));
        l1.remove(0);
        System.out.println(l1.get(0));
        System.out.println(l1.get(1));
        System.out.println(l1.get(3));
    
    }
}
