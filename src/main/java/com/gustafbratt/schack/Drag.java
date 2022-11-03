package com.gustafbratt.schack;

public class Drag {
    final Brade foregaende;
    final Brade kommande;
    final char pjas;
    final Position start;
    final Position slut;

    public Drag(Brade foregaende, char pjas, Position start, Position slut) {
        this.foregaende = foregaende;
        this.pjas = pjas;
        this.start = start;
        this.slut = slut;
        this.kommande = skapaKommande();
    }

    public Brade getKommande() {
        return kommande;
    }

    private Brade skapaKommande() {
        Brade nya = foregaende.klona();
        nya.setPjas(start, '.');
        nya.setPjas(slut, pjas);
        return nya;
    }
}
