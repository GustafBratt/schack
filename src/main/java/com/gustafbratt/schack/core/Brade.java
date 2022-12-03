package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.gustafbratt.schack.core.Farg.VIT;


public class Brade {
    final char[][] rutor = new char[8][8];
    private Optional<Integer> poang = Optional.empty(); //TODO nullable Integer istället?
    Farg aktuellFarg = VIT;
    private int antalDrag = 0;
    private final ArrayList<Drag> dragHistorik = new ArrayList<>();

    private boolean vitKungFlyttad = false;
    private boolean svartKungFlyttad = false;
    private boolean tornA1Flyttad = false;
    private boolean tornA8Flyttad = false;
    private boolean tornH1Flyttad = false;
    private boolean tornH8Flyttad = false;

    public Brade(BRADE_INIT_TYP typ) {
        if (typ == BRADE_INIT_TYP.INGEN_INIT) {
            return;
        }
        if (typ == BRADE_INIT_TYP.TOMT) {
            vitKungFlyttad = true;
            svartKungFlyttad = true;
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
            vitKungFlyttad = true;
            svartKungFlyttad = true;
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
        if (typ == BRADE_INIT_TYP.TIME_FOR_ROCKAD) {
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
        if (typ == BRADE_INIT_TYP.TORN_MOT_KUNG) {
            rutor[0] = new char[]{'.', '.', '.', '.', '.', 'k', '.', '.'};
            rutor[1] = new char[]{'.', '.', '.', 's', 'l', '.', '.', '.'};
            rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            rutor[6] = new char[]{'.', '.', '.', '.', 'K', '.', '.', '.'};
            rutor[7] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
            return;
        }
        rutor[0] = new char[]{'t', 's', 'l', 'd', 'k', 'l', 's', 't'}; //1 Svart
        rutor[1] = new char[]{'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}; //2
        rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'}; //3
        rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'}; //4
        rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'}; //5
        rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'}; //6
        rutor[6] = new char[]{'B', 'B', 'B', 'B', 'B', 'B', 'B', 'B'}; //7
        rutor[7] = new char[]{'T', 'S', 'L', 'D', 'K', 'L', 'S', 'T'}; //8  Vit
    }

    public List<Drag> getDraghistorik() {
        return Collections.unmodifiableList(dragHistorik);
    }

    public void print() {
        System.out.println("Antal drag genomförda: " + antalDrag);
        if (aktuellFarg == VIT) {
            System.out.println("  a b c d e f g h  " + aktuellFarg + "s drag. Poäng: " + poang());
            for (int i = 0; i < 8; i++) {
                //System.out.print(i + 1 + "|");
                System.out.print(8 - i + "|");
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
            //System.out.print(8 - i + "|");
            System.out.print(i + 1 + "|");
            for (int j = 0; j < 8; j++) {
                System.out.print(rutor[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("  h g f e d c b a");

    }

    public char charPa(String position) {
        int kolumnRaw = position.charAt(0) - 'a';
        int radRaw = Integer.parseInt(position.charAt(1) + "") - 1;
        if (aktuellFarg == VIT) {
            radRaw = 7 - radRaw;
        } else {
            kolumnRaw = 7 - kolumnRaw;
        }
        return rutor[radRaw][kolumnRaw];
    }

    public void setPjas(String position, char c) {
        int kolumnRaw = position.charAt(0) - 'a';
        int radRaw = Integer.parseInt(position.charAt(1) + "") - 1;
        if (aktuellFarg == VIT) {
            radRaw = 7 - radRaw;
        } else {
            kolumnRaw = 7 - kolumnRaw;
        }
        rutor[radRaw][kolumnRaw] = c;
    }

    public Brade(Drag drag) {
        Brade gamla = drag.getBradeFran();
        drag.setBradeTill(this);
        if (!Character.isUpperCase(gamla.charPa(drag.getFran()))) { //TODO: den här behövs nog inte. Men bra i dev/test
            throw new IllegalStateException("Inte en aktiv pjäs på " + drag.getFran() + ". Det är en " + gamla.charPa(drag.getFran()));
        }
        klonaOchFlippa(gamla);
        antalDrag = gamla.antalDrag + 1;
        dragHistorik.addAll(gamla.dragHistorik);
        dragHistorik.add(drag);
        if (drag.rockadTyp != null) {
            utforRockad(drag.rockadTyp);
        } else {
            setPjas(drag.getTill(), Character.toLowerCase(drag.getPjas()));
            setPjas(drag.getFran(), '.');
        }
        uppdateraRockadPjaser(drag);
    }

    private void utforRockad(RockadTyp rockadTyp) {
        setPjas(rockadTyp.kungFran(), '.');
        setPjas(rockadTyp.kungTill(), Character.toLowerCase(Pjas.CONST_KUNG));
        setPjas(rockadTyp.tornFran(), '.');
        setPjas(rockadTyp.tornTill(), Character.toLowerCase(Pjas.CONST_TORN));
    }

    private void uppdateraRockadPjaser(Drag drag) {
        switch (drag.getFran()) {
            case "e1" -> vitKungFlyttad = true;
            case "e8" -> svartKungFlyttad = true;
            case "a1" -> tornA1Flyttad = true;
            case "a8" -> tornA8Flyttad = true;
            case "h1" -> tornH1Flyttad = true;
            case "h8" -> tornH8Flyttad = true;
        }

    }

    void klonaOchFlippa(Brade gamla) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                rutor[i][j] = bytSpelare(gamla.rutor[7 - i][7 - j]);
            }
        }
        aktuellFarg = gamla.aktuellFarg.andra();
        vitKungFlyttad = gamla.vitKungFlyttad;
        svartKungFlyttad = gamla.svartKungFlyttad;
        tornA8Flyttad = gamla.tornA8Flyttad;
        tornA1Flyttad = gamla.tornA1Flyttad;
        tornH1Flyttad = gamla.tornH1Flyttad;
        tornH8Flyttad = gamla.tornH8Flyttad;
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
        if (poang.isEmpty()) {
            poang = Optional.of(beraknaPoang());
        }
        return poang.get();
    }

    //Vit maximerar
    int beraknaPoang() {
        int poang = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Character.isUpperCase(rutor[i][j])) {
                    poang += 8 - i;
                    poang += getVarde(rutor[i][j]);
                }
                if (Character.isLowerCase(rutor[i][j])) {
                    poang -= i + 1;
                    poang -= getVarde(rutor[i][j]);
                }
            }
        }
        if (poang > 5_000) { //Vit vill vinna så fort som möjligt, ha så hög poäng som möjligt
            poang = 5_000 - antalDrag;
        }
        if (poang < -5_000) { //Svart vill ha så lite poäng som möjligt. Addera därför antal drag.
            poang = -5_000 + antalDrag;
        }
        if (aktuellFarg == VIT)
            return poang;
        return -poang;
    }

    public List<Drag> beraknaMojligaDrag() {
        List<Drag> allaDrag = new ArrayList<>();
        for (char i = 'a'; i < 'i'; i++) {
            for (int j = 1; j < 9; j++) {
                String pos = "" + i + j;
                Optional<Pjas> p = getPjas(pos);
                p.ifPresent(pjas -> allaDrag.addAll(pjas.getMojligaDrag()));
            }
        }
        return allaDrag;
    }


    public Optional<Pjas> getPjas(String pos) {
        Pjas valdPjas = null;
        switch (charPa(pos)) {
            case Pjas.CONST_BONDE -> valdPjas = new Bonde(this, pos);
            case Pjas.CONST_DAM -> valdPjas = new Dam(this, pos);
            case Pjas.CONST_KUNG -> valdPjas = new Kung(this, pos);
            case Pjas.CONST_TORN -> valdPjas = new Torn(this, pos);
            case Pjas.CONST_SPRINGARE -> valdPjas = new Springare(this, pos);
            case Pjas.CONST_LOPARE -> valdPjas = new Lopare(this, pos);
        }
        return Optional.ofNullable(valdPjas);
    }


    //TODO hitta bättre värden
    private int getVarde(char c) {
        c = Character.toUpperCase(c);
        return switch (c) {
            case Pjas.CONST_BONDE -> 1;
            case Pjas.CONST_KUNG -> 10_000;
            case Pjas.CONST_TORN -> 6;
            case Pjas.CONST_SPRINGARE -> 5;
            case Pjas.CONST_LOPARE -> 4;
            case Pjas.CONST_DAM -> 9;
            default -> 0;
        };
    }

    public String framfor(String position) throws UtanforBradetException {
        int rad = Integer.parseInt(position.charAt(1) + "");
        char kolumn = position.charAt(0);
        if (aktuellFarg == VIT) {
            rad++;
        } else {
            rad--;
        }
        return "" + kolumn + rad;
    }

    public String framforVanster(String position) throws UtanforBradetException {
        int rad = Integer.parseInt(position.charAt(1) + "");
        char kolumn = position.charAt(0);
        if (aktuellFarg == VIT) {
            rad++;
            kolumn++;
        } else {
            rad--;
            kolumn--;
        }
        return "" + kolumn + rad;
    }

    public String framforHoger(String position) throws UtanforBradetException {
        int rad = Integer.parseInt(position.charAt(1) + "");
        char kolumn = position.charAt(0);
        if (aktuellFarg == VIT) {
            rad++;
            kolumn--;
        } else {
            rad--;
            kolumn++;
        }
        return "" + kolumn + rad;
    }


    public boolean isTornA1Flyttad() {
        return tornA1Flyttad;
    }

    public boolean isTornA8Flyttad() {
        return tornA8Flyttad;
    }

    public boolean isTornH1Flyttad() {
        return tornH1Flyttad;
    }

    public boolean isTornH8Flyttad() {
        return tornH8Flyttad;
    }

    public boolean isVitKungFlyttad() {
        return vitKungFlyttad;
    }

    public boolean isSvartKungFlyttad() {
        return svartKungFlyttad;
    }

    @Deprecated //Vad fan ska man använda då?
    public Brade klonaOchFlippa() {
        Brade b2 = new Brade(BRADE_INIT_TYP.TOMT);
        b2.klonaOchFlippa(this);
        return b2;
    }

    public enum BRADE_INIT_TYP {
        TOMT,
        START,
        BONDER_DAM_KUNG,
        BONDER_DAM_KUNG_TORN,
        TORN_MOT_KUNG,
        TIME_FOR_ROCKAD,
        INGEN_INIT,
    }
}
