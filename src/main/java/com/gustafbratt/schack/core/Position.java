package com.gustafbratt.schack.core;

import static com.gustafbratt.schack.core.Farg.VIT;

public class Position {
    private int radRaw;
    private int kolumnRaw;
    private final Farg farg;

    public Position(int radRaw, int kolumnRaw, Farg farg) throws UtanforBradetException {
        validera(radRaw, kolumnRaw);
        this.radRaw = radRaw;
        this.kolumnRaw = kolumnRaw;
        this.farg = farg;
    }

    public Position(String position, Farg farg) throws UtanforBradetException {
        if (position.length() != 2) {
            throw new UtanforBradetException("Inte en giltig position: " + position);
        }
        kolumnRaw = position.charAt(0) - 'a';
        radRaw = Integer.parseInt(position.charAt(1) + "") - 1;
        validera(radRaw, kolumnRaw);
        this.farg = farg;
    }

    Farg getFarg() {
        return farg;
    }

    public Position flippad() {
        try {
            return new Position(7 - radRaw, 7 - kolumnRaw, farg);
        } catch (UtanforBradetException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void validera(int radRaw, int kolumnRaw) throws UtanforBradetException {
        if (radRaw < 0 || kolumnRaw < 0 || radRaw > 7 || kolumnRaw > 7) {
            throw new UtanforBradetException();
        }
    }

    public int getRad() {
        if(farg == VIT)
            return radRaw;
        return 7 - radRaw;
    }

    public int getKolumn() {
        if(farg == VIT)
            return kolumnRaw;
        return 7 - kolumnRaw;
    }

    public Position framfor() throws UtanforBradetException {
        if(farg == VIT)
            return new Position(radRaw - 1, kolumnRaw, farg);
        return new Position(radRaw + 1, kolumnRaw, farg);
    }

    public Position vanster() throws UtanforBradetException {
        if(farg == VIT)
            return new Position(radRaw, kolumnRaw - 1, farg);
        return new Position(radRaw, kolumnRaw + 1, farg);
    }

    public Position hoger() throws UtanforBradetException {
        if(farg == VIT)
            return new Position(radRaw, kolumnRaw + 1, farg);
        return new Position(radRaw, kolumnRaw - 1, farg);
    }

    @Override
    public String toString() {
        return "" + (char)(kolumnRaw + 'a') + (radRaw + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (radRaw != position.radRaw) return false;
        return kolumnRaw == position.kolumnRaw;
    }

    @Override
    public int hashCode() {
        int result = radRaw;
        result = 31 * result + kolumnRaw;
        return result;
    }


    public int getRadRaw() {
        return radRaw;
    }

    public int getKolumnRaw() {
        return kolumnRaw;
    }

}
