package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.UtanforBradetException;
import com.gustafbratt.schack.core.pjas.Bonde;
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

    @Test
    void hogerKantSvart() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.print();
        var drag = new Drag(brade, brade.position("a7"), brade.position("a6"));
        brade = drag.getKommande();
        brade.print();
        Bonde bonde = new Bonde(brade, brade.position("g2"));
        System.out.println(bonde.getMojligaDrag());
        assertThat(bonde.getMojligaDrag()).hasSize(2);
        assertThat(bonde.getMojligaDrag().stream().map(Drag::toString)).contains("Bg2-g3", "Bg2-g4");
    }


    private List<Drag> skapaBrade(String bondePos, String extraPjasPos, char extraPjasTyp) throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.setPjas(brade.position(extraPjasPos), extraPjasTyp);
        brade.print();
        Bonde bonde = new Bonde(brade, brade.position(bondePos));
        List<Drag> mojligaDrag = bonde.getMojligaDrag();
        System.out.println("Möjliga drag:" + mojligaDrag);
        for(Drag drag : mojligaDrag) {
            drag.getKommande().print();
        }
        return mojligaDrag;
    }

}