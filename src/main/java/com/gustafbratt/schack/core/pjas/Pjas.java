package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.*;

import java.util.ArrayList;
import java.util.List;

public abstract class Pjas {
    public static final char CONST_BONDE = 'B';
    public static final char CONST_KUNG = 'K';
    public static final char CONST_DAM = 'D';

    final Brade brade;
    final Position position;

    final List<Drag> mojligaDrag = new ArrayList<>();

    public Pjas(Brade brade, Position position) {
        this.brade = brade;
        this.position = position;
    }

    public abstract List<Drag> getMojligaDrag();

    boolean ledigEllerMotstandare(Position position) {
        char pjas = brade.charPa(position);
        return pjas == '.' || Character.isLowerCase(pjas);
    }

    void skapaLinjeMedDrag(Riktning riktning) {
        Position pekare = position;
        try {
            do {
                pekare = riktning.flytta(pekare);
                skapaDragOmLedigEllerMotstandare(pekare);
            } while (brade.charPa(pekare) == '.');
        } catch (UtanforBradetException ignored) {
        }
    }

    void skapaDragOmLedigEllerMotstandare(Position positionTill) {
        if (ledigEllerMotstandare(positionTill)) {
            Drag drag = new Drag(brade, this.position, positionTill);
            mojligaDrag.add(drag);
        }
    }

    public abstract char getChar();
}

