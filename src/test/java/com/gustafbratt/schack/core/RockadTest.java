package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.Pjas;
import org.junit.jupiter.api.Test;

import static com.gustafbratt.schack.core.Brade.BRADE_INIT_TYP.TIME_FOR_ROCKAD;
import static org.assertj.core.api.Assertions.assertThat;

public class RockadTest {
    @Test
    public void start() {
        var brade = new Brade(TIME_FOR_ROCKAD);
        brade.print();
        Pjas kung = brade.getPjas("e1").get();
        var dragVit = kung.getMojligaDrag();
        System.out.println(dragVit);
        assertThat(dragVit.stream().map(Drag::toString)).containsExactlyInAnyOrder("Ke1-f1", "Ke1-g1R");
        var b2 = dragVit.get(1).utfor(); //Vits drag. Svart aktiv.
        b2.print();
        assertThat(b2.charPa("h1")).isEqualTo('.');
        assertThat(b2.charPa("g1")).isEqualTo('k');
        assertThat(b2.charPa("f1")).isEqualTo('t');
        assertThat(b2.charPa("e1")).isEqualTo('.');

        var dragSvart = b2.getPjas("e8").get().getMojligaDrag();
        System.out.println(dragSvart);
        assertThat(dragSvart.stream().map(Drag::toString)).containsExactlyInAnyOrder("Ke8-f8", "Ke8-g8R");
        Brade klar = dragSvart.get(1).utfor();
        klar.print();
        assertThat(klar.isVitKungFlyttad()).isTrue();
        assertThat(klar.isSvartKungFlyttad()).isTrue();
        assertThat(klar.isTornA1Flyttad()).isFalse();
    }

    @Test
    public void ivagen() {
        Brade brade = new Brade(TIME_FOR_ROCKAD);
        brade.print();
        Pjas kung = brade.getPjas("e1").get();
        var dragVit = kung.getMojligaDrag();
        System.out.println(dragVit);
        assertThat(dragVit.stream().map(Drag::toString)).contains("Ke1-g1R"); //Health check
        brade.setPjas("f1", 'S');
        brade.print();
        kung = brade.getPjas("e1").get();
        dragVit = kung.getMojligaDrag();
        System.out.println(dragVit);
    }

    @Test
    public void vitLang() {
        String mojligaDragString;
        mojligaDragString = bradeMedPjasPaVit("c5");
        System.out.println(mojligaDragString);
        assertThat(mojligaDragString).contains("Ke1-c1R");

        mojligaDragString = bradeMedPjasPaVit("b1");
        System.out.println(mojligaDragString);
        assertThat(mojligaDragString).doesNotContain("Ke1-c1R");

        mojligaDragString = bradeMedPjasPaVit("c1");
        System.out.println(mojligaDragString);
        assertThat(mojligaDragString).doesNotContain("Ke1-c1R");

        mojligaDragString = bradeMedPjasPaVit("d1");
        System.out.println(mojligaDragString);
        assertThat(mojligaDragString).doesNotContain("Ke1-c1R");
    }

    private String bradeMedPjasPaVit(String pos) {
        Brade brade = new Brade(TIME_FOR_ROCKAD);
        brade.setPjas("d1", '.');
        brade.setPjas("d8", '.');
        brade.setPjas(pos, 'S');
        brade.print();
        return brade.getPjas("e1").get().getMojligaDrag().toString();
    }

    @Test
    public void svartLang() {
        String mojligaDrag;
        mojligaDrag = bradeMedPjasPaSvart("b3");
        System.out.println(mojligaDrag);
        assertThat(mojligaDrag).contains("Ke8-c8R");

        mojligaDrag = bradeMedPjasPaSvart("d8");
        System.out.println(mojligaDrag);
        assertThat(mojligaDrag).doesNotContain("Ke8-c8R");
    }

    private String bradeMedPjasPaSvart(String pos) {
        Brade brade = new Brade(TIME_FOR_ROCKAD);
        brade.setPjas("d1", '.');
        brade.setPjas("d8", '.');
        brade.setPjas(pos, 'S');
        brade = new Drag(brade, "g2", "g3").utfor();
        brade.print();
        return brade.getPjas("e8").get().getMojligaDrag().toString();
    }

}
