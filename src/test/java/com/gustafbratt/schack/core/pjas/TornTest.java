package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.Position;
import com.gustafbratt.schack.core.UtanforBradetException;
import com.gustafbratt.schack.core.pjas.Pjas;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TornTest {
    @Test
    public void test1() throws UtanforBradetException {
        Brade b = new Brade(Brade.BRADE_INIT_TYP.BONDER_DAM_KUNG_TORN);
        b.print();
        Pjas torn1 = b.getPjas(new Position("a8")).get();
        var drag1 = torn1.getMojligaDrag();
        System.out.println(drag1);
        assertThat(drag1.stream().map(Drag::toString)).containsExactly("Ta8-b8", "Ta8-c8");
        Pjas torn2 = b.getPjas(new Position("h8")).get();
        var drag2 = torn2.getMojligaDrag();
        System.out.println(drag2);
        assertThat(drag2.stream().map(Drag::toString)).containsExactly("Th8-g8", "Th8-f8");
    }
}
