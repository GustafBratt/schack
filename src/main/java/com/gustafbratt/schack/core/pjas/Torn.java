package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Riktning;

public class Torn extends Pjas {
    public Torn(Brade brade, String position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_TORN) {
            throw new IllegalStateException("Inte ett torn på position " + position + ". Det är en " + pjasKod);
        }
        this.beraknaMojligaDrag();
    }

    private void beraknaMojligaDrag() {
        for(Riktning riktning : Riktning.RAKA) {
            skapaLinjeMedDrag(riktning);
        }
    }

    @Override
    public char getChar() {
        return CONST_TORN;
    }

    static int[][] varde = new int[8][];

    static {
        varde[0] = new int[]{60, 60, 60, 60, 60, 60, 60, 60};
        varde[1] = new int[]{60, 60, 60, 60, 60, 60, 60, 60};
        varde[2] = new int[]{60, 60, 60, 60, 60, 60, 60, 60};
        varde[3] = new int[]{60, 60, 60, 60, 60, 60, 60, 60};
        varde[4] = new int[]{60, 60, 60, 60, 60, 60, 60, 60};
        varde[5] = new int[]{60, 60, 60, 60, 60, 60, 60, 60};
        varde[6] = new int[]{60, 60, 60, 60, 60, 60, 60, 60};
        varde[7] = new int[]{59, 60, 60, 60, 60, 60, 60, 59};
    }

    public static int getVarde(int rad, int kolumn) {
        return varde[rad][kolumn];
    }

}
