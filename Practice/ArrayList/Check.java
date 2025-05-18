import java.util.ArrayList;


public class Check {
    public static void main(String[] args) {
        ArrayList<String> cars = new ArrayList<>();
        cars.add("Honda");   
        cars.add("Tesla");   
        cars.add("BMW");   
        System.out.println(cars);
        System.out.println(cars.get(2));
        cars.add(0, "Maruti");
        System.out.println(cars);
        System.out.println(cars.get(2));
        cars.remove(0);
        System.out.println(cars);
    }
}