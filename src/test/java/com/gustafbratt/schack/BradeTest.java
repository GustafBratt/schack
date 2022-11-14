package com.gustafbratt.schack;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.UtanforBradetException;
import com.gustafbratt.schack.core.pjas.Bonde;
import com.gustafbratt.schack.core.pjas.Kung;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BradeTest {
    @Test
    public void print() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.print();
    }

    @Test
    public void flippa() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(brade.position("a1"), 'B');
        brade.setPjas(brade.position("a8"), 'k');
        brade.print();
        brade = brade.klonaOchFlippa();
        brade.print();
        var p = brade.charPa(brade.position("a1"));
        System.out.println("Pjäs på a1: " + p);
        assertThat(p).isEqualTo('b');
    }

    @Test
    public void flippa2() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(brade.position("b7"), 'K'); // g2
        brade.setPjas(brade.position("b2"), 'b'); // g7
        brade.print();
        brade = brade.klonaOchFlippa();
        brade.print();
        assertThat(brade.charPa(brade.position("b7"))).isEqualTo('k');
        assertThat(brade.charPa(brade.position("b2"))).isEqualTo('B');
        System.out.println(new Bonde(brade, brade.position("b2")).getMojligaDrag());
        brade = brade.klonaOchFlippa();
        brade.print();
        assertThat(brade.charPa(brade.position("b7"))).as("b7").isEqualTo('K');
        assertThat(brade.charPa(brade.position("b2"))).as("b2").isEqualTo('b');
        Kung kung = new Kung(brade, brade.position("b7"));
        System.out.println(kung.getMojligaDrag());
        brade = brade.klonaOchFlippa();
        brade.print();
        Bonde bonde = new Bonde(brade, brade.position("b2"));
        System.out.println(bonde.getMojligaDrag());
    }
}
