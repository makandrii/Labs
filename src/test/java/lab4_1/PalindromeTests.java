package lab4_1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.makarov.lab4_1.Palindrome.isPalindrome;

public class PalindromeTests {
    @Test
    public void testPalindrome() {
        assertTrue(isPalindrome("level"));
        assertFalse(isPalindrome("not"));
    }

    @Test
    public void testIgnoreSpaces() {
        assertTrue(isPalindrome(" l e v e l "));
    }

    @Test
    public void testIgnoreRegister() {
        assertTrue(isPalindrome("LEvel"));
    }
}
