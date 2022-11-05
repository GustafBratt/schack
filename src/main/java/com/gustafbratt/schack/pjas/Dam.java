package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;

import java.util.List;

public class Dam extends Pjas {

    public Dam(Brade brade, Position position) {
        char pjasKod = brade.pjasPa(position);
        if (pjasKod != CONST_DAM) {
            throw new IllegalStateException("Inte en dam på position " + position + ". Det är en " + pjasKod);
        }
        this.brade = brade;
        this.position = position;
    }


    @Override
    public List<Drag> mojligaDrag() {
        skapaDragLinjeFramat(position);
        skapaDragLinjeBakat(position);
        return mojligaDrag;
    }

}
