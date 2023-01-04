package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.UtanforBradetException;

public class PositionUtils {
    public static void validera(String position) throws UtanforBradetException {
        if (position.length() != 2) {
            throw new UtanforBradetException("Inte en giltig position: " + position);
        }
        int kolumnRaw = position.charAt(0) - 'a';
        int radRaw = Integer.parseInt(position.charAt(1) + "") - 1;
        if (radRaw < 0 || kolumnRaw < 0 || radRaw > 7 || kolumnRaw > 7) {
            throw new UtanforBradetException();
        }
    }

    public static int getRadRaw(String position) {
        return Integer.parseInt(position.charAt(1) + "") - 1;
    }

    public static int getKolumnRaw(String position) {
        return position.charAt(0) - 'a';
    }


}
