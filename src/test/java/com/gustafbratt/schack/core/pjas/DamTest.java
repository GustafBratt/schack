package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.StartBraden;
import com.gustafbratt.schack.core.UtanforBradetException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustafbratt.schack.core.pjas.Pjas.CONST_DAM;
import static org.assertj.core.api.Assertions.assertThat;

class DamTest {

    @Test
    void start() throws UtanforBradetException {
        Brade brade = new Brade(StartBraden.START);
        brade.print();
        Dam dam = new Dam(brade, "d1");
        List<Drag> actual = dam.getMojligaDrag();
        System.out.println(actual);
        assertThat(actual).hasSize(0);
    }

    @Test
    void helaBradet() {
        Brade brade = new Brade(StartBraden.TOMT);
        brade.setPjas("d1", CONST_DAM);
        brade.print();
        Dam dam = new Dam(brade, "d1");
        List<Drag> actual = dam.getMojligaDrag();
        System.out.println(actual);
        assertThat(actual).hasSize(21);
    }

    @Test
    void mitten() {
        Brade brade = new Brade(StartBraden.TOMT);
        brade.setPjas("d4", CONST_DAM);
        brade.print();
        Dam dam = new Dam(brade, "d4");
        List<Drag> actual = dam.getMojligaDrag();
        System.out.println(actual);
        assertThat(actual).hasSize(27); //TODO 27
    }

    @Test
    void mittenMedPjaser() {
        Brade brade = new Brade(StartBraden.START);
        brade.setPjas("d4", CONST_DAM);
        brade.print();
        Dam dam = new Dam(brade, "d4");
        List<Drag> actual = dam.getMojligaDrag();
        System.out.println(actual);
        //actual.forEach(d -> d.getKommande().print());
        assertThat(actual).hasSize(19);
    }

    @Test
    void motstandareTorn() {
        Brade brade = new Brade(StartBraden.START);
        brade.setPjas("h8", CONST_DAM);
        brade.print();
        Dam dam = new Dam(brade, "h8");
        List<Drag> actual = dam.getMojligaDrag();
        System.out.println(actual);
        assertThat(actual).hasSize(3);
    }


}