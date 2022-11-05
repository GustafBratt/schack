package com.gustafbratt.schack;

public class Position {
    int radRaw;
    int kolumnRaw;

    public Position(int radRaw, int kolumnRaw) throws UtanforBradetException {
        validera(radRaw, kolumnRaw);
        this.radRaw = radRaw;
        this.kolumnRaw = kolumnRaw;
    }

    public Position(String position) throws UtanforBradetException {
        if (position.length() != 2) {
            throw new UtanforBradetException("Inte en giltig position: " + position);
        }
        kolumnRaw = position.charAt(0) - 'a';
        radRaw = Integer.parseInt(position.charAt(1) + "") - 1;
        validera(radRaw, kolumnRaw);
    }

    private void validera(int radRaw, int kolumnRaw) throws UtanforBradetException {
        if (radRaw < 0 || kolumnRaw < 0 || radRaw > 7 || kolumnRaw > 7) {
            throw new UtanforBradetException();
        }
    }

    public int getRadRaw() {
        return radRaw;
    }

    public int getKolumnRaw() {
        return kolumnRaw;
    }

    public Position framfor() throws UtanforBradetException {
        return new Position(radRaw - 1, kolumnRaw);
    }

    public Position bakom() throws UtanforBradetException {
        return new Position(radRaw + 1, kolumnRaw);
    }


    public Position vanster() throws UtanforBradetException {
        return new Position(radRaw, kolumnRaw - 1);
    }

    public Position hoger() throws UtanforBradetException {
        return new Position(radRaw, kolumnRaw + 1);
    }

    @Override
    public String toString() {
        return "" + (char)(kolumnRaw + 'a') + (radRaw + 1);
    }
}
