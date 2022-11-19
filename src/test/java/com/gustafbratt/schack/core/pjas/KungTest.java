package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.Position;
import com.gustafbratt.schack.core.UtanforBradetException;
import com.gustafbratt.schack.core.pjas.Kung;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustafbratt.schack.core.pjas.Pjas.CONST_KUNG;
import static org.assertj.core.api.Assertions.assertThat;

class KungTest {

    @Test
    void start() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.print();
        Kung kung = new Kung(brade, new Position("e8"));
        assertThat(kung.getMojligaDrag()).hasSize(0);
    }

    @Test
    void mitten() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(new Position("d4"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("d4"));
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }


    @Test
    void vidMotstandare() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(new Position("d3"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("d3"));
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }

    @Test
    void vidEgna() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(new Position("d6"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("d6"));
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(5);
    }

    @Test
    void a1Hornet() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(new Position("a1"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("a1"));
        List<Drag> actual = kung.getMojligaDrag();
        //actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(3);
    }

    @Test
    void h8Hornet() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(new Position("h8"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("h8"));
        List<Drag> actual = kung.getMojligaDrag();
//        actual.forEach(b ->
//                b.getKommande().print()
//        );
        assertThat(actual).hasSize(3);
    }

    @Test
    void mangaDrag() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(new Position("d7"), 'B');
        brade.setPjas(new Position("b2"), 'b');
        brade.setPjas(new Position("e1"), 'k');
        brade.print();
        Drag d1 = new Drag(brade, new Position("d7"), new Position("d5"));
        brade = brade.utforDrag(d1);
        brade.print();
        Kung kung = new Kung(brade, new Position("e1"));
        List<Drag> mojligaDrag = kung.getMojligaDrag();
        System.out.println(mojligaDrag);
        assertThat(mojligaDrag).hasSize(5);
        assertThat(mojligaDrag.stream().map(Drag::toString)).contains("Ke1-e2", "Ke1-f1", "Ke1-d1", "Ke1-f2", "Ke1-d2");
    }

}