package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.*;
import org.junit.jupiter.api.Test;

import static com.gustafbratt.schack.core.SpelStatus.PAGAR;
import static com.gustafbratt.schack.core.SpelStatus.TRE_UPPREPNINGAR;
import static com.gustafbratt.schack.core.StartBraden.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BradeTest {
    @Test
    public void print() {
        Brade brade = new Brade(START);
        brade.print();
    }

    @Test
    public void flippa() {
        Brade brade = new Brade(TOMT);
        brade.setPjas("a1", 'B');
        brade.setPjas("a8", 'k');
        brade.print();
        Brade b2 = new Brade(TOMT);
        b2.klonaOchFlippa(brade);
        b2.print();
        var p = b2.charPa("a1");
        System.out.println("Pjäs på a1: " + p);
        assertThat(p).isEqualTo('b');
    }

    @Test
    public void flippa2() {
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
    public void poang() throws OgiltigtDragException {
        Brade b = new Brade(START);
        System.out.println("==== START ====");
        b.print();
        System.out.println("== SLUT START =");
        int startPoang = b.poang();
        assertThat(startPoang).isEqualTo(0);
        b = b.hittaDrag("e2", "e3").utfor();
        //b = new Brade(b.hittaDrag( "e2", "e3"));
        b.print();
        assertThat(b.poang()).isGreaterThan(0);
        b = new Brade(b.hittaDrag( "e7", "e5"));
        b.print();
//        assertThat(b.poang()).isLessThan(0);
    }

    @Test
    public void merPoangTest() throws OgiltigtDragException {
        Brade brade = new Brade(TOMT);
        //brade.setPjas("a1", 'K');
        brade.setPjas("h1", 'k');
        brade.setPjas("c4", 'D');
        brade.print();
        System.out.println(brade.poang());
        var b2 = brade.hittaDrag( "c4", "c3").utfor();
        b2.print();
        System.out.println(b2.poang());
    }


    @Test
    public void poangFlyttFram2() {
        Brade b = new Brade(TOMT);
        b.setPjas("d8", 'b');
        b.setPjas("e1", 'K');
        b.setPjas("e8", 'k');
        b.print();
        System.out.println(b.beraknaPoang());

    }

    @Test
    public void poang2() {
        Brade b = new Brade(TOMT);
        b.setPjas("d4", 'k'); //VITs drag. svart kung. INT_MIN
        b.setPjas("d3", 'b');
        b.setPjas("d2", 'B');
        b.print();
        assertThat(b.poang()).isLessThan(5_000);
    }

    @Test
    public void poang3() {
        Brade b = new Brade(TOMT);
        b.setPjas("d4", 'K'); //SVARTs drag. svart kung. INT_MAX
        b.setPjas("d3", 'b');
        b.setPjas("d2", 'B');
        b.print();
        assertThat(b.poang()).isGreaterThan(4_000);
    }


    @Test
    public void flippa3() {
        Brade b = new Brade(BONDER_DAM_KUNG);
        b.print();
        b = b.klonaOchFlippa();
        b.print();
        var p = b.getPjas("d8");
        System.out.println(p.get());
    }

    @Test
    public void vitKungFlyttad() throws OgiltigtDragException {
        Brade b = new Brade(BONDER_DAM_KUNG_TORN);
        b.print();
        assertThat(b.isVitKungFlyttad()).isFalse();

        Brade b2 = b.hittaDrag( "e1", "f1").utfor(); //Flytta vit kung
        b2.print();
        assertThat(b.isVitKungFlyttad()).isFalse();
        assertThat(b2.isSvartKungFlyttad()).isFalse();

        Brade b3 = b2.hittaDrag( "e8", "f8").utfor(); //Flytta svart kung
        b3.print();
        assertThat(b3.isVitKungFlyttad()).isTrue(); //vit aktuell

        Brade b4 = b3.hittaDrag( "f1", "e1").utfor(); //Flytta tillbaka vit kung
        b4.print();
        assertThat(b4.isSvartKungFlyttad()).isTrue(); //Svart aktuell

        Brade b5 = b4.hittaDrag( "f8", "e8").utfor(); //Flytta tillbaka svart kung
        b5.print();
        assertThat(b5.isVitKungFlyttad()).isTrue(); //Vit aktuell

    }

    @Test
    public void promovering() throws OgiltigtDragException {
        Brade b = new Brade(TORN_MOT_KUNG);
        b.setPjas("b7", 'B');
        b.print();
        var allaDrag = b.getMojligaDrag();
        System.out.println(allaDrag);
        assertThat(allaDrag.stream().map(Drag::toString)).contains("Db7-b8=Q");
        assertThat(allaDrag.stream().map(Drag::toString)).contains("Db7-a8xT=Q");
        Drag d = b.hittaDrag( "b7", "a8");
        b = d.utfor();
        b.print();
    }

    @Test
    void equalsOchHashcode() {
        var bradeClass = Brade.class;
        var falt = bradeClass.getDeclaredFields();
        assertThat(falt).as("Dags att uppdatera equals() och hashcode()").hasSize(13);
    }

    @Test
    //"a draw if the same position occurs three times during the game"
    void remiEfterTreDrag() throws OgiltigtDragException {
        Brade b = new Brade(TORN_MOT_KUNG);
        b.setPjas("h5", 'T');
        b = b.hittaDrag("h5", "h6").utfor();
        b = b.hittaDrag("a8", "a7").utfor();

        System.out.println("'start'");
        b.print(); //A 1
        b = b.hittaDrag("e2", "d2").utfor(); //Flytt
        b.print(); //B
        b = b.hittaDrag("f8", "e8").utfor();
        b.print(); //C

        b = b.hittaDrag("d2", "e2").utfor(); //Tillbaka
        b.print(); //D
        b = b.hittaDrag("e8", "f8").utfor();
        System.out.println("tillbaka");
        b.print(); //A 2

        b = b.hittaDrag("e2", "d2").utfor(); //Flytt
        b.print(); //B
        b = b.hittaDrag("f8", "e8").utfor();
        b.print(); //C

        b = b.hittaDrag("d2", "e2").utfor(); //Tillbaka
        b.print(); //D
        assertThat(b.getSpelStatus()).isEqualTo(PAGAR);
        b = b.hittaDrag("e8", "f8").utfor();
        b.print(); //A 3
        assertThat(b.getSpelStatus()).isEqualTo(TRE_UPPREPNINGAR);

        b = b.hittaDrag("e2", "d2").utfor(); //Flytt
        b.print(); //B
        assertThat(b.getSpelStatus()).isEqualTo(TRE_UPPREPNINGAR);
    }

    @Test
    void kanKungenTas1() {
        var brade = new Brade(TOMT);
        brade.setPjas("a1", 'K');
        brade.setPjas("a5", 't');
        brade.setPjas("g8", 'k');
        brade.print();
        var mojligaDrag = brade.getMojligaDrag();
        System.out.println("mojliga drag: " + mojligaDrag);
        assertThat(mojligaDrag).hasSize(2);
        assertThat(brade.isISchack()).isTrue();
        Drag d = new Drag(brade, "a1", "a2", DragTyp.FLYTT);
    }
    //Be2-e3, Sb8-c6, Dd1-f3, Sc6-e5, Df3-g3, Bd7-d6, Ke1-d1, Lc8-g4, Dg3-f3, Lg4-f3xD, Bg2-f3xL, Sg8-f6, Sb1-c3,
    // Ba7-a6, Lf1-b5, Ba6-b5xL, Sc3-b5xB, Bg7-g6, Sb5-c3, Lf8-h6, Bb2-b3, Bb7-b6, Sc3-a4, Bc7-c6, Bf3-f4, Lh6-f4xB,
    // Be3-f4xL, Se5-d7, Bb3-b4, Ta8-a4xS, Kd1-e2, Ta4-b4xB, Bd2-d3, Be7-e5, Ba2-a4, Be5-f4xB, Bc2-c3, Dd8-e7
    //"Det går inte att flytta till b4."
    //Jo, det gör det!
    @Test
    void konstigtFall() {
        var brade = new Brade(TOMT);
        brade.setPjas("e8", 'k');
        brade.setPjas("e7", 'd');
        brade.setPjas("b4", 't');
        brade.setPjas("d1", 'K');
        brade.setPjas("c3", 'B');
        //brade = brade.klonaOchFlippa();
        brade.print();
        var alladrag = brade.getMojligaDrag();
        System.out.println(alladrag);
    }
}
