package test;

import currencyConverter.Currency;
import currencyConverter.MainWindow;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BlackBoxTests {

    private ArrayList<Currency> currencies = Currency.init();

    /**
     * We test every single pair of currencies.
     * @param c1 Currency 1
     * @param c2 Currency 2
     */
    @ParameterizedTest
    @MethodSource("currencyCheck")
    void testCurrencyConvertingList(String c1, String c2){
        assertTrue(MainWindow.convert(c1,c2, currencies, 1d) > 0d);
    }

    /**
     * Generates every possible currency trading.
     * @return List of every possible currency trading needed for the test.
     */
    static Stream<String[]> currencyCheck(){
        ArrayList<String[]> pairs = new ArrayList<>();
        String[] currencies = {"US Dollar", "Canadian Dollar", "British Pound", "Euro", "Swiss Franc", "Australian Dollar"};
        for (String s1 : currencies){
            for (String s2 : currencies){
                if(!s1.equalsIgnoreCase(s2)){
                    String[] p = {s1,s2};
                    pairs.add(p);
                }
            }
        }
        return pairs.stream();
    }

    /**
     * This tests for values in [0, 1 000 000].
     * We assume, if we try values outside of this threshold, we get a null value.
     */
    @ParameterizedTest
    @ValueSource(doubles={-500d,-1d, 0, 500000d, 1000000d, 1000001d})
    public void testCorrectAmount(Double amount){
        if(amount <= 1000000 && amount >= 0){
            assertEquals(Currency.convert(amount, 1d),amount);
        }
        else{
            assertNull(Currency.convert(amount, 1d));
        }
    }

}
