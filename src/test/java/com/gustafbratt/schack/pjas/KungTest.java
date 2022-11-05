package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;
import com.gustafbratt.schack.UtanforBradetException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static com.gustafbratt.schack.pjas.Pjas.CONST_KUNG;

class KungTest {

    @Test
    void start() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.print();
        Kung kung = new Kung(brade, new Position("e8"));
        assertThat(kung.mojligaDrag()).hasSize(0);
    }

    @Test
    void mitten() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(new Position("d4"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("d4"));
        List<Drag> actual = kung.mojligaDrag();
        actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }


    @Test
    void vidMotstandare() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(new Position("d3"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("d3"));
        List<Drag> actual = kung.mojligaDrag();
        actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }

    @Test
    void vidEgna() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(new Position("d6"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("d6"));
        List<Drag> actual = kung.mojligaDrag();
        actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(5);
    }

    @Test
    void a1Hornet() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(new Position("a1"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("a1"));
        List<Drag> actual = kung.mojligaDrag();
        actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(3);
    }

    @Test
    void h8Hornet() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(new Position("h8"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, new Position("h8"));
        List<Drag> actual = kung.mojligaDrag();
        actual.forEach(b ->
                b.getKommande().print()
        );
        assertThat(actual).hasSize(3);
    }

}