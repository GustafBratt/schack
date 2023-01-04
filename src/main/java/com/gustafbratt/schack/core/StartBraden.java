package com.gustafbratt.schack.core;

public enum StartBraden {
    TOMT,
    START,
    BONDER_DAM_KUNG,
    BONDER_DAM_KUNG_TORN,
    TORN_MOT_KUNG,
    TIME_FOR_ROCKAD,
    INGEN_INIT,
    ;

    public char[][] skapaRutor() {
        char[][] rutor = new char[8][];
        if (this == StartBraden.INGEN_INIT) {
            return rutor;
        }
        if (this == StartBraden.TOMT) {
            rutor[0] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[1] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[7] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            return rutor;
        }
        if (this == StartBraden.BONDER_DAM_KUNG) {
            rutor[0] = new char[]{'.', '.', '.', 'd', 'k', '.', '.', '.'};
            rutor[1] = new char[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
            rutor[7] = new char[]{'.', '.', '.', 'D', 'K', '.', '.', '.'};
            return rutor;
        }
        if (this == StartBraden.TIME_FOR_ROCKAD) {
            rutor[0] = new char[]{'t', '.', '.', 'd', 'k', '.', '.', 't'};
            rutor[1] = new char[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
            rutor[7] = new char[]{'T', '.', '.', 'D', 'K', '.', '.', 'T'};
            return rutor;
        }
        if (this == StartBraden.BONDER_DAM_KUNG_TORN) {
            rutor[0] = new char[]{'t', '.', '.', 'd', 'k', '.', '.', 't'};
            rutor[1] = new char[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
            rutor[7] = new char[]{'T', '.', '.', 'D', 'K', '.', '.', 'T'};
            return rutor;
        }
        if (this == StartBraden.TORN_MOT_KUNG) {
            rutor[0] = new char[]{'t', '.', '.', '.', '.', 'k', '.', '.'};
            rutor[1] = new char[]{'.', '.', '.', 's', 'l', '.', '.', '.'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'.', '.', '.', '.', 'K', '.', '.', '.'};
            rutor[7] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            return rutor;
        }
        rutor[0] = new char[]{'t', 's', 'l', 'd', 'k', 'l', 's', 't'}; //1 Svart
        rutor[1] = new char[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}; //2
        rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'}; //3
        rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'}; //4
        rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'}; //5
        rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'}; //6
        rutor[6] = new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}; //7
        rutor[7] = new char[]{'T', 'S', 'L', 'D', 'K', 'L', 'S', 'T'}; //8  Vit

        return rutor;
    }
}
