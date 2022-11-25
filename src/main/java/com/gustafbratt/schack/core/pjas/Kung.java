package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.*;

public class Kung extends Pjas {

    public Kung(Brade brade, String position) {
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
            } catch (UtanforBradetException ignored) {
            }
        }
        //Kungen får inte vara i schack TODO
        //Kungen får inte ha fylltat på sig
        if(!brade.aktuellKungHarFlyttatPaSig()) {
            rockada();
            rockadh();
        }
    }

    private void rockadh() {
    }

    private void rockada() {

    }

    @Override
    public char getChar() {
        return CONST_KUNG;
    }
}