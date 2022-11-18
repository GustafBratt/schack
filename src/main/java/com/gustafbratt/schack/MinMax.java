package com.gustafbratt.schack;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.UtanforBradetException;

import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MinMax {
    /*
    Drag alphabeta(Drag drag, int depth, int alpha, int beta, boolean maximizingPlayer) throws UtanforBradetException {
        Brade node = drag.getKommande();
        if ((depth == 0) || (node.poang() == Integer.MAX_VALUE || node.poang() == Integer.MIN_VALUE)) {
            return drag;
        }
        Drag value;
        if (maximizingPlayer) {  //Vit
            value = new Drag(Integer.MIN_VALUE);
            List<Drag> allaMojligaDrag = node.allaMojligaDrag();
            for (Drag mojligtDrag : allaMojligaDrag) {
                value = dragMedHogstPoang(value, alphabeta(mojligtDrag, depth - 1, alpha, beta, false));
                //if (value.getKommandePoang() >= beta) {
                //    break;
                //}
                alpha = max(alpha, value.getKommandePoang());
            }
        } else { //svart
            value = new Drag(Integer.MAX_VALUE);
            List<Drag> allaMojligaDrag = node.allaMojligaDrag();
            for (Drag mojligtDrag : allaMojligaDrag) {
                value = getDragMedLagstPoang(value, alphabeta(mojligtDrag, depth - 1, alpha, beta, true));
                //if (value.getKommandePoang() <= alpha) {
                //    break;
                //}
                beta = min(beta, value.getKommandePoang());
            }
        }
        return value;
    }

    private Drag getDragMedLagstPoang(Drag d1, Drag d2) {
        if(d1.getKommandePoang() < d2.getKommandePoang())
            return d1;
        return d2;
    }

    private Drag dragMedHogstPoang(Drag d1, Drag d2) {
        if(d1.getKommandePoang() > d2.getKommandePoang())
            return d1;
        return d2;
    }*/
}
