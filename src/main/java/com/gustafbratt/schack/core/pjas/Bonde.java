package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.*;

public class Bonde extends Pjas {

    public Bonde(Brade brade, String position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_BONDE) {
            throw new IllegalStateException("Inte en bonde på position " + position + ". Det är en " + pjasKod);
        }
        this.beraknaMojligaDrag();
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
        if ((brade.getAktuellFarg() == Farg.VIT && getRad(position) == 2) || (brade.getAktuellFarg() == Farg.SVART && getRad(position) == 7))  {
            try {
                if (brade.charPa(brade.framfor(position)) == '.' && brade.charPa(brade.framfor(brade.framfor(position))) == '.') {
                    Drag drag = new Drag(brade, position, brade.framfor(brade.framfor(position)));
                    mojligaDrag.add(drag);
                }
            } catch (UtanforBradetException ignored) {
            }
        }
        try {
            String framforVanster = brade.framforVanster(this.position);
            PositionUtils.validera(framforVanster);
            if (Character.isLowerCase(brade.charPa(framforVanster))) {
                Drag drag = new Drag(brade, this.position, framforVanster);
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
        try {
            String framforHoger = brade.framforHoger(this.position);
            PositionUtils.validera(framforHoger);
            if (Character.isLowerCase(brade.charPa(framforHoger))) {
                Drag drag = new Drag(brade, this.position, framforHoger);
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
    }

    int getRad(String position) {
        return Integer.parseInt(""+position.charAt(1)); //TODO: wtf?
    }
}
