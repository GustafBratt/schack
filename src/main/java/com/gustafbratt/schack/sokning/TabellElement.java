package com.gustafbratt.schack.sokning;

public class TabellElement {
    private final ElementTyp elementTyp;
    private final int value;
    private final int depth;

    public TabellElement(ElementTyp elementTyp, int value, int depth) {
        this.elementTyp = elementTyp;
        this.value = value;
        this.depth = depth;
    }

    public ElementTyp getElementTyp() {
        return elementTyp;
    }

    public int getValue() {
        return value;
    }

    public int getDepth() {
        return depth;
    }
}

enum ElementTyp {
    EXACT,
    LOWERBOUND,
    UPPERBOUND,
}
