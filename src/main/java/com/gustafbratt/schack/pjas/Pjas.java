package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Pjas {
    public static char CONST_BONDE = 'B';
    public static char CONST_KUNG = 'K';
    public static char CONST_DAM = 'D';

    Brade brade;
    Position position;

    final List<Drag> mojligaDrag = new ArrayList<>();

    public abstract List<Drag> mojligaDrag();


    boolean ledigEllerMotstandare(Position position) {
        char pjas = brade.pjasPa(position);
        return pjas == '.' || Character.isLowerCase(pjas);
    }

    void skapaDragLinjeFramat() {
        Position pekare = position;
        try {
            do {
                skapaDragOmLedigEllerMotstandare(pekare);
                pekare = pekare.framfor();
            } while (brade.pjasPa(pekare) == '.');
        } catch (UtanforBradetException ignored) {
        }
    }

    void skapaDragLinjeBakat() {
        Position pekare = position;
        try {
            do {
                skapaDragOmLedigEllerMotstandare(pekare);
                pekare = pekare.bakom();
            } while (brade.pjasPa(pekare) == '.');
        } catch (UtanforBradetException ignored) {
        }
    }

    void skapaDragLinje(Flytt flytt) {
        Position pekare = position;
        try {
            do {
                pekare = flytt.flytta(pekare);
                skapaDragOmLedigEllerMotstandare(pekare);
            } while (brade.pjasPa(pekare) == '.');
        } catch (UtanforBradetException ignored) {
        }
    }


    void skapaDragOmLedigEllerMotstandare(Position positionTill) {
        if (ledigEllerMotstandare(positionTill)) {
            Drag drag = new Drag(brade, this.position, positionTill);
            mojligaDrag.add(drag);
        }
    }


}

