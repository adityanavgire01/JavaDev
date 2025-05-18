class A {
    int i = 10;
    public void display() {System.out.println("A i: " + i);}
}
class B extends A {
    int i = 100;
    public void display() {
        super.display();
        System.out.println("B i: " + i);
    }
}

public class Check {
    public static void main(String[] args) {
        B obj1 = new B();
        obj1.display();
    }
}