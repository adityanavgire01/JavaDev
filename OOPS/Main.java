class Student {
    int rollNo;
    String name;

    void insertRecord(int r, String n) {
        rollNo = r;
        name = n;
    }

    void displayRecord() {
        System.out.println(rollNo + " " + name);
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student();
        // Student s3 = (Student) s1.clone();


        s1.insertRecord(101, "Aditya");
        s2.insertRecord(112, "Sonali");

        s1.displayRecord();
        s2.displayRecord();
    }
}
