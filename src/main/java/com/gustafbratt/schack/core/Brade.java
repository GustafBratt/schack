package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.Bonde;
import com.gustafbratt.schack.core.pjas.Dam;
import com.gustafbratt.schack.core.pjas.Kung;
import com.gustafbratt.schack.core.pjas.Pjas;

public class Brade {
    private final char[][] rutor = new char[8][8];
    Farg aktuellFarg = Farg.VIT;

    public Brade(BRADE_INIT_TYP typ) {
        if (typ == BRADE_INIT_TYP.INGEN_INIT) {
            return;
        }
        if (typ == BRADE_INIT_TYP.TOMT)  {
            rutor[0] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[1] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[7] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            return;
        }
        rutor[0] = new char[]{'t', 's', 'l', 'd', 'k', 'l', 's', 't'};
        rutor[1] = new char[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        rutor[6] = new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
        rutor[7] = new char[]{'T', 'S', 'L', 'D', 'K', 'L', 'S', 'T'};
    }

    public void print() {
        if(aktuellFarg == Farg.VIT) {
            System.out.println("  a b c d e f g h  " + aktuellFarg + "s drag");
            for (int i = 0; i < 8; i++) {
                System.out.print(i + 1 + "|");
                for (int j = 0; j < 8; j++) {
                    System.out.print(rutor[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }
            System.out.println("  a b c d e f g h");
            return;
        }
        System.out.println("  h g f e d c b a  " + aktuellFarg + "s drag");
        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i + "|");
            for (int j = 0; j < 8; j++) {
                System.out.print(rutor[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("  h g f e d c b a");

    }

    public char charPa(Position position) {
        return rutor[position.getRad()][position.getKolumn()];
    }

    public Brade klonaOchFlippa() {
        Brade nya = new Brade(BRADE_INIT_TYP.INGEN_INIT);
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j  < 8; j++) {
                nya.rutor[i][j] = bytSpelare(rutor[7-i][7-j]);
            }
        }
        nya.aktuellFarg = aktuellFarg.andra();
        return nya;
    }

    public char bytSpelare(char c) {
        if(Character.isUpperCase(c))
            return Character.toLowerCase(c);
        return Character.toUpperCase(c);
    }

    public void setPjas(Position position, char c) {
        rutor[position.getRad()][position.getKolumn()] = c;
    }

    public Farg getAktuellFarg() {
        return aktuellFarg;
    }

    public Position position(String s) throws UtanforBradetException {
        return new Position(s, aktuellFarg);
    }

    public enum BRADE_INIT_TYP {
        TOMT,
        START,
        INGEN_INIT,
    }
}
