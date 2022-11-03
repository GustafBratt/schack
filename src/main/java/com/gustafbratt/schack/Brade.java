package com.gustafbratt.schack;

/*
  a b c d e f g h
 1
 2
 3
 4
 5
 6
 7
 8
 */
public class Brade {
    char[][] rutor = new char[8][8];
    public void print() {
        System.out.println("  a b c d e f g h");
        for(int i = 0; i < 8; i++ ) {
            System.out.print(i+1 + "|");
            for(int j = 0; j < 8; j++) {
                System.out.print(rutor[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("  a b c d e f g h");
    }
    public void startPosition() {
        rutor[0] = new char[]{'t', 's', 'l', 'd', 'k', 'l', 's', 't'};
        rutor[1] = new char[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
        rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        rutor[6] = new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
        rutor[7] = new char[]{'T', 'S', 'L', 'D', 'K', 'L', 'S', 'T'};

    }

    public char pjasPa(Position position) {
        return rutor[position.radRaw][position.kolumnRaw];
    }

    public Brade klona() {
        Brade nya = new Brade();
        for(int i = 0; i < 8 ; i++) {
            System.arraycopy(rutor[i], 0, nya.rutor[i], 0, 8);
        }
        return nya;
    }

    public void setPjas(Position position, char c) {
        rutor[position.getRadRaw()][position.getKolumnRaw()] = c;
    }
}
