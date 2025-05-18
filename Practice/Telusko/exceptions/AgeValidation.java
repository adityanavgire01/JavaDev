class UnderageException extends Exception {
    public UnderageException(String message) {
        super(message);
    }
}

public class AgeValidation {
    public static void main(String[] args) {
        int age = 21;
        try {
            if (age < 18) {
                throw new UnderageException("You must be 18 or older to register.");
            }
            System.out.println("Registration Successfull");
        } catch (UnderageException e) {
            System.out.println("Registration Failed : " + e.getMessage());
        }
    }
}