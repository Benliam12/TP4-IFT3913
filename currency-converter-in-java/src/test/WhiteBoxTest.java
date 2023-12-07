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

public class WhiteBoxTest {


    @ParameterizedTest
    @ValueSource(doubles={-500d,-1d, 0, 500000d, 1000000d, 1000001d, 0.003d, 5.4d, 1.5987435d, 6d})
    void whiteBoxTestCurrencyConvert(Double amount){
        assertEquals(Currency.convert(amount, 1d), Math.round(amount*100d) / 100d);
    }

    //B: Pour la méthode convert de Currency, il n'y a aucun branchement donc pas logique de vérifier le critère B
    //C: Encore une fois il n'y a aucun branchement donc il y a un seul 1-chemin.
    // On s'assure que la méthode prend les décimals, docn il couvre tout les cas.
    //D: Il n'y a auncun if dans la méthode.
    //E: Il n'y a acune boucle dans la méthode.

    private ArrayList<Currency> currencies = Currency.init();
    @Test
    void whiteBoxTestMainWindowConvertA(){
        assertTrue(MainWindow.convert(this.currencies.get(1).getName(), this.currencies.get(0).getName(),this.currencies,1d) > 0 );
    }

    // Pour le cas ou tout les if sont passé au moins une fois pour B il est testé dans convertA
    @Test
    void whiteBoxTestMainWindowConvertB1(){
        assertEquals(MainWindow.convert("test", this.currencies.get(0).getName(),this.currencies,1d), 0.0 );
    }

    @Test
    void whiteBoxTestMainWindowConvertB2(){
        assertEquals(MainWindow.convert(this.currencies.get(1).getName(), "test",this.currencies,1d), 0.0 );
    }

    @Test
    void whiteBoxTestMainWindowConvertC1(){
        assertEquals(MainWindow.convert(this.currencies.get(1).getName(), this.currencies.get(0).getName(),new ArrayList<Currency>(),1d), 0 );
    }

    // chemin parcourt les boucles 0 fois. Sinon dans A, B1 et B2 on passe dans toutes les boucles.
    @Test
    void whiteBoxTestMainWindowConvertC2(){
        assertEquals(MainWindow.convert("test", this.currencies.get(0).getName(),new ArrayList<Currency>(),1d), 0.0 );
    }

    @Test
    void whiteBoxTestMainWindowConvertC3(){
        assertEquals(MainWindow.convert(this.currencies.get(1).getName(), "test",new ArrayList<Currency>(),1d), 0.0 );
    }

    //D: auncun if avec condition composé

    //E: Aucune boucle imbriqué ni concaténé puis ont test toutes les itérations possibles de paires de devises.

    /**
     * We test every single pair of currencies.
     * @param c1 Currency 1
     * @param c2 Currency 2
     */
    @ParameterizedTest
    @MethodSource("currencyCheck")
    void testCurrencyConvertingList(String c1, String c2){
        //System.out.println(MainWindow.convert(c1,c2, this.currencies, 1d));
        assertTrue(MainWindow.convert(c1,c2, currencies, 1d) > 0d);
    }

    /**
     * Generates every possible currency trading.
     * @return List of every possible currency trading needed for the test.
     */
    static Stream<String[]> currencyCheck(){
        ArrayList<String[]> pairs = new ArrayList<>();
        ArrayList<Currency> currency = Currency.init();

        for (Currency s1 : currency){
            for (Currency s2 : currency){
                if(!s1.getName().equalsIgnoreCase(s2.getName())){
                    String[] p = {s1.getName(),s2.getName()};
                    pairs.add(p);
                }
            }
        }
        return pairs.stream();
    }














}
