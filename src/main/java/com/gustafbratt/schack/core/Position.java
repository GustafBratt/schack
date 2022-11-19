package com.gustafbratt.schack.core;

public class Position {
    String position;

    public Position(String position) throws UtanforBradetException {
        if (position.length() != 2) {
            throw new UtanforBradetException("Inte en giltig position: " + position);
        }
        int kolumnRaw = position.charAt(0) - 'a';
        int radRaw = Integer.parseInt(position.charAt(1) + "") - 1;
        validera(radRaw, kolumnRaw);
        this.position = position;
    }

    public Position(char kolumn, int rad) throws UtanforBradetException {
        this(""+ kolumn + rad);
    }

    public char getKolumn() {
        return position.charAt(0);
    }

    public int getRad() {
        return Integer.parseInt(position.charAt(1) + "");
    }



    private void validera(int radRaw, int kolumnRaw) throws UtanforBradetException {
        if (radRaw < 0 || kolumnRaw < 0 || radRaw > 7 || kolumnRaw > 7) {
            throw new UtanforBradetException();
        }
    }

    @Override
    public String toString() {
        return "" + position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position1 = (Position) o;

        return position.equals(position1.position);
    }

    @Override
    public int hashCode() {
        return position.hashCode();
    }

    public String asString() {
        return position;
    }
}
