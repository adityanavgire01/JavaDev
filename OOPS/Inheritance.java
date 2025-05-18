class Animal {
    void eat() {System.out.println("eating...");}
}

class Dog extends Animal {
    void bark() {System.out.println("barking...");}
    void eat() {System.out.println("Dog eating...");} // overiding eat method
}

public class Inheritance {
    public static void main(String[] args) {
        Dog d1 = new Dog();

        d1.bark();
        d1.eat();
    }
}