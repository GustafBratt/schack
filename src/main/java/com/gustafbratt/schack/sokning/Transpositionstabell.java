package com.gustafbratt.schack.sokning;

import com.gustafbratt.schack.core.Brade;

import java.util.HashMap;
import java.util.Optional;

public class Transpositionstabell {
    private static HashMap<Brade, TabellElement> tabell = new HashMap<>();

    public static void store(Brade brade, TabellElement tabellElement) {
        tabell.put(brade, tabellElement);
    }


    public static Optional<TabellElement> lookup(Brade brade) {
        return Optional.ofNullable(tabell.get(brade));
    }

    public static int getSize() {
        return tabell.size();
    }
}
