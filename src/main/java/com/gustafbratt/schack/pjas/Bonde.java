package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;
import com.gustafbratt.schack.UtanforBradetException;

import java.util.List;

public class Bonde extends Pjas {

    public Bonde(Brade brade, Position position) {
        char pjasKod = brade.pjasPa(position);
        if (pjasKod != CONST_BONDE) {
            throw new IllegalStateException("Inte en bonde på position " + position + ". Det är en " + pjasKod);
        }
        this.brade = brade;
        this.position = position;
    }

    @Override
    public List<Drag> mojligaDrag() {
        try {
            if (brade.pjasPa(position.framfor()) == '.') {
                Drag drag = new Drag(brade, position, position.framfor());
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) { }
        if (position.getRadRaw() == 6) {
            try {
                if (brade.pjasPa(position.framfor()) == '.' && brade.pjasPa(position.framfor().framfor()) == '.') {
                    Drag drag = new Drag(brade, position, position.framfor().framfor());
                    mojligaDrag.add(drag);
                }
            } catch (UtanforBradetException ignored) {
            }
        }
        try {
            if (Character.isLowerCase(brade.pjasPa(position.framfor().vanster()))) {
                Drag drag = new Drag(brade, position, position.framfor().vanster());
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
        try {
            if (Character.isLowerCase(brade.pjasPa(position.framfor().hoger()))) {
                Drag drag = new Drag(brade, position, position.framfor().hoger());
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }

        return mojligaDrag;
    }
}
