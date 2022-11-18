package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.*;

import java.util.List;

public class Bonde extends Pjas {

    public Bonde(Brade brade, Position position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_BONDE) {
            throw new IllegalStateException("Inte en bonde på position " + position + ". Det är en " + pjasKod);
        }
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
            if (brade.charPa(brade.framfor(position)) == '.') {
                Drag drag = new Drag(brade, position, brade.framfor(position));
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
        if ((brade.getAktuellFarg() == Farg.SVART && position.getRad() == 2) || (brade.getAktuellFarg() == Farg.VIT && position.getRad() == 7))  {
            try {
                if (brade.charPa(brade.framfor(position)) == '.' && brade.charPa(brade.framfor(brade.framfor(position))) == '.') {
                    Drag drag = new Drag(brade, position, brade.framfor(brade.framfor(position)));
                    mojligaDrag.add(drag);
                }
            } catch (UtanforBradetException ignored) {
            }
        }
        try {
            if (Character.isLowerCase(brade.charPa(brade.framforVanster(position)))) {
                Drag drag = new Drag(brade, position, brade.framforVanster(position));
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
        try {
            if (Character.isLowerCase(brade.charPa(brade.framforHoger(position)))) {
                Drag drag = new Drag(brade, position, brade.framforHoger(position));
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
    }
}
