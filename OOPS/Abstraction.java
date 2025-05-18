abstract class Bike {
    abstract void run();
}

class Honda extends Bike{
    void run() {System.out.println("Honda running..");}
}

public class Abstraction {
    public static void main(String[] args) {
        Bike obj1 = new Honda();
        obj1.run();
    }
}
