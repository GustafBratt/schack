package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;

public class Drag {
    final char pjas;
    final String fran;
    final String till;
    final private Brade bradeFran;
    private Brade bradeTill; //Sätts vid utför()
    final RockadTyp rockadTyp;
    private final char tagenPjas;
    private final boolean promovering;
    final DragTyp dragTyp;

    //TODO kolla om den här finns med i listan över giltiga drag?
    Drag(Brade bradeFran, String fran, String till, DragTyp dragTyp) {
        this.fran = fran;
        this.till = till;
        this.bradeFran = bradeFran;
        this.dragTyp = dragTyp;
        rockadTyp = null;
        if(dragTyp == DragTyp.ENPASSANT) {
            tagenPjas = 'b';
        } else {
            tagenPjas = bradeFran.charPa(till);
        }
        int radFran = Integer.parseInt(fran.charAt(1) + "");
        int radTill = Integer.parseInt(till.charAt(1) + "");
        if (bradeFran.charPa(fran) == Pjas.CONST_BONDE && (radFran == 7 || radFran == 2) && (radTill == 8 || radTill == 1)) {
            this.pjas = Pjas.CONST_DAM;
            promovering = true;
        } else {
            this.pjas = bradeFran.charPa(fran);
            promovering = false;
        }
    }

    //TODO: Den här behövs ju inte. All logik finns ju i vanliga konstruktorn
    Drag(Brade bradeFran, RockadTyp rockadTyp) {
        this.bradeFran = bradeFran;
        this.pjas = Pjas.CONST_KUNG;
        this.rockadTyp = rockadTyp;
        this.fran = rockadTyp.kungFran;
        this.till = rockadTyp.kungTill;
        tagenPjas = '.';
        promovering = false;
        dragTyp = DragTyp.ROCKAD;
    }

    public RockadTyp getRockadTyp() {
        return rockadTyp;
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
        if (rockadTyp != null) {
            return "" + pjas + fran + "-" + till + "R";
        }
        String promSuffix = "";
        if (promovering)
            promSuffix = "=Q";
        if (tagenPjas != '.')
            return "" + pjas + fran + "-" + till + "x" + Character.toUpperCase(tagenPjas) + promSuffix;
        return "" + pjas + fran + "-" + till + promSuffix;
    }

    public boolean tarAnnanPjas() {
        return tagenPjas != '.';
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

    public DragTyp getDragTyp() {
        return dragTyp;
    }
}

