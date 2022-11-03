package com.gustafbratt.schack;

public class Position {
    int radRaw;
    int kolumnRaw;

    public Position(int radRaw, int kolumnRaw) {
        this.radRaw = radRaw;
        this.kolumnRaw = kolumnRaw;
    }

    public Position(String position) throws UtanforBradetException {
        if (position.length() != 2) {
            throw new UtanforBradetException("Inte en giltig position: " + position);
        }
        kolumnRaw = position.charAt(0) - 'A';
        radRaw = Integer.parseInt(position.charAt(1) + "") - 1;
        System.out.println(kolumnRaw);
    }

    public int getRadRaw() {
        return radRaw;
    }

    public int getKolumnRaw() {
        return kolumnRaw;
    }

    public Position framfor() {
        return new Position(radRaw - 1, kolumnRaw);
    }
}
