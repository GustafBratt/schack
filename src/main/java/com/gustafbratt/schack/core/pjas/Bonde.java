package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.Position;
import com.gustafbratt.schack.core.UtanforBradetException;

import java.util.List;

public class Bonde extends Pjas {

    public Bonde(Brade brade, Position position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_BONDE) {
            throw new IllegalStateException("Inte en bonde på position " + position + ". Det är en " + pjasKod);
        }
        //this.brade = brade;
        //this.position = position;
        this.beraknaMojligaDrag();
    }

    @Override
    public List<Drag> getMojligaDrag() {
        return mojligaDrag;
    }

    @Override
    public char getChar() {
        return CONST_BONDE;
    }

    private void beraknaMojligaDrag() {
        try {
            if (brade.charPa(position.framfor()) == '.') {
                Drag drag = new Drag(brade, position, position.framfor());
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
        if (position.getRad() == 6) {
            try {
                if (brade.charPa(position.framfor()) == '.' && brade.charPa(position.framfor().framfor()) == '.') {
                    Drag drag = new Drag(brade, position, position.framfor().framfor());
                    mojligaDrag.add(drag);
                }
            } catch (UtanforBradetException ignored) {
            }
        }
        try {
            if (Character.isLowerCase(brade.charPa(position.framfor().vanster()))) {
                Drag drag = new Drag(brade, position, position.framfor().vanster());
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
        try {
            if (Character.isLowerCase(brade.charPa(position.framfor().hoger()))) {
                Drag drag = new Drag(brade, position, position.framfor().hoger());
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
    }
}
