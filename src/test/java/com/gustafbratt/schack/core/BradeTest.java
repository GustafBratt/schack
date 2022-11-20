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
        brade.setPjas(new Position("a1"), 'B');
        brade.setPjas(new Position("a8"), 'k');
        brade.print();
        brade = brade.klonaOchFlippa();
        brade.print();
        var p = brade.charPa(new Position("a1"));
        System.out.println("Pjäs på a1: " + p);
        assertThat(p).isEqualTo('b');
    }

    @Test
    public void flippa2() throws UtanforBradetException {
        Brade brade = new Brade(TOMT);
        brade.setPjas(new Position("b7"), 'K'); // g2
        brade.setPjas(new Position("b2"), 'b'); // g7
        brade.print();
        brade = brade.klonaOchFlippa();
        brade.print();
        assertThat(brade.charPa(new Position("b7"))).isEqualTo('k');
        assertThat(brade.charPa(new Position("b2"))).isEqualTo('B');
        System.out.println(new Bonde(brade, new Position("b2")).getMojligaDrag());
        brade = brade.klonaOchFlippa();
        brade.print();
        assertThat(brade.charPa(new Position("b7"))).as("b7").isEqualTo('K');
        assertThat(brade.charPa(new Position("b2"))).as("b2").isEqualTo('b');
        Kung kung = new Kung(brade, new Position("b7"));
        System.out.println(kung.getMojligaDrag());
        brade = brade.klonaOchFlippa();
        brade.print();
        Bonde bonde = new Bonde(brade, new Position("b2"));
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
        b = b.utforDrag(new Drag(b, new Position("e2"), new Position("e3")));
        b.print();
        assertThat(b.poang()).isGreaterThan(0);
        b = b.utforDrag(new Drag(b, new Position("e7"), new Position("e5")));
        b.print();
        assertThat(b.poang()).isLessThan(0);
    }

    @Test
    public void poangFlyttFram() throws UtanforBradetException {
        Brade b = new Brade(TOMT);
        b.setPjas(new Position("d1"), 'B');
        b.setPjas(new Position("e1"), 'K');
        b.setPjas(new Position("e8"), 'k');
        b.print();
        System.out.println(b.beraknaPoang());
        assertThat(b.poang()).isEqualTo(2); //En poäng för existens, en poäng för rad.
        b.setPjas(new Position("a2"), 'D');
        b.print();
        assertThat(b.beraknaPoang()).isEqualTo(13); //2 + 9 + 2
    }

    @Test
    public void poangFlyttFram2() throws UtanforBradetException {
        Brade b = new Brade(TOMT);
        b.setPjas(new Position("d8"), 'b');
        b.setPjas(new Position("e1"), 'K');
        b.setPjas(new Position("e8"), 'k');
        b.print();
        System.out.println(b.beraknaPoang());

    }

    @Test
    public void poang4() throws UtanforBradetException {
        Brade b = new Brade(TORN_MOT_KUNG);
        b.print();
        Drag d = new Drag(b, new Position("h8"), new Position("h7"));
        b = b.utforDrag(d);
        b.print();
    }

    @Test
    public void poang2() throws UtanforBradetException {
        Brade b = new Brade(TOMT);
        b.setPjas(new Position("d4"), 'k'); //VITs drag. svart kung. INT_MIN
        b.setPjas(new Position("d3"), 'b');
        b.setPjas(new Position("d2"), 'B');
        b.print();
        assertThat(b.poang()).isLessThan(5_000);
    }

    @Test
    public void poang3() throws UtanforBradetException {
        Brade b = new Brade(TOMT);
        b.setPjas(new Position("d4"), 'K'); //SVARTs drag. svart kung. INT_MAX
        b.setPjas(new Position("d3"), 'b');
        b.setPjas(new Position("d2"), 'B');
        b.print();
        assertThat(b.poang()).isGreaterThan(5_000);
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
        var p = b.getPjas(new Position("d8"));
        System.out.println(p.get());
        /*Bonde bonde = new Bonde(b, new Position("h2"));
        var bondensMojliga = bonde.getMojligaDrag();
        System.out.println(bondensMojliga);
        var allMojliga = b.allaMojligaDrag();
        System.out.println(allMojliga);*/
    }

    private Brade skapaBradeMedExtraPjas(String pos, char pjas) throws UtanforBradetException {
        Brade b = new Brade(START);
        b.setPjas(new Position(pos), pjas);
        b.print();
        return b;
    }
}
