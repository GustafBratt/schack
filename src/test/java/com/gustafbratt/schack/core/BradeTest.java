package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.Bonde;
import com.gustafbratt.schack.core.pjas.Kung;
import org.junit.jupiter.api.Test;

import static com.gustafbratt.schack.core.Brade.BRADE_INIT_TYP.*;
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
        brade.setPjas("a1", 'B');
        brade.setPjas("a8", 'k');
        brade.print();
        brade = brade.klonaOchFlippa();
        brade.print();
        var p = brade.charPa("a1");
        System.out.println("Pjäs på a1: " + p);
        assertThat(p).isEqualTo('b');
    }

    @Test
    public void flippa2() throws UtanforBradetException {
        Brade brade = new Brade(TOMT);
        brade.setPjas("b7", 'K'); // g2
        brade.setPjas("b2", 'b'); // g7
        brade.print();
        brade = brade.klonaOchFlippa();
        brade.print();
        assertThat(brade.charPa("b7")).isEqualTo('k');
        assertThat(brade.charPa("b2")).isEqualTo('B');
        System.out.println(new Bonde(brade, "b2").getMojligaDrag());
        brade = brade.klonaOchFlippa();
        brade.print();
        assertThat(brade.charPa("b7")).as("b7").isEqualTo('K');
        assertThat(brade.charPa("b2")).as("b2").isEqualTo('b');
        Kung kung = new Kung(brade, "b7");
        System.out.println(kung.getMojligaDrag());
        brade = brade.klonaOchFlippa();
        brade.print();
        Bonde bonde = new Bonde(brade, "b2");
        System.out.println(bonde.getMojligaDrag());
    }

    @Test
    public void poang() throws UtanforBradetException {
        Brade b = new Brade(START);
        System.out.println("==== START ====");
        b.print();
        System.out.println("== SLUT START =");
        int startPoang = b.poang();
        assertThat(startPoang).isEqualTo(0);
        b = b.utforDrag(new Drag(b, "e2", "e3"));
        b.print();
        assertThat(b.poang()).isGreaterThan(0);
        b = b.utforDrag(new Drag(b, "e7", "e5"));
        b.print();
        assertThat(b.poang()).isLessThan(0);
    }

    @Test
    public void poangFlyttFram() throws UtanforBradetException {
        Brade b = new Brade(TOMT);
        b.setPjas("d1", 'B');
        b.setPjas("e1", 'K');
        b.setPjas("e8", 'k');
        b.print();
        System.out.println(b.beraknaPoang());
        assertThat(b.poang()).isEqualTo(2); //En poäng för existens, en poäng för rad.
        b.setPjas("a2", 'D');
        b.print();
        assertThat(b.beraknaPoang()).isEqualTo(13); //2 + 9 + 2
    }

    @Test
    public void poangFlyttFram2() throws UtanforBradetException {
        Brade b = new Brade(TOMT);
        b.setPjas("d8", 'b');
        b.setPjas("e1", 'K');
        b.setPjas("e8", 'k');
        b.print();
        System.out.println(b.beraknaPoang());

    }

    @Test
    public void poang2() throws UtanforBradetException {
        Brade b = new Brade(TOMT);
        b.setPjas("d4", 'k'); //VITs drag. svart kung. INT_MIN
        b.setPjas("d3", 'b');
        b.setPjas("d2", 'B');
        b.print();
        assertThat(b.poang()).isLessThan(5_000);
    }

    @Test
    public void poang3() throws UtanforBradetException {
        Brade b = new Brade(TOMT);
        b.setPjas("d4", 'K'); //SVARTs drag. svart kung. INT_MAX
        b.setPjas("d3", 'b');
        b.setPjas("d2", 'B');
        b.print();
        assertThat(b.poang()).isGreaterThan(4_000);
    }


    @Test
    public void allaMojligaDrag() throws UtanforBradetException {
        Brade b = new Brade(BONDER_DAM_KUNG);
        b.print();
        var d = b.beraknaMojligaDrag();
        System.out.println(d);
        assertThat(d).hasSize(20);
        b = b.klonaOchFlippa();
        b.print();
        d = b.beraknaMojligaDrag();
        System.out.println(d);
        assertThat(d).hasSize(20);
        assertThat(d.stream().map(Drag::toString)).contains("Bh7-h6");
    }

    @Test
    public void flippa3() throws UtanforBradetException {
        Brade b = new Brade(BONDER_DAM_KUNG);
        b.print();
        b = b.klonaOchFlippa();
        b.print();
        var p = b.getPjas("d8");
        System.out.println(p.get());
    }

    @Test
    public void vitKungFlyttad() throws UtanforBradetException {
        Brade b = new Brade(BONDER_DAM_KUNG);
        b.print();
        assertThat(b.aktuellKungHarFlyttatPaSig()).isFalse();

        Brade b2 = new Drag(b, "e1", "f1").utfor(); //Flytta vit kung
        b2.print();
        assertThat(b.aktuellKungHarFlyttatPaSig()).isFalse();
        assertThat(b2.aktuellKungHarFlyttatPaSig()).isFalse();

        Brade b3 = new Drag(b2, "e8", "f8").utfor(); //Flytta svart kung
        b3.print();
        assertThat(b3.aktuellKungHarFlyttatPaSig()).isTrue(); //vit aktuell

        Brade b4 = new Drag(b3, "f1", "e1").utfor(); //Flytta tillbaka vit kung
        b4.print();
        assertThat(b4.aktuellKungHarFlyttatPaSig()).isTrue(); //Svart aktuell

        Brade b5 = new Drag(b4, "f8", "e8").utfor(); //Flytta tillbaka svart kung
        b5.print();
        assertThat(b5.aktuellKungHarFlyttatPaSig()).isTrue(); //Vit aktuell

    }
}
