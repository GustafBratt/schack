package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;
import com.gustafbratt.schack.UtanforBradetException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustafbratt.schack.pjas.Pjas.CONST_DAM;
import static org.assertj.core.api.Assertions.assertThat;

class DamTest {

    @Test
    void start() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.print();
        Dam dam = new Dam(brade, new Position("d8"));
        List<Drag> actual = dam.mojligaDrag();
        System.out.println(actual);
        assertThat(actual).hasSize(0);
    }

    @Test
    void helaBradet() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(new Position("d8"), CONST_DAM);
        brade.print();
        Dam dam = new Dam(brade, new Position("d8"));
        List<Drag> actual = dam.mojligaDrag();
        System.out.println(actual);
        assertThat(actual).hasSize(21);
    }

    @Test
    void mitten() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.TOMT);
        brade.setPjas(new Position("d4"), CONST_DAM);
        brade.print();
        Dam dam = new Dam(brade, new Position("d4"));
        List<Drag> actual = dam.mojligaDrag();
        System.out.println(actual);
        assertThat(actual).hasSize(27); //TODO 27
    }

    @Test
    void mittenMedPjaser() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(new Position("d4"), CONST_DAM);
        brade.print();
        Dam dam = new Dam(brade, new Position("d4"));
        List<Drag> actual = dam.mojligaDrag();
        System.out.println(actual);
        actual.forEach(d -> d.getKommande().print());
        assertThat(actual).hasSize(19);
    }

    @Test
    void motstandareTorn() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(new Position("h1"), CONST_DAM);
        brade.print();
        Dam dam = new Dam(brade, new Position("h1"));
        List<Drag> actual = dam.mojligaDrag();
        System.out.println(actual);
        actual.forEach(d -> d.getKommande().print());
        assertThat(actual).hasSize(3);
    }


}