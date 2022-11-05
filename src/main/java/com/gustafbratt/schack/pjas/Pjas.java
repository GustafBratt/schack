package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;
import com.gustafbratt.schack.UtanforBradetException;

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

    void skapaDragLinjeFramat(Position pekare) {
        try {
            var positionTill = pekare.framfor();
            skapaDragOmLedigEllerMotstandare(positionTill);
            if (brade.pjasPa(positionTill) == '.') {
                skapaDragLinjeFramat(positionTill);
            }
        } catch (UtanforBradetException ignored) {
        }
    }

    void skapaDragLinjeBakat(Position pekare) {
        try {
            var positionTill = pekare.bakom();
            skapaDragOmLedigEllerMotstandare(positionTill);
            if (brade.pjasPa(positionTill) == '.') {
                skapaDragLinjeBakat(positionTill);
            }
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
