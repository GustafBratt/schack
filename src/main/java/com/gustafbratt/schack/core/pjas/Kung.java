package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.*;

import static com.gustafbratt.schack.core.Farg.SVART;
import static com.gustafbratt.schack.core.Farg.VIT;

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
        for (Riktning riktning : Riktning.ALLARIKTNINGAR) {
            try {
                skapaDragOmLedigEllerMotstandare(riktning.flytta(position));
            } catch (UtanforBradetException ignored) {
            }
        }
    }

    public void beraknaRokader() {
        //TODO kungen får inte stå i schack; man kan alltså inte undkomma en schack genom att rockera
        //varken kungen eller det torn som används för rockaden får ha flyttats tidigare under partiet
        //inget fält mellan kungen och tornet får vara besatt av en annan pjäs; det får alltså inte stå någon annan pjäs emellan dem, oavsett färg
        //inget av de fält som kungen rör sig över, eller hamnar på, får vara hotat av någon av motståndarens pjäser; man kan alltså inte flytta in i schack
        if (brade.getAktuellFarg() == VIT && !brade.isVitKungFlyttad()) {
            if (kanGoraRockad(RockadTyp.VIT_LANG, brade.isTornA1Flyttad()))
                mojligaDrag.add(new Drag(brade, RockadTyp.VIT_LANG));
            if (kanGoraRockad(RockadTyp.VIT_KORT, brade.isTornH1Flyttad()))
                mojligaDrag.add(new Drag(brade, RockadTyp.VIT_KORT));
        }

        if (brade.getAktuellFarg() == SVART && !brade.isSvartKungFlyttad()) {
            if (kanGoraRockad(RockadTyp.SVART_LANG, brade.isTornA8Flyttad()))
                mojligaDrag.add(new Drag(brade, RockadTyp.SVART_LANG));
            if (kanGoraRockad(RockadTyp.SVART_KORT, brade.isTornH8Flyttad())) {
                mojligaDrag.add(new Drag(brade, RockadTyp.SVART_KORT));
            }
        }
    }


    private boolean kanGoraRockad(RockadTyp rockadTyp, boolean tornFlyttad) {
        boolean pjasMellan = false;
        for (String pos : rockadTyp.getMellanPositioner()) {
            if (brade.charPa(pos) != '.') {
                pjasMellan = true;
            }
        }
        return !tornFlyttad && !pjasMellan;
    }


    @Override
    public char getChar() {
        return CONST_KUNG;
    }
}