package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.*;

import java.util.List;

public class Kung extends Pjas {

    public Kung(Brade brade, Position position) {
        char pjasKod = brade.pjasPa(position);
        if (pjasKod != CONST_KUNG) {
            throw new IllegalStateException("Inte en kung på position " + position + ". Det är en " + pjasKod);
        }
        this.brade = brade;
        this.position = position;
    }

    @Override
    public List<Drag> mojligaDrag() {
        for(Flytt flytt : Flytt.ALLARIKTNINGAR) {
            try {
                skapaDragOmLedigEllerMotstandare(flytt.flytta(position));
            } catch (UtanforBradetException ignored) { }
        }
        return mojligaDrag;
    }
}