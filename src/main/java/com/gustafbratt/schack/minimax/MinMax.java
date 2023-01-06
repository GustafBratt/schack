package com.gustafbratt.schack.minimax;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Farg;
import com.gustafbratt.schack.core.SpelStatus;
import com.gustafbratt.schack.core.pjas.Drag;

import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MinMax {

    public static Drag hittaBastaDrag(Brade brade, Farg farg, int djup) {
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        List<Drag> allaMojligaDrag = brade.beraknaMojligaDrag();
        allaMojligaDrag.forEach(Drag::utfor);
        int value;
        Drag bastaDrag = null;
        if (farg == Farg.VIT) { //vit
            allaMojligaDrag.sort((o1, o2) -> o2.getBradeTill().poang() - o1.getBradeTill().poang());
            value = Integer.MIN_VALUE;
            for (Drag drag : allaMojligaDrag) {
                Brade child = new Brade(drag);
                DragPoang dp = minimax(child, djup - 1, false, alpha, beta);
                if (value <= dp.getPoang()) {
                    value = dp.getPoang();
                    bastaDrag = drag;
                }
                alpha = max(alpha, value);
            }
        } else { //svart
            value = Integer.MAX_VALUE;
            allaMojligaDrag.sort((o2, o1) -> o2.getBradeTill().poang() - o1.getBradeTill().poang());
            for (Drag drag : allaMojligaDrag) {
                System.out.print(djup + " ");
                Brade child = new Brade(drag);
                DragPoang dp = minimax(child, djup - 1, true, alpha, beta);
                if (value >= dp.getPoang()) {
                    value = dp.getPoang();
                    bastaDrag = drag;
                }
                beta = min(beta, value);
            }
            System.out.println("");
        }
        System.out.println("Klar med djup " + djup + " value: " + value + " drag: " + bastaDrag);
        return bastaDrag;
    }

    public static DragPoang minimax(Brade node, int depth, boolean maximizingPlayer, int alpha, int beta) {
        if (Thread.currentThread().isInterrupted()) {
            throw new RuntimeException();
        }
        if (depth == 0) {
            return new DragPoang(null, node.poang());
        }
        if (node.poang() > 3_000 || node.poang() < -3_000) {
            return new DragPoang(null, node.poang());
        }
        if (node.getSpelStatus().equals(SpelStatus.TRE_UPPREPNINGAR)) {
            return new DragPoang(null, 0);
        }
        List<Drag> allaMojligaDrag = node.beraknaMojligaDrag();
        int value;
        Drag bastaDrag = null;
        allaMojligaDrag.forEach(Drag::utfor);
        if (maximizingPlayer) { //vit
            value = Integer.MIN_VALUE;
            allaMojligaDrag.sort((o1, o2) -> o2.getBradeTill().poang() - o1.getBradeTill().poang());
            for (Drag drag : allaMojligaDrag) {
                Brade child = drag.getBradeTill();
                DragPoang dp = minimax(child, depth - 1, false, alpha, beta);
                if (value <= dp.getPoang()) {
                    value = dp.getPoang();
                    bastaDrag = drag;
                }
                if (value > beta)
                    break;
                alpha = max(alpha, value);
            }
        } else { //svart
            value = Integer.MAX_VALUE;
            allaMojligaDrag.sort((o2, o1) -> o2.getBradeTill().poang() - o1.getBradeTill().poang());
            for (Drag drag : allaMojligaDrag) {
                Brade child = drag.getBradeTill();
                DragPoang dp = minimax(child, depth - 1, true, alpha, beta);
                if (value >= dp.getPoang()) {
                    value = dp.getPoang();
                    bastaDrag = drag;
                }
                if (value < alpha)
                    break;
                beta = min(beta, value);
            }
        }
        return new DragPoang(bastaDrag, value);
    }
}
