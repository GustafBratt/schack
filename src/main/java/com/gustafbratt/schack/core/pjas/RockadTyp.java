package com.gustafbratt.schack.core.pjas;

public enum RockadTyp {
    VIT_LANG("e1", "c1", "a1", "d1", new String[]{"b1", "c1", "d1"}),
    VIT_KORT("e1", "g1", "h1", "f1", new String[]{"f1", "g1"}),
    SVART_LANG("e8", "c8", "a8", "d8", new String[]{"b8", "c8", "d8"}),
    SVART_KORT("e8", "g8", "h8", "f8", new String[]{"f8", "g8"});

    final String kungFran;
    final String kungTill;
    final String tornFran;
    final String tornTill;
    final String[] mellanPositioner;

    RockadTyp(String kungFran, String kungTill, String tornFran, String tornTill, String[] mellanPositioner) {
        this.kungFran = kungFran;
        this.kungTill = kungTill;
        this.tornFran = tornFran;
        this.tornTill = tornTill;
        this.mellanPositioner = mellanPositioner;
    }

    public String kungFran() {
        return kungFran;
    }

    public String kungTill() {
        return kungTill;
    }

    public String tornFran() {
        return tornFran;
    }

    public String tornTill() {
        return tornTill;
    }

    public String[] getMellanPositioner() {
        return mellanPositioner;
    }
}
