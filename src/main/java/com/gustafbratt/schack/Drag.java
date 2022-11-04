package com.gustafbratt.schack;

public class Drag {
    final Brade foregaende;
    final Brade kommande;
    final char pjas;
    final Position start;
    final Position till;

    public Drag(Brade foregaende, Position start, Position till) {
        this.foregaende = foregaende;
        this.pjas = foregaende.pjasPa(start);
        this.start = start;
        this.till = till;
        this.kommande = skapaKommande();
    }

    public Brade getKommande() {
        return kommande;
    }

    private Brade skapaKommande() {
        Brade nya = foregaende.klona();
        nya.setPjas(start, '.');
        nya.setPjas(till, pjas);
        return nya;
    }

    @Override
    public String toString() {
        return "" + pjas + start + "-" + till;
    }
}
