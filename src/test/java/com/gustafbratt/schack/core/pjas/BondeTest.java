package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.UtanforBradetException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BondeTest {
    @Test
    void hogerKant() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("h2", "g3", 'l');
        assertThat(mojligaDrag).hasSize(3);
    }

    @Test
    void lopareFramfor() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("g2", "g3", 'l');
        assertThat(mojligaDrag).hasSize(0);
    }

    @Test
    void lopareTvaStegFramfor() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("g2", "g4", 'l');
        assertThat(mojligaDrag).hasSize(1);
    }

    @Test
    void vidMotstandarLinje() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("d6", "d6", 'B');
        assertThat(mojligaDrag).hasSize(2);
    }

    @Test
    void sammaFramfor() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("c2", "c3", 'D');
        assertThat(mojligaDrag).hasSize(0);
    }

    @Test
    void hogerKantSvart() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.print();
        var drag = new Drag(brade, "a2", "a3");
        brade = brade.utforDrag(drag);
        brade.print();
        Bonde bonde = new Bonde(brade, "g7");
        List<Drag> mojligaDrag = bonde.getMojligaDrag();
        System.out.println(mojligaDrag);
        assertThat(mojligaDrag).hasSize(2);
        assertThat(mojligaDrag.stream().map(Drag::toString)).contains("Bg7-g6", "Bg7-g5");
    }


    private List<Drag> skapaBrade(String bondePos, String extraPjasPos, char extraPjasTyp) throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(extraPjasPos, extraPjasTyp);
        brade.print();
        Bonde bonde = new Bonde(brade, bondePos);
        List<Drag> mojligaDrag = bonde.getMojligaDrag();
        System.out.println("Möjliga drag:" + mojligaDrag);
        return mojligaDrag;
    }

}