package com.gustafbratt.schack.minimax;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.pjas.Drag;

public class DragPoang {
    Drag drag;
    int poang;

    public DragPoang(Drag drag, int poang) {
        this.drag = drag;
        this.poang = poang;
    }

    public Drag getDrag() {
        return drag;
    }

    public void setDrag(Drag drag) {
        this.drag = drag;
    }

    public int getPoang() {
        return poang;
    }

    public void setPoang(int poang) {
        this.poang = poang;
    }

    public DragPoang negeraPoang() {
        poang = -poang;
        return this;
    }
}
