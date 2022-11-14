package com.gustafbratt.schack.core;

public enum Farg {
    SVART,
    VIT;

    public Farg andra() {
        return this.equals(SVART) ? VIT : SVART;
    }
}
