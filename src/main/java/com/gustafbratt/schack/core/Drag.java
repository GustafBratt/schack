package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.Pjas;

public class Drag {
    final char pjas;
    final String fran;
    final String till;
    final private Brade brade;
    final RockadTyp rockadTyp;

    public Drag(Brade brade, String fran, String till) {
        this.pjas = brade.charPa(fran);
        this.fran = fran;
        this.till = till;
        this.brade = brade;
        rockadTyp = null;
    }

    public Drag(Brade brade, RockadTyp rockadTyp) {
        this.brade = brade;
        this.pjas = Pjas.CONST_KUNG;
        this.rockadTyp = rockadTyp;
        this.fran = rockadTyp.kungFran;
        this.till = rockadTyp.kungTill;
    }

    public Brade getBrade() {
        return brade;
    }

    public Brade utfor() {
        return new Brade(this);
    }

    public String getTill() {
        return till;
    }

    @Override
    public String toString() {
        if (rockadTyp == null)
            return "" + pjas + fran + "-" + till;
        return "" + pjas + fran + "-" + till + "R";

    }

    public String getFran() {
        return fran;
    }

    public char getPjas() {
        return pjas;
    }
}

