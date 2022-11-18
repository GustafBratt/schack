package com.gustafbratt.schack.core;

public class Drag {
    char pjas;
    final Position start;
    final Position till;

    //TODO: Verifiera att pjäsen får gå såhär?
    public Drag(Brade brade, Position start, Position till) {
        this.pjas = brade.charPa(start);
        this.start = start;
        this.till = till;
    }

    public Position getTill() {
        return till;
    }

    @Override
    public String toString() {
        return "" + pjas + start + "-" + till;
    }

    public Position getStart() {
        return start;
    }
}
