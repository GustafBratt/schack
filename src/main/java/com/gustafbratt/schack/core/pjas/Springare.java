package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Riktning;
import com.gustafbratt.schack.core.UtanforBradetException;

public class Springare extends Pjas {
    public Springare(Brade brade, String position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_SPRINGARE) {
            throw new IllegalStateException("Inte en springare på position " + position + ". Det är en " + pjasKod);
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

    static int[][] varde = new int[8][];

    static {
        varde[0] = new int[]{40, 40, 40, 40, 40, 40, 40, 40};
        varde[1] = new int[]{40, 45, 40, 40, 40, 45, 45, 40};
        varde[2] = new int[]{40, 45, 45, 45, 45, 45, 45, 40};
        varde[3] = new int[]{40, 40, 45, 50, 50, 45, 40, 40};
        varde[4] = new int[]{40, 40, 45, 50, 50, 45, 40, 40};
        varde[5] = new int[]{40, 45, 45, 45, 45, 45, 45, 40};
        varde[6] = new int[]{40, 45, 45, 40, 40, 45, 45, 40};
        varde[7] = new int[]{30, 30, 30, 30, 30, 30, 30, 30};
    }

    public static int getVarde(int rad, int kolumn) {
        return varde[rad][kolumn];
    }


}
