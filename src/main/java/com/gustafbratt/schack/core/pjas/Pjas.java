package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Pjas {
    public static final char CONST_BONDE = 'B';
    public static final char CONST_KUNG = 'K';
    public static final char CONST_DAM = 'D';
    public static final char CONST_TORN = 'T';
    public static final char CONST_SPRINGARE = 'S';
    public static final char CONST_LOPARE = 'L';

    final Brade brade;
    final String position;

    final List<Drag> mojligaDrag = new ArrayList<>();

    public Pjas(Brade brade, String position) {
        this.brade = brade;
        this.position = position;
    }

    boolean ledigEllerMotstandare(String position) {
        char pjas = brade.charPa(position);
        return pjas == '.' || Character.isLowerCase(pjas);
    }

    void skapaLinjeMedDrag(Riktning riktning) {
        String pekare = position;
        try {
            do {
                pekare = riktning.flytta(pekare);
                PositionUtils.validera(pekare);
                skapaDragOmLedigEllerMotstandare(pekare);
            } while (brade.charPa(pekare) == '.');
        } catch (UtanforBradetException ignored) {
        }
    }

    void skapaDragOmLedigEllerMotstandare(String positionTill) throws UtanforBradetException {
        PositionUtils.validera(positionTill);
        if (ledigEllerMotstandare(positionTill)) {
            Drag drag = new Drag(brade, this.position, positionTill);
            mojligaDrag.add(drag);
        }
    }

    final public List<Drag> getMojligaDrag() {
        return mojligaDrag;
    }


    public abstract char getChar();

    @Override
    public String toString() {
        return "Pjas{" +
                "aktuellFarg=" + brade.getAktuellFarg() +
                ", position=" + position +
                ", mojligaDrag=" + mojligaDrag +
                '}';
    }
}

