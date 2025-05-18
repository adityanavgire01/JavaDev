public class Exceptions {
    public static void main(String[] args) {
        int i = 2;
        int j = 0;
        int[] nums = new int[5];

        try {
            j = 10 / i;
            nums[5] = 2;
        } catch (ArithmeticException e) {
            System.out.println("first " + e);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("second " + e);
        }
    }
}
