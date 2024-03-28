import org.example.StringUtils;
import org.example.Wallet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StringUtilsTest {
    private StringUtils testString;
    @BeforeEach
    void initMethod() {
        testString = new StringUtils();
    }

    @AfterEach
    void cleanMethod() {
        testString = null;
    }

    @Test
    public void testReverseStringNull() {
        assertAll(
                () -> assertNull(testString.reverseString(null))
        );
    }

    @Test
    public void testReverseString() {
        assertAll(
                () -> assertEquals("naijugneP", testString.reverseString("Pengujian")),
                () -> assertNotEquals("naijugnep", testString.reverseString("NAIJUGNEP")),
                () -> assertNotEquals("Pengujian", testString.reverseString("Pengujian"))
        );
    }

    @Test
    public void testIsPalindromeNull() {
        assertAll(
                () -> assertFalse(testString.isPalindrome(null))
        );
    }

    @Test
    public void testIsPalindrome() {
        assertAll(
                () -> assertTrue(testString.isPalindrome("Malam")),
                () -> assertFalse(testString.isPalindrome("Pagi"))
        );
    }

    @Test
    public void testCountVowels() {
        assertAll(
                () -> assertEquals(4, testString.countVowels("Pengujian")),
                () -> assertNotEquals(0, testString.countVowels("Pengujian"))
        );
    }

    @Test
    public void testCountNullVowels() {
        assertAll(
                () -> assertEquals(0, testString.countVowels(null))
        );
    }


}
