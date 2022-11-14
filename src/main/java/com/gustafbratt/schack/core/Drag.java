package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.Pjas;

public class Drag {
    final Brade foregaende;
    final Brade kommande;
    final char pjas;
    final Position start;
    final Position till;

    //TODO: Verifiera att pjäsen får gå såhär?
    public Drag(Brade foregaende, Position start, Position till) {
        this.foregaende = foregaende;
        this.pjas = foregaende.charPa(start);
        this.start = start;
        this.till = till;
        this.kommande = skapaKommande();
    }

    public Brade getKommande() {
        return kommande;
    }

    public Position getTill() {
        return till;
    }

    private Brade skapaKommande() {
        Brade nya = foregaende.klonaOchFlippa();
        nya.setPjas(start.flippad(), '.');
        nya.setPjas(till.flippad(), foregaende.bytSpelare(pjas));
        return nya;
    }

    @Override
    public String toString() {
        return "" + pjas + start + "-" + till;
    }
}
