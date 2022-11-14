package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
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
        Kung kung = new Kung(brade, brade.position("e8"));
        assertThat(kung.getMojligaDrag()).hasSize(0);
    }

    @Test
    void mitten() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(brade.position("d4"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, brade.position("d4"));
        List<Drag> actual = kung.getMojligaDrag();
        actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }


    @Test
    void vidMotstandare() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(brade.position("d3"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, brade.position("d3"));
        List<Drag> actual = kung.getMojligaDrag();
        actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(8);
    }

    @Test
    void vidEgna() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(brade.position("d6"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, brade.position("d6"));
        List<Drag> actual = kung.getMojligaDrag();
        actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(5);
    }

    @Test
    void a1Hornet() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(brade.position("a1"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, brade.position("a1"));
        List<Drag> actual = kung.getMojligaDrag();
        actual.forEach(b -> b.getKommande().print());
        assertThat(actual).hasSize(3);
    }

    @Test
    void h8Hornet() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(brade.position("h8"), CONST_KUNG);
        brade.print();
        Kung kung = new Kung(brade, brade.position("h8"));
        List<Drag> actual = kung.getMojligaDrag();
        actual.forEach(b ->
                b.getKommande().print()
        );
        assertThat(actual).hasSize(3);
    }

    @Test
    void mangaDrag() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(brade.position("d7"), 'B');
        brade.setPjas(brade.position("b2"), 'b');
        brade.setPjas(brade.position("e1"), 'k');
        brade.print();
        Drag d1 = new Drag(brade, brade.position("d7"), brade.position("d5"));
        brade = d1.getKommande();
        brade.print();
        Kung kung = new Kung(brade, brade.position("e1"));
        System.out.println(kung.getMojligaDrag());
    }

}