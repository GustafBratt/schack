package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.*;

import java.util.List;

public class Kung extends Pjas {

    public Kung(Brade brade, Position position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_KUNG) {
            throw new IllegalStateException("Inte en kung på position " + position + ". Det är en " + pjasKod);
        }
        this.beraknaMojligaDrag();
    }

    private void beraknaMojligaDrag() {
        for(Riktning riktning : Riktning.ALLARIKTNINGAR) {
            try {
                skapaDragOmLedigEllerMotstandare(riktning.flytta(position));
            } catch (UtanforBradetException ignored) { }
        }
    }

    @Override
    public List<Drag> getMojligaDrag() {
        return mojligaDrag;
    }

    @Override
    public char getChar() {
        return CONST_KUNG;
    }
}