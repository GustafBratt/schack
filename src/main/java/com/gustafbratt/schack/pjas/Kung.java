package com.gustafbratt.schack.pjas;

import com.gustafbratt.schack.Brade;
import com.gustafbratt.schack.Drag;
import com.gustafbratt.schack.Position;
import com.gustafbratt.schack.UtanforBradetException;

import java.util.ArrayList;
import java.util.List;

public class Kung extends Pjas {

    public Kung(Brade brade, Position position) {
        char pjasKod = brade.pjasPa(position);
        if (pjasKod != CONST_KUNG) {
            throw new IllegalStateException("Inte en kung på position " + position + ". Det är en " + pjasKod);
        }
        this.brade = brade;
        this.position = position;
    }

    @Override
    public List<Drag> mojligaDrag() {
        // +
        try {
            skapaDragOmLedigEllerMotstandare(position.framfor());
        } catch (UtanforBradetException ignored) { }
        try {
            skapaDragOmLedigEllerMotstandare(position.hoger());
        } catch (UtanforBradetException ignored) { }
        try {
            skapaDragOmLedigEllerMotstandare(position.vanster());
        } catch (UtanforBradetException ignored) { }
        try {
            skapaDragOmLedigEllerMotstandare(position.bakom());
        } catch (UtanforBradetException ignored) { }
        // x
        try {
            skapaDragOmLedigEllerMotstandare(position.framfor().hoger());
        } catch (UtanforBradetException ignored) { }
        try {
            skapaDragOmLedigEllerMotstandare(position.hoger().bakom());
        } catch (UtanforBradetException ignored) { }
        try {
            skapaDragOmLedigEllerMotstandare(position.vanster().framfor());
        } catch (UtanforBradetException ignored) { }
        try {
            skapaDragOmLedigEllerMotstandare(position.bakom().vanster());
        } catch (UtanforBradetException ignored) { }

        return mojligaDrag;
    }

}