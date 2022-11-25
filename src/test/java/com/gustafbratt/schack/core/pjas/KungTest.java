package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustafbratt.schack.core.pjas.Pjas.CONST_KUNG;
import static org.assertj.core.api.Assertions.assertThat;

class KungTest {

    @Test
    void start() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.print();
        Kung kung = new Kung(brade, "e1");
        assertThat(kung.getMojligaDrag()).hasSize(0);
    }

    @Test
    void mitten() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas("d4", CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, "d4");
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }


    @Test
    void vidMotstandare() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas("d6", CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, "d6");
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }

    @Test
    void vidEgna() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas("d3", CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, "d3");
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(5);
    }

    @Test
    void a1Hornet() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas("a1", CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, "a1");
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(3);
    }

    @Test
    void h8Hornet() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
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
    void mangaDrag() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas("d7", 'B');
        brade.setPjas("b2", 'b');
        brade.setPjas("e1", 'k');
        brade.print();
        Drag d1 = new Drag(brade, "d7", "d5");
        brade = brade.utforDrag(d1);
        brade.print();
        Kung kung = new Kung(brade, "e1");
        List<Drag> mojligaDrag = kung.getMojligaDrag();
        System.out.println(mojligaDrag);
        assertThat(mojligaDrag).hasSize(5);
        assertThat(mojligaDrag.stream().map(Drag::toString)).contains("Ke1-e2", "Ke1-f1", "Ke1-d1", "Ke1-f2", "Ke1-d2");
    }

}