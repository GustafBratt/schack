package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;

import java.util.ArrayList;
import java.util.List;

public class Bonde {
    Brade brade;
    Position position;

    public Bonde(Brade brade, Position position) {
        char pjasKod = brade.pjasPa(position);
        if (pjasKod != 'B') {
            throw new IllegalStateException("Inte en bonde på position " + position + ". Det är en " + pjasKod);
        }
        this.brade = brade;
        this.position = position;
    }

    public List<Drag> mojligaDrag() {
        List<Drag> mojligaDrag = new ArrayList<>();
        if (brade.pjasPa(position.framfor()) == '.') {
            Drag drag = new Drag(brade, 'B', position, position.framfor());
            mojligaDrag.add(drag);
        }
        if (position.getRadRaw() == 6) {
            if (brade.pjasPa(position.framfor().framfor()) == '.') {
                Drag drag = new Drag(brade, 'B', position, position.framfor().framfor());
                mojligaDrag.add(drag);
            }
        }
        return mojligaDrag;
    }
}
