package com.gustafbratt.schack;

import java.util.List;

@FunctionalInterface
public interface Flytt {
    Position flytta(Position pos) throws UtanforBradetException;

    // +
    Flytt FRAM = pos -> new Position(pos.getRadRaw() - 1, pos.getKolumnRaw());
    Flytt BAK = pos -> new Position(pos.getRadRaw() + 1, pos.getKolumnRaw());
    Flytt HOGER = pos -> new Position(pos.getRadRaw(), pos.getKolumnRaw() + 1);
    Flytt VANSTER = pos -> new Position(pos.getRadRaw(), pos.getKolumnRaw() - 1);


    // x
    Flytt FRAMHOGER = pos -> new Position(pos.getRadRaw() - 1, pos.getKolumnRaw() + 1);
    Flytt BAKHOGER = pos -> new Position(pos.getRadRaw() + 1, pos.getKolumnRaw() + 1);
    Flytt FRAMVANSTER = pos -> new Position(pos.getRadRaw() - 1, pos.getKolumnRaw() - 1);
    Flytt BAKVANSTER = pos -> new Position(pos.getRadRaw() + 1, pos.getKolumnRaw() - 1);

    List<Flytt> ALLARIKTNINGAR = List.of(FRAM, BAK, HOGER, VANSTER, FRAMHOGER, BAKHOGER, FRAMVANSTER, BAKVANSTER);

}
