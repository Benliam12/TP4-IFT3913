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









}
