package com.gustafbratt.schack;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.UtanforBradetException;
import com.gustafbratt.schack.core.pjas.Bonde;
import com.gustafbratt.schack.core.pjas.Kung;
import org.junit.jupiter.api.Test;

import static com.gustafbratt.schack.core.Brade.BRADE_INIT_TYP.START;
import static com.gustafbratt.schack.core.Brade.BRADE_INIT_TYP.TOMT;
import static org.assertj.core.api.Assertions.assertThat;

public class BradeTest {
    @Test
    public void print() {
        Brade brade = new Brade(START);
        brade.print();
    }

    @Test
    public void flippa() throws UtanforBradetException {
        Brade brade = new Brade(TOMT);
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
        Brade brade = new Brade(TOMT);
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

    @Test
    public void poang() throws UtanforBradetException {
        Brade b = skapaBradeMedExtraPjas("a4", '.');
        assertThat(b.poang()).isEqualTo(0);

        b = skapaBradeMedExtraPjas("e8", '.'); //Vit kung borta. Svart vinner.
        assertThat(b.poang()).isEqualTo(Integer.MIN_VALUE);

        b = skapaBradeMedExtraPjas("e1", '.'); //Svart kung borta. VIt vinner
        assertThat(b.poang()).isEqualTo(Integer.MAX_VALUE);

        b = skapaBradeMedExtraPjas("e6", 'D');
        assertThat(b.poang()).isEqualTo(9);
        b = b.klonaOchFlippa();
        b.print();
        assertThat(b.poang()).isEqualTo(9);

        b = skapaBradeMedExtraPjas("e6", 'd');
        assertThat(b.poang()).isEqualTo(-9);
        b = b.klonaOchFlippa();
        assertThat(b.poang()).isEqualTo(-9);

    }

    private Brade skapaBradeMedExtraPjas(String pos, char pjas) throws UtanforBradetException {
        Brade b = new Brade(START);
        b.setPjas(b.position(pos), pjas);
        b.print();
        return b;
    }
}
