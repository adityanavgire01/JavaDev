// 2 strings are called anagram if they contain same characters, but the order of sequence of characters can be different

import java.util.Arrays;

public class Anagram {
    public static void main(String[] args) {
        String str1 = "brag";
        String str2 = "grab";

        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        if (str1.length() != str2.length()) {
            System.out.println("Length not same. No Anagram");
        } else {
            char[] s1 = str1.toCharArray();
            char[] s2 = str2.toCharArray();

            Arrays.sort(s1);
            Arrays.sort(s2);

            if (Arrays.equals(s1, s2)) {
                System.out.println("Both strings are Anagram");
            } else {
                System.out.println("Both strings are not Anagram");
            }
        }
    
    }
}