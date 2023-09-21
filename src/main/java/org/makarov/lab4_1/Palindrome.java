package org.makarov.lab4_1;

public class Palindrome {
    public static boolean isPalindrome(String string) {
        var input = string.trim().toUpperCase();
        var length = input.length();

        for (int i = 0; i < Math.ceil(length / 2.0); i++) {
            if (input.charAt(i) != input.charAt(length - i - 1)) {
                return false;
            }
        }

        return true;
    }
}
