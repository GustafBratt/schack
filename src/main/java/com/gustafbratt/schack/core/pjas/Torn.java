package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.Position;
import com.gustafbratt.schack.core.Riktning;

import java.util.List;

public class Torn extends Pjas {
    public Torn(Brade brade, Position position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_TORN) {
            throw new IllegalStateException("Inte en kung på position " + position + ". Det är en " + pjasKod);
        }
        this.beraknaMojligaDrag();
    }

    private void beraknaMojligaDrag() {
        for(Riktning riktning : Riktning.RAKA) {
            skapaLinjeMedDrag(riktning);
        }
    }

    @Override
    public char getChar() {
        return CONST_TORN;
    }
}
