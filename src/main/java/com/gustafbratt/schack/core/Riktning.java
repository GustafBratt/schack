package com.gustafbratt.schack.core;

import java.util.List;

@FunctionalInterface
public interface Riktning {
    Position flytta(Position pos) throws UtanforBradetException;

    // +
    Riktning ETT = pos -> new Position((char) (pos.getKolumn() + 1), pos.getRad());
    Riktning TVA = pos -> new Position((char) (pos.getKolumn() - 1), pos.getRad());
    Riktning TRE = pos -> new Position(pos.getKolumn(), pos.getRad() + 1);
    Riktning FYRA = pos -> new Position(pos.getKolumn(), pos.getRad() - 1);


    // x
    Riktning FEM = pos -> new Position((char) (pos.getKolumn() + 1), pos.getRad() + 1);
    Riktning SEX = pos -> new Position((char) (pos.getKolumn() + 1), pos.getRad() - 1);
    Riktning SJU = pos -> new Position((char) (pos.getKolumn() - 1), pos.getRad() + 1);
    Riktning OTTA = pos -> new Position((char) (pos.getKolumn() - 1), pos.getRad() - 1);

    //snel hest
    Riktning SPRINGARE1 = pos -> new Position((char) (pos.getKolumn() + 1), pos.getRad() + 2);
    Riktning SPRINGARE2 = pos -> new Position((char) (pos.getKolumn() + 1), pos.getRad() - 2);
    Riktning SPRINGARE3 = pos -> new Position((char) (pos.getKolumn() - 1), pos.getRad() + 2);
    Riktning SPRINGARE4 = pos -> new Position((char) (pos.getKolumn() - 1), pos.getRad() - 2);

    Riktning SPRINGARE5 = pos -> new Position((char) (pos.getKolumn() + 2), pos.getRad() + 1);
    Riktning SPRINGARE6 = pos -> new Position((char) (pos.getKolumn() + 2), pos.getRad() - 1);
    Riktning SPRINGARE7 = pos -> new Position((char) (pos.getKolumn() - 2), pos.getRad() + 1);
    Riktning SPRINGARE8 = pos -> new Position((char) (pos.getKolumn() - 2), pos.getRad() - 1);


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
