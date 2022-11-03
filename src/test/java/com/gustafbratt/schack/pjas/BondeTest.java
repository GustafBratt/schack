package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;
import com.gustafbratt.schack.UtanforBradetException;
import org.junit.jupiter.api.Test;

import java.util.List;

class BondeTest {
    @Test
    void franStart() throws UtanforBradetException {
        Brade brade = new Brade();
        brade.startPosition();
        brade.print();
        Bonde bonde = new Bonde(brade, new Position("D7"));
        List<Drag> mojligaDrag = bonde.mojligaDrag();
        System.out.println(mojligaDrag);
        for(Drag drag : mojligaDrag) {
            drag.getKommande().print();
        }
    }
}