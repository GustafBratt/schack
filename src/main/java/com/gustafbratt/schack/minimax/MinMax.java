package com.gustafbratt.schack.minimax;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.UtanforBradetException;

import java.util.Collections;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MinMax {
    int startDjup;

    public MinMax(int startDjup) {
        this.startDjup = startDjup;
    }

    public DragPoang minimax(Brade node, int depth, boolean maximizingPlayer, int alpha, int beta) throws UtanforBradetException {
        if (depth == 0) {
            return new DragPoang(null, node.poang());
        }
        if (node.poang() > 3_000 || node.poang() < -3_000) {
            return new DragPoang(null, node.poang());
        }
        List<Drag> allaMojligaDrag = node.beraknaMojligaDrag();
        Collections.shuffle(allaMojligaDrag);
        Collections.reverse(allaMojligaDrag);
        int value;
        Drag bastaDrag = null;
        if (maximizingPlayer) { //vit
            value = Integer.MIN_VALUE;
            for (Drag drag : allaMojligaDrag) {
                Brade child = node.utforDrag(drag);
                DragPoang dp = minimax(child, depth - 1, false, alpha, beta);
                if (value <= dp.getPoang()) {
                    value = dp.getPoang();
                    bastaDrag = drag;
                }
                //if (value >= beta)
                //    break;
                alpha = max(alpha, value);
            }
        } else { //svart
            value = Integer.MAX_VALUE;
            for (Drag drag : allaMojligaDrag) {
                if (startDjup == depth) {
                        System.out.print(drag+ " ");
                }
                Brade child = node.utforDrag(drag);
                DragPoang dp = minimax(child, depth - 1, true, alpha, beta);
                if (value >= dp.getPoang()) {
                    value = dp.getPoang();
                    bastaDrag = drag;
                }
                //if (value <= alpha)
                //    break;
                beta = min(beta, value);
            }
            if (startDjup == depth) {
                System.out.println("");
            }
        }
        return new DragPoang(bastaDrag, value);
    }
}
