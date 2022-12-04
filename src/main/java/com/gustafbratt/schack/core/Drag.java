package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.Pjas;

public class Drag {
    final char pjas;
    final String fran;
    final String till;
    final private Brade bradeFran;
    private Brade bradeTill;
    final RockadTyp rockadTyp;

    public Drag(Brade bradeFran, String fran, String till) {
        this.pjas = bradeFran.charPa(fran);
        this.fran = fran;
        this.till = till;
        this.bradeFran = bradeFran;
        RockadTyp rockadSok = null;
        for(RockadTyp typ : RockadTyp.values()) {
            if(typ.kungFran.equals(fran) && typ.kungTill().equals(till) && bradeFran.charPa(fran) == 'K') {
                rockadSok = typ;
            }
        }
        rockadTyp = rockadSok;
    }

    public Drag(Brade bradeFran, RockadTyp rockadTyp) {
        this.bradeFran = bradeFran;
        this.pjas = Pjas.CONST_KUNG;
        this.rockadTyp = rockadTyp;
        this.fran = rockadTyp.kungFran;
        this.till = rockadTyp.kungTill;
    }

    public Brade getBradeFran() {
        return bradeFran;
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

    public Brade getBradeTill() {
        return bradeTill;
    }

    public void setBradeTill(Brade bradeTill) {
        this.bradeTill = bradeTill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drag drag = (Drag) o;

        if (pjas != drag.pjas) return false;
        if (fran != null ? !fran.equals(drag.fran) : drag.fran != null) return false;
        if (till != null ? !till.equals(drag.till) : drag.till != null) return false;
        if (bradeFran != null ? !bradeFran.equals(drag.bradeFran) : drag.bradeFran != null) return false;
        return rockadTyp == drag.rockadTyp;
    }

    @Override
    public int hashCode() {
        int result = pjas;
        result = 31 * result + (fran != null ? fran.hashCode() : 0);
        result = 31 * result + (till != null ? till.hashCode() : 0);
        result = 31 * result + (bradeFran != null ? bradeFran.hashCode() : 0);
        result = 31 * result + (rockadTyp != null ? rockadTyp.hashCode() : 0);
        return result;
    }
}

