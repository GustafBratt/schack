package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.Position;
import com.gustafbratt.schack.core.Riktning;

import java.util.List;

public class Dam extends Pjas {

    public Dam(Brade brade, Position position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_DAM) {
            throw new IllegalStateException("Inte en dam på position " + position + ". Det är en " + pjasKod);
        }
        beraknaMojligaDrag();
    }

    private void beraknaMojligaDrag() {
        for(Riktning riktning : Riktning.ALLARIKTNINGAR) {
            skapaLinjeMedDrag(riktning);
        }
    }

    @Override
    public List<Drag> getMojligaDrag() {
        return mojligaDrag;
    }

    @Override
    public char getChar() {
        return CONST_DAM;
    }


}
