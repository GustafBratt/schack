package com.gustafbratt.schack.core;

import java.util.List;

@FunctionalInterface
public interface Riktning {
    Position flytta(Position pos) throws UtanforBradetException;

    // +
    Riktning ETT = pos -> new Position(pos.getRadRaw() - 1, pos.getKolumnRaw(), pos.getFarg());
    Riktning TVA = pos -> new Position(pos.getRadRaw() + 1, pos.getKolumnRaw(), pos.getFarg());
    Riktning TRE = pos -> new Position(pos.getRadRaw(), pos.getKolumnRaw() + 1, pos.getFarg());
    Riktning FYRA = pos -> new Position(pos.getRadRaw(), pos.getKolumnRaw() - 1, pos.getFarg());


    // x
    Riktning FEM = pos -> new Position(pos.getRadRaw() - 1, pos.getKolumnRaw() + 1, pos.getFarg());
    Riktning SEX = pos -> new Position(pos.getRadRaw() + 1, pos.getKolumnRaw() + 1, pos.getFarg());
    Riktning SJU = pos -> new Position(pos.getRadRaw() - 1, pos.getKolumnRaw() - 1, pos.getFarg());
    Riktning OTTA = pos -> new Position(pos.getRadRaw() + 1, pos.getKolumnRaw() - 1, pos.getFarg());

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
}
