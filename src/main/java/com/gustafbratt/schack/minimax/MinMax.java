package com.gustafbratt.schack.minimax;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Farg;
import com.gustafbratt.schack.core.SpelStatus;
import com.gustafbratt.schack.core.pjas.Drag;

import java.util.List;

public class MinMax {

    public final static int INF = 999999;
    public final static int INF_NEG = -999999;

    public static Drag hittaBastaDrag(Brade brade, Farg farg, int djup) {
        System.out.println("Antal möjliga drag: " + brade.beraknaMojligaDrag().size());
        var res = negaMax(brade, djup, INF_NEG, INF, farg);
        var value = res.getPoang();
        var bastaDrag = res.getDrag();
        System.out.println("Klar med djup " + djup + " value: " + value + " drag: " + bastaDrag);
        return bastaDrag;
    }

    public static DragPoang negaMax(Brade node, int depth, int alpha, int beta, Farg farg) {
        if (Thread.currentThread().isInterrupted()) {
            throw new RuntimeException();
        }
        if (depth == 0 || node.poang() > 3_000 || node.poang() < -3_000) {
            return new DragPoang(null, node.poang() * farg.getPoangFaktor());
        }
        if (node.getSpelStatus().equals(SpelStatus.TRE_UPPREPNINGAR)) {
            return new DragPoang(null, 0);
        }
        int value = -99999;
        List<Drag> allaMojligaDrag = node.beraknaMojligaDrag();
        allaMojligaDrag.forEach(Drag::utfor);
        allaMojligaDrag.sort((o1, o2) -> (o2.getBradeTill().poang() - o1.getBradeTill().poang()) * farg.getPoangFaktor());
        Drag bastaDraget = null;
        for (Drag child : allaMojligaDrag) {
            var result = negaMax(child.getBradeTill(), depth - 1, -beta, -alpha, farg.andra()).negeraPoang();
            if (value < result.getPoang()) {
                value = result.getPoang();
                bastaDraget = child;
            }
            alpha = Math.max(alpha, value);
            if (alpha >= beta)
                break;
        }
        return new DragPoang(bastaDraget, value);
    }

}
