package com.gustafbratt.schack.minimax;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.UtanforBradetException;

import java.util.Collections;
import java.util.List;

public class MinMax {
    int startDjup;

    public MinMax(int startDjup) {
        this.startDjup = startDjup;
    }

    public DragPoang minimax(Brade node, int depth, boolean maximizingPlayer) throws UtanforBradetException {
        if (depth == 0){
            return new DragPoang(null, node.poang());
        }
        if(node.poang() > 5_000 || node.poang() < -5_000) { // Svart vill ha litet. djupt ner har låg depth
            return new DragPoang(null, node.poang()+depth); //TODO if maximzingPlayer?
        }
        List<Drag> allaMojligaDrag = node.allaMojligaDrag();
        Collections.shuffle(allaMojligaDrag);
        int value;
        int dragpekare = 0;
        Drag bastaDrag = null;
        if(maximizingPlayer) { //vit
            value = Integer.MIN_VALUE;
            for(Drag drag : allaMojligaDrag) {
                if(startDjup == depth) {
                    dragpekare++;
                    System.out.print("" + dragpekare + "/" + allaMojligaDrag.size() + " ");
                }
                Brade child = node.utforDrag(drag);
                DragPoang dp = minimax(child, depth - 1, false);
                if(value <= dp.getPoang()) {
                    value = dp.getPoang();
                    bastaDrag = drag;
                }
            }
        } else { //svart
            value = Integer.MAX_VALUE;
            for(Drag drag : allaMojligaDrag) {
                if(startDjup == depth) {
                    dragpekare++;
                    System.out.print("" + dragpekare + "/" + allaMojligaDrag.size() + " ");
                }
                Brade child = node.utforDrag(drag);
                DragPoang dp = minimax(child, depth - 1, true);
                if(value >= dp.getPoang()) {
                    value = dp.getPoang();
                    bastaDrag = drag;
                }
            }
        }
        if(startDjup == depth) {
            System.out.println();
        }
        return new DragPoang(bastaDrag, value);
    }
}
