
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.*;

/** 
 * JUnit tests cases for the Yoda Calculator.
 * 
 * @author Vitaly Ford
 */ 
public class YodaCalcTests {
    @Test
    public void simpleNestedParenthesisWithSingleDigits() {
        assertEquals(5.0, YodaCalc.calc("2 + 3 * ((5 - 8 / 4) + 6) / 9"));
    }

    @Test
    public void singleDigitPlusMinus() {
        assertEquals(-10.0, YodaCalc.calc("2+3-9 + 1 - 7"));
    }

    @Test
    public void multiDigitPlusMinus() {
        assertEquals(-86764.0, YodaCalc.calc("256+312-9456 + 11 - 77887"));
    }

    @Test
    public void multiDigitAllOpsWholeDecimal() {
        assertEquals(-4.0, YodaCalc.calc("512 / 256 * 4 + 256 / 16 * 7 - 12 * 9 - 32/2"));
    }

    @Test
    public void multiDigitAllOpsFractions() {
        // Test the result to be within [593.1; 593.3]
        assertEquals(593.2, YodaCalc.calc("591+10*6/(442-3*(61+7-8))+4/2"), 0.1);
    }

    @Test
    public void singleDigitWithSimpleParenthesis() {
        assertEquals(16.0, YodaCalc.calc("2+   3 - (9 + 5 * 8) / 7 + 9 * 2"));
    }

    @Test
    public void multiDigitWithSimpleParenthesis() {
        assertEquals(-199792.0, YodaCalc.calc("257+   356 - (9758 + 512 * 844) / 2 + 978 * 21"));
    }

    @Test
    public void simplestPlusWithoutSpaces() {
        assertEquals(13.0, YodaCalc.calc("4+9"));
    }

    @Test
    public void simplestMultiplyWithSpaces() {
        assertEquals(14.0, YodaCalc.calc("2 *  7"));
    }

    @Test
    public void manyParenthesisMultiDigit() {
        assertEquals(9494.0, YodaCalc.calc("712 + 22 * (5+(5+(5+(5+8/4*(5+6*4/2)+7*7)/2)*9)-74)+  61*8"));
    }
}
