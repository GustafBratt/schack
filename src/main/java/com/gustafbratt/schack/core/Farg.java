package com.gustafbratt.schack.core;

public enum Farg {
    SVART(-1),
    VIT(1);

    private final int poangFaktor;

    Farg(int poangFaktor) {
        this.poangFaktor = poangFaktor;
    }

    public Farg andra() {
        return this.equals(SVART) ? VIT : SVART;
    }

    public int getPoangFaktor() {
        return poangFaktor;
    }
}
