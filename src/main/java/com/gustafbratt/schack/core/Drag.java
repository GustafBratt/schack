package com.gustafbratt.schack.core;

public class Drag {
    char pjas;
    final String start;
    final String till;
    final Brade brade;

    public Drag(Brade brade, String start, String till) {
        this.pjas = brade.charPa(start);
        this.start = start;
        this.till = till;
        this.brade = brade;
    }

    public Brade utfor(){
        return brade.utforDrag(this);
    }

    public String getTill() {
        return till;
    }

    @Override
    public String toString() {
        return "" + pjas + start + "-" + till;
    }

    public String getStart() {
        return start;
    }
}
