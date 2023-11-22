package test;

import currencyConverter.Currency;
import currencyConverter.CurrencyConverter;
import currencyConverter.MainWindow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlackBoxTests {

    private ArrayList<Currency> currencies = Currency.init();

    @ParameterizedTest
    @MethodSource("currencyCheck")
    void testCurrencyConvertingList(String c1, String c2){
        //System.out.println(MainWindow.convert(c1,c2, this.currencies, 1d));
        assertTrue(MainWindow.convert(c1,c2, Currency.init(), 1d) > 0d);
    }

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

    
}
