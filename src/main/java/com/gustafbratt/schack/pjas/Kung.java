package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;
import com.gustafbratt.schack.UtanforBradetException;

import java.util.ArrayList;
import java.util.List;

public class Kung implements PjasIf {

    Brade brade;
    Position position;

    public Kung(Brade brade, Position position) {
        char pjasKod = brade.pjasPa(position);
        if (pjasKod != CONST_KUNG) {
            throw new IllegalStateException("Inte en bonde på position " + position + ". Det är en " + pjasKod);
        }
        this.brade = brade;
        this.position = position;
    }

    @Override
    public List<Drag> mojligaDrag() {
        List<Drag> mojligaDrag = new ArrayList<>();
        // +
        try {
            testaDrag(mojligaDrag, position.framfor());
        } catch (UtanforBradetException ignored) { }
        try {
            testaDrag(mojligaDrag, position.hoger());
        } catch (UtanforBradetException ignored) { }
        try {
            testaDrag(mojligaDrag, position.vanster());
        } catch (UtanforBradetException ignored) { }
        try {
            testaDrag(mojligaDrag, position.bakom());
        } catch (UtanforBradetException ignored) { }
        // x
        try {
            testaDrag(mojligaDrag, position.framfor().hoger());
        } catch (UtanforBradetException ignored) { }
        try {
            testaDrag(mojligaDrag, position.hoger().bakom());
        } catch (UtanforBradetException ignored) { }
        try {
            testaDrag(mojligaDrag, position.vanster().framfor());
        } catch (UtanforBradetException ignored) { }
        try {
            testaDrag(mojligaDrag, position.bakom().vanster());
        } catch (UtanforBradetException ignored) { }

        return mojligaDrag;
    }

    private void testaDrag(List<Drag> mojligaDrag, Position positionTill) throws UtanforBradetException {
        if (ledigEllerMotstandare(positionTill)) {
            Drag drag = new Drag(brade, this.position, positionTill);
            mojligaDrag.add(drag);
        }
    }

    private boolean ledigEllerMotstandare(Position position) {
        char pjas = brade.pjasPa(position);
        return pjas == '.' || Character.isLowerCase(pjas);
    }
}