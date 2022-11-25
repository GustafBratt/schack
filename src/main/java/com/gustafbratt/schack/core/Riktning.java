package com.gustafbratt.schack.core;

import java.util.List;

@FunctionalInterface
public interface Riktning {
    String flytta(String pos) throws UtanforBradetException;

    // +
    Riktning ETT = pos -> nyPosition(pos, 1,0);
    Riktning TVA = pos -> nyPosition(pos, -1,0);
    Riktning TRE = pos -> nyPosition(pos, 0,+1);
    Riktning FYRA = pos -> nyPosition(pos, 0,-1);

    // x
    Riktning FEM = pos -> nyPosition(pos, 1,1);
    Riktning SEX = pos -> nyPosition(pos, 1,-1);
    Riktning SJU = pos -> nyPosition(pos, -1,1);
    Riktning OTTA = pos-> nyPosition(pos, -1,-1);

    //snel hest
    Riktning SPRINGARE1 = pos -> nyPosition(pos, 1,+2); 
    Riktning SPRINGARE2 = pos -> nyPosition(pos, 1,-2);
    Riktning SPRINGARE3 = pos -> nyPosition(pos, -1,+2);
    Riktning SPRINGARE4 = pos -> nyPosition(pos, -1,-2);
    Riktning SPRINGARE5 = pos -> nyPosition(pos, 2,1);
    Riktning SPRINGARE6 = pos -> nyPosition(pos, 2,-1);
    Riktning SPRINGARE7 = pos -> nyPosition(pos, -2,1);
    Riktning SPRINGARE8 = pos -> nyPosition(pos, -2,-1);

    private static String nyPosition(String position, int kolumnFlytt, int radFlytt){
        char kolumn = (char)(position.charAt(0) + (char)kolumnFlytt);
        int rad = Integer.parseInt(""+position.charAt(1));
        rad += radFlytt;
        return "" + kolumn + rad;
    };


    List<Riktning> ALLARIKTNINGAR = List.of(
            ETT,
            TVA,
            TRE,
            FYRA,
            FEM,
            SEX,
            SJU,
            OTTA
    );

    List<Riktning> RAKA = List.of(
            ETT,
            TVA,
            TRE,
            FYRA
    );

    List<Riktning> DIAGONAL = List.of(
            FEM,
            SEX,
            SJU,
            OTTA
    );

    List<Riktning> SPRINGARE  = List.of(
            SPRINGARE1,
            SPRINGARE2,
            SPRINGARE3,
            SPRINGARE4,
            SPRINGARE5,
            SPRINGARE6,
            SPRINGARE7,
            SPRINGARE8
    );
}
