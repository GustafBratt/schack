package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.gustafbratt.schack.core.Farg.SVART;
import static com.gustafbratt.schack.core.Farg.VIT;

public class Brade {
    private final char[][] rutor = new char[8][8];
    private Optional<Integer> poang = Optional.empty();
    Farg aktuellFarg = VIT;

    public Brade(BRADE_INIT_TYP typ) {
        if (typ == BRADE_INIT_TYP.INGEN_INIT) {
            return;
        }
        if (typ == BRADE_INIT_TYP.TOMT) {
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
        if (typ == BRADE_INIT_TYP.BONDER_DAM_KUNG) {
            rutor[0] = new char[]{'.', '.', '.', 'd', 'k', '.', '.', '.'};
            rutor[1] = new char[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
            rutor[7] = new char[]{'.', '.', '.', 'D', 'K', '.', '.', '.'};
            return;
        }
        if (typ == BRADE_INIT_TYP.BONDER_DAM_KUNG_TORN) {
            rutor[0] = new char[]{'t', '.', '.', 'd', 'k', '.', '.', 't'};
            rutor[1] = new char[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'};
            rutor[7] = new char[]{'T', '.', '.', 'D', 'K', '.', '.', 'T'};
            return;
        }
        if(typ == BRADE_INIT_TYP.TORN_MOT_KUNG) {
            rutor[0] = new char[]{'t', 's', 'd', 'k', '.', '.', '.', 't'};
            rutor[1] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'.', '.', '.', '.', 'B', '.', '.', '.'};;
            rutor[7] = new char[]{'.', '.', '.', '.', 'K', '.', '.', '.'};
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
        if (aktuellFarg == VIT) {
            System.out.println("  a b c d e f g h  " + aktuellFarg + "s drag. Poäng: " + poang());
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
        System.out.println("  h g f e d c b a  " + aktuellFarg + "s drag Poäng: " + poang());
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

    public char charPa(Position p) {
        String position = p.asString();
        int kolumnRaw = position.charAt(0) - 'a';
        int radRaw = Integer.parseInt(position.charAt(1) + "") - 1;
        if (aktuellFarg == SVART) {
            kolumnRaw = 7 - kolumnRaw;
            radRaw = 7 - radRaw;
        }
        return rutor[radRaw][kolumnRaw];
    }

    public void setPjas(Position p, char c) {
        String position = p.asString();
        int kolumnRaw = position.charAt(0) - 'a';
        int radRaw = Integer.parseInt(position.charAt(1) + "") - 1;
        if (aktuellFarg == SVART) {
            kolumnRaw = 7 - kolumnRaw;
            radRaw = 7 - radRaw;
        }
        rutor[radRaw][kolumnRaw] = c;
    }

    public Brade utforDrag(Drag drag) {
        Brade b2 = klonaOchFlippa();
        char pjas = b2.charPa(drag.getStart());
        b2.setPjas(drag.getTill(), pjas);
        b2.setPjas(drag.getStart(), '.');
        return b2;
    }

    public Brade klonaOchFlippa() {
        Brade nya = new Brade(BRADE_INIT_TYP.INGEN_INIT);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                nya.rutor[i][j] = bytSpelare(rutor[7 - i][7 - j]);
            }
        }
        nya.aktuellFarg = aktuellFarg.andra();
        return nya;
    }

    public char bytSpelare(char c) {
        if (Character.isUpperCase(c))
            return Character.toLowerCase(c);
        return Character.toUpperCase(c);
    }

    public Farg getAktuellFarg() {
        return aktuellFarg;
    }

    public int poang() {
        if(poang.isEmpty()) {
            poang = Optional.of(beraknaPoang());
        }
        return poang.get();
    }

    public static final int VIT_VINNER = 10_000;
    public static final int SVART_VINNER = -10_000;

    //MAX_INT vit vinner
    //MIN_INT svart vinner
    private int beraknaPoang() {
        int poangAktuell = 0;
        int poangInaktuell = 0;
        boolean aktuellKungHittad = false;
        boolean inaktuellKungHittad = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Character.isUpperCase(rutor[i][j])) {
                    poangAktuell += getVarde(rutor[i][j]);
                }
                if (Character.isLowerCase(rutor[i][j])) {
                    poangInaktuell += getVarde(rutor[i][j]);
                }
                if (rutor[i][j] == 'K')
                    aktuellKungHittad = true;
                if (rutor[i][j] == 'k')
                    inaktuellKungHittad = true;
            }
        }
        if (!aktuellKungHittad && !inaktuellKungHittad)
            return 0;
        if (aktuellFarg == VIT) {
            if (!aktuellKungHittad) //Vit kung är tagen
                return SVART_VINNER;
            if (!inaktuellKungHittad)
                return VIT_VINNER;
            return poangAktuell - poangInaktuell;
        }
        if (!aktuellKungHittad) //Svart kung är tagen
            return VIT_VINNER;
        if (!inaktuellKungHittad)
            return SVART_VINNER;
        return poangInaktuell - poangAktuell;
    }

    public List<Drag> allaMojligaDrag() throws UtanforBradetException {
        List<Drag> allaDrag = new ArrayList<>();
        for (char i = 'a'; i < 'i'; i++) {
            for (int j = 1; j < 9; j++) {
                Position pos = new Position("" + i + j);
                Optional<Pjas> p = getPjas(pos);
                p.ifPresent(pjas -> allaDrag.addAll(pjas.getMojligaDrag()));
            }
        }
        return allaDrag;
    }


    public Optional<Pjas> getPjas(Position pos) throws UtanforBradetException {
        Pjas valdPjas = null;
        switch (charPa(pos)) {
            case Pjas.CONST_BONDE -> valdPjas = new Bonde(this, pos);
            case Pjas.CONST_DAM -> valdPjas = new Dam(this, pos);
            case Pjas.CONST_KUNG -> valdPjas = new Kung(this, pos);
            case Pjas.CONST_TORN -> valdPjas = new Torn(this, pos);
            case Pjas.CONST_SPRINGARE -> valdPjas = new Springare(this, pos);
        }
        return Optional.ofNullable(valdPjas);
    }


    private int getVarde(char c) {
        c = Character.toUpperCase(c);
        return switch (c) {
            case Pjas.CONST_BONDE -> 1;
            case Pjas.CONST_KUNG -> 2;
            case Pjas.CONST_TORN -> 6;
            case Pjas.CONST_SPRINGARE -> 5;
            case Pjas.CONST_DAM -> 9;
            default -> 0;
        };
    }

    public Position framfor(Position position) throws UtanforBradetException {

        int rad = Integer.parseInt(position.asString().charAt(1) + "");
        char kolumn = position.asString().charAt(0);
        if(aktuellFarg==SVART) {
            rad++;
        } else {
            rad--;
        }
        return new Position(""+kolumn + rad);
    }

    public Position framforVanster(Position position) throws UtanforBradetException {
        int rad = Integer.parseInt(position.asString().charAt(1) + "");
        char kolumn = position.asString().charAt(0);
        if(aktuellFarg==SVART) {
            rad++;
            kolumn++;
        } else {
            rad--;
            kolumn--;
        }
        return new Position(""+kolumn + rad);
    }

    public Position framforHoger(Position position) throws UtanforBradetException {
        int rad = Integer.parseInt(position.asString().charAt(1) + "");
        char kolumn = position.asString().charAt(0);
        if(aktuellFarg==SVART) {
            rad++;
            kolumn--;
        } else {
            rad--;
            kolumn++;
        }
        return new Position(""+kolumn + rad);
    }


    public enum BRADE_INIT_TYP {
        TOMT,
        START,
        BONDER_DAM_KUNG,
        BONDER_DAM_KUNG_TORN,
        TORN_MOT_KUNG,
        INGEN_INIT,
    }
}
