package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Riktning;

public class Lopare extends Pjas {

    public Lopare(Brade brade, String position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_LOPARE) {
            throw new IllegalStateException("Inte en kung på position " + position + ". Det är en " + pjasKod);
        }
        this.beraknaMojligaDrag();
    }

    private void beraknaMojligaDrag() {
        for(Riktning riktning : Riktning.DIAGONAL) {
            skapaLinjeMedDrag(riktning);
        }
    }

    @Override
    public char getChar() {
        return 'l';
    }
}
