package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.StartBraden;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustafbratt.schack.core.pjas.Pjas.CONST_KUNG;
import static org.assertj.core.api.Assertions.assertThat;

class KungTest {

    @Test
    void start() {
        Brade brade = new Brade(StartBraden.START);
        brade.print();
        Kung kung = new Kung(brade, "e1");
        assertThat(kung.getMojligaDrag()).hasSize(0);
    }

    @Test
    void mitten() {
        Brade brade = new Brade(StartBraden.START);
        brade.setPjas("d4", CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, "d4");
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }


    @Test
    void vidMotstandare() {
        Brade brade = new Brade(StartBraden.START);
        brade.setPjas("d6", CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, "d6");
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }

    @Test
    void vidEgna() {
        Brade brade = new Brade(StartBraden.START);
        brade.setPjas("d3", CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, "d3");
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(5);
    }

    @Test
    void a1Hornet() {
        Brade brade = new Brade(StartBraden.TOMT);
        brade.setPjas("a1", CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, "a1");
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(3);
    }

    @Test
    void h8Hornet() {
        Brade brade = new Brade(StartBraden.TOMT);
        brade.setPjas("h8", CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, "h8");
        List<Drag> actual = kung.getMojligaDrag();
//        actual.forEach(b ->
//                b.getKommande().print()
//        );
        assertThat(actual).hasSize(3);
    }

    @Test
    void mangaDrag() throws OgiltigtDragException {
        Brade brade = new Brade(StartBraden.TOMT);
        brade.setPjas("d7", 'B');
        brade.setPjas("b2", 'b');
        brade.setPjas("e1", 'k');
        brade.print();
        Drag d1 = brade.hittaDrag( "d7", "d8");
        brade = new Brade(d1);
        brade.print();
        Kung kung = new Kung(brade, "e1");
        List<Drag> mojligaDrag = kung.getMojligaDrag();
        System.out.println(mojligaDrag);
        assertThat(mojligaDrag).hasSize(5);
        assertThat(mojligaDrag.stream().map(Drag::toString)).contains("Ke1-e2", "Ke1-f1", "Ke1-d1", "Ke1-f2", "Ke1-d2");
    }

    @Test
    public void wtfTest() throws OgiltigtDragException {
        Brade b = new Brade(StartBraden.START);
        b = b.hittaDrag( "a2", "a3").utfor(); //Vit
        b = b.hittaDrag( "h7", "h6").utfor(); //Svart
        b = b.hittaDrag( "b2", "b3").utfor(); //Vit
        b = b.hittaDrag( "g8", "f6").utfor(); //Svart
        b = b.hittaDrag( "c2", "c3").utfor(); //Vit
        b = b.hittaDrag( "e7", "e5").utfor(); //Svart
        b = b.hittaDrag( "d2", "d3").utfor(); //Vit
        b = b.hittaDrag( "f8", "d6").utfor(); //Svart
        b = b.hittaDrag( "e2", "e3").utfor(); //Vit
        b = b.hittaDrag( "h8", "h7").utfor();
        b = b.hittaDrag( "f2", "f3").utfor();
        b.print();
        var allaDrag = b.beraknaMojligaDrag();
        assertThat(allaDrag.stream().map(Drag::toString)).doesNotContain("Ke8-g8R");
    }

    @Test
    public void wtfTest2() throws OgiltigtDragException {
        Brade b = new Brade(StartBraden.START);
        b = b.hittaDrag( "a2", "a3").utfor(); //Vit
        b = b.hittaDrag( "h7", "h6").utfor(); //Svart
        b = b.hittaDrag( "b2", "b3").utfor(); //Vit
        b = b.hittaDrag( "g8", "f6").utfor(); //Svart
        b = b.hittaDrag( "c2", "c3").utfor(); //Vit
        b = b.hittaDrag( "e7", "e5").utfor(); //Svart
        b = b.hittaDrag( "d2", "d3").utfor(); //Vit
        b = b.hittaDrag( "f8", "d6").utfor(); //Svart
        b = b.hittaDrag( "e2", "e3").utfor(); //Vit
        assertThat(b.beraknaMojligaDrag().stream().map(Drag::toString)).contains("Ke8-g8R");
        b = b.hittaDrag( "e8", "g8").utfor();
        assertThat(b.charPa("f8")).isEqualTo('t');
    }


}