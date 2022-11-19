package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Position;
import com.gustafbratt.schack.core.Riktning;
import com.gustafbratt.schack.core.UtanforBradetException;

public class Springare extends Pjas {
    public Springare(Brade brade, Position position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_SPRINGARE) {
            throw new IllegalStateException("Inte en kung på position " + position + ". Det är en " + pjasKod);
        }
        this.beraknaMojligaDrag();
    }

    private void beraknaMojligaDrag() {
        for (Riktning riktning : Riktning.SPRINGARE) {
            try {
                skapaDragOmLedigEllerMotstandare(riktning.flytta(position));
            } catch (UtanforBradetException ignored) {
            }
        }
    }

    @Override
    public char getChar() {
        return CONST_SPRINGARE;
    }
}
