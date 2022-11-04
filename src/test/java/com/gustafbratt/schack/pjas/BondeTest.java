package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;
import com.gustafbratt.schack.UtanforBradetException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BondeTest {
    @Test
    void hogerKant() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("h7", "g6", 'l');
        assertThat(mojligaDrag).hasSize(3);
    }

    @Test
    void lopareFramfor() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("g7", "g6", 'l');
        assertThat(mojligaDrag).hasSize(0);
    }

    @Test
    void lopareTvaStegFramfor() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("g7", "g5", 'l');
        assertThat(mojligaDrag).hasSize(1);
    }

    @Test
    void vidMotstandarLinje() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("d3", "d3", 'B');
        assertThat(mojligaDrag).hasSize(2);
    }

    @Test
    void sammaFramfor() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("c7", "c6", 'D');
        assertThat(mojligaDrag).hasSize(0);
    }

    private List<Drag> skapaBrade(String bondePos, String extraPjasPos, char extraPjasTyp) throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(new Position(extraPjasPos), extraPjasTyp);
        brade.print();
        Bonde bonde = new Bonde(brade, new Position(bondePos));
        List<Drag> mojligaDrag = bonde.mojligaDrag();
        System.out.println("Möjliga drag:" + mojligaDrag);
        for(Drag drag : mojligaDrag) {
            drag.getKommande().print();
        }
        return mojligaDrag;
    }

}