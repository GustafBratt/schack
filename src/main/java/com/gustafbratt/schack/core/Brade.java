package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.gustafbratt.schack.core.Farg.SVART;
import static com.gustafbratt.schack.core.Farg.VIT;
import static com.gustafbratt.schack.core.StartBraden.*;


public class Brade {
    char[][] rutor;
    private Optional<Integer> poang = Optional.empty(); //TODO nullable Integer istället?
    Farg aktuellFarg = VIT;
    private int antalDrag = 0;
    private final ArrayList<Drag> dragHistorik = new ArrayList<>();
    private final ArrayList<Brade> bradeHistorik = new ArrayList<>();

    private boolean vitKungFlyttad = false;
    private boolean svartKungFlyttad = false;
    private boolean tornA1Flyttad = false;
    private boolean tornA8Flyttad = false;
    private boolean tornH1Flyttad = false;
    private boolean tornH8Flyttad = false;
    private Character enPassantKolumn;
    private static long berakningar;

    public Brade(StartBraden typ) {
        rutor = typ.skapaRutor();
        if (typ != START && typ != BONDER_DAM_KUNG_TORN && typ != TIME_FOR_ROCKAD) {
            vitKungFlyttad = true;
            svartKungFlyttad = true;
            tornA1Flyttad = true;
            tornA8Flyttad = true;
            tornH1Flyttad = true;
            tornH8Flyttad = true;
        }
        bradeHistorik.add(this);
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
        bradeHistorik.addAll(gamla.bradeHistorik);
        bradeHistorik.add(this);
        if (drag.getRockadTyp() != null) {
            utforRockad(drag.getRockadTyp());
        } else {
            setPjas(drag.getTill(), Character.toLowerCase(drag.getPjas()));
            setPjas(drag.getFran(), '.');
        }
        if (drag.getDragTyp() == DragTyp.ENPASSANT) {

            if (aktuellFarg == SVART) {
                setPjas("" + gamla.enPassantKolumn + "5", '.');
            } else {
                setPjas("" + gamla.enPassantKolumn + "4", '.');
            }
        }
        uppdateraRockadPjaser(drag);
        uppdateraEnPassant(drag);
    }

    //TODO: lägg till... schack matt? Och patt?
    public SpelStatus getSpelStatus() {
        long antalLikadana = bradeHistorik.stream().filter(b -> b.equals(this)).count();
        if (antalLikadana > 2)
            return SpelStatus.TRE_UPPREPNINGAR;
        return SpelStatus.PAGAR;
    }

    public List<Drag> getDraghistorik() {
        return Collections.unmodifiableList(dragHistorik);
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


    private void uppdateraEnPassant(Drag drag) {
        if (drag.getPjas() == Pjas.CONST_BONDE) {
            if (PositionUtils.getRadRaw(drag.getFran()) == 1 && PositionUtils.getRadRaw(drag.getTill()) == 3) {
                enPassantKolumn = drag.getFran().charAt(0);
            }
            if (PositionUtils.getRadRaw(drag.getFran()) == 6 && PositionUtils.getRadRaw(drag.getTill()) == 4) {
                enPassantKolumn = drag.getFran().charAt(0);
            }
        }
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
        switch (drag.getTill()) {
            case "e1" -> vitKungFlyttad = true;
            case "e8" -> svartKungFlyttad = true;
            case "a1" -> tornA1Flyttad = true;
            case "a8" -> tornA8Flyttad = true;
            case "h1" -> tornH1Flyttad = true;
            case "h8" -> tornH8Flyttad = true;
        }

    }

    void klonaOchFlippa(Brade gamla) {
        rutor = new char[8][8];
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

    private boolean kanGoraMinstEttDrag() {
        for (char i = 'a'; i < 'i'; i++) {
            for (int j = 1; j < 9; j++) {
                String pos = "" + (char) i + j;
                Optional<Pjas> p = getPjas(pos);
                if (p.isPresent()) {
                    if (p.get() instanceof Kung) {
                        if (!isISchack()) {
                            ((Kung) (p.get())).beraknaRokader();
                        }
                    }
                    if (p.get().getMojligaDrag().stream().anyMatch(d -> !d.utfor().kanAktivTaKung()))
                        return true;
                }
            }
        }
        return false;
    }

    //Vit maximerar
    int beraknaPoang() {
        berakningar++;
        //if (this.getMojligaDrag().size() == 0)
        if (!this.kanGoraMinstEttDrag())
            return 0;
        int poang = 0;
        var flippad = klonaOchFlippa();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                poang += getVarde(i, j);
                poang -= flippad.getVarde(i, j);
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

    Optional<List<Drag>> mojligaDrag = Optional.empty();

    public List<Drag> getMojligaDrag() {
        if (mojligaDrag.isEmpty())
            mojligaDrag = Optional.of(beraknaAllaMojligaDrag());
        return mojligaDrag.get();
    }

    private List<Drag> beraknaAllaMojligaDrag() {
        List<Drag> allaDrag = new ArrayList<>();
        for (char i = 'a'; i < 'i'; i++) {
            for (int j = 1; j < 9; j++) {
                String pos = "" + (char) i + j;
                Optional<Pjas> p = getPjas(pos);
                if (p.isPresent()) {
                    if (p.get() instanceof Kung) {
                        if (!isISchack()) {
                            ((Kung) (p.get())).beraknaRokader();
                        }
                    }
                    allaDrag.addAll(p.get()
                            .getMojligaDrag()
                            .stream()
                            .filter(d -> !d.utfor().kanAktivTaKung())
                            .collect(Collectors.toList())
                    );
                }
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

    private int getVarde(int i, int j) {
        var c = rutor[i][j];
        return switch (c) {
            case Pjas.CONST_BONDE -> Bonde.getVarde(i, j);
            case Pjas.CONST_KUNG -> 10_000;
            case Pjas.CONST_TORN -> Torn.getVarde(i, j);
            case Pjas.CONST_SPRINGARE -> Springare.getVarde(i, j);
            case Pjas.CONST_LOPARE -> Lopare.getVarde(i, j);
            case Pjas.CONST_DAM -> Dam.getVarde(i, j);
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
        String nyPosition = "" + kolumn + rad;
        PositionUtils.validera(nyPosition);
        return nyPosition;
    }

    public String framforVanster(String position) {
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

    public String framforHoger(String position) {
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


    public Brade klonaOchFlippa() {
        Brade flippadKlon = new Brade(TOMT);
        flippadKlon.klonaOchFlippa(this);
        return flippadKlon;
    }

    public Character getEnPassantKolumn() {
        return enPassantKolumn;
    }

    public void print() {
        System.out.println(getSpelStatus() + " Antal drag genomförda: " + antalDrag + " " + dragHistorik);
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

    public Drag hittaDrag(String fran, String till) throws OgiltigtDragException {
        Optional<Pjas> pjasOptional = getPjas(fran);
        if (pjasOptional.isEmpty()) {
            throw new OgiltigtDragException();
        }
        Optional<Drag> drag = pjasOptional.get().getMojligaDrag().stream()
                .filter(d -> d.getTill().equals(till))
                .findFirst();
        if (drag.isEmpty()) {
            throw new OgiltigtDragException();
        }
        return drag.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Brade brade = (Brade) o;

        if (vitKungFlyttad != brade.vitKungFlyttad) return false;
        if (svartKungFlyttad != brade.svartKungFlyttad) return false;
        if (tornA1Flyttad != brade.tornA1Flyttad) return false;
        if (tornA8Flyttad != brade.tornA8Flyttad) return false;
        if (tornH1Flyttad != brade.tornH1Flyttad) return false;
        if (tornH8Flyttad != brade.tornH8Flyttad) return false;
        if (!Arrays.deepEquals(rutor, brade.rutor)) return false;
        if (aktuellFarg != brade.aktuellFarg) return false;
        return enPassantKolumn != null ? enPassantKolumn.equals(brade.enPassantKolumn) : brade.enPassantKolumn == null;
    }

    @Override
    public int hashCode() {
        int result = Arrays.deepHashCode(rutor);
        result = 31 * result + aktuellFarg.hashCode();
        result = 31 * result + (vitKungFlyttad ? 1 : 0);
        result = 31 * result + (svartKungFlyttad ? 1 : 0);
        result = 31 * result + (tornA1Flyttad ? 1 : 0);
        result = 31 * result + (tornA8Flyttad ? 1 : 0);
        result = 31 * result + (tornH1Flyttad ? 1 : 0);
        result = 31 * result + (tornH8Flyttad ? 1 : 0);
        result = 31 * result + (enPassantKolumn != null ? enPassantKolumn.hashCode() : 0);
        return result;
    }

    public static long getBerakningar() {
        return berakningar;
    }

    public boolean isISchack() {
        var flippad = klonaOchFlippa();
        return flippad.kanAktivTaKung();
    }

    //En rockad kan inte ta kung.
    public boolean kanAktivTaKung() {
        var passivKungPos = beraknaPassivKungPosition();
        for (char i = 'a'; i < 'i'; i++) {
            for (int j = 1; j < 9; j++) {
                String pos = "" + i + j;
                Optional<Pjas> p = getPjas(pos);
                if (p.isPresent()) {
                    boolean tarKung = p.get().getMojligaDrag().stream().map(Drag::getTill).anyMatch(po -> po.equals(passivKungPos));
                    if (tarKung)
                        return true;
                }
            }
        }
        return false;
    }

    private String beraknaPassivKungPosition() {
        for (char i = 'a'; i < 'i'; i++) {
            for (int j = 1; j < 9; j++) {
                String pos = "" + i + j;
                if (charPa(pos) == 'k')
                    return pos;
            }
        }
        throw new IllegalStateException("Kan inte hitta någon passiv kung!");
    }

    public void rensaCache() {
        mojligaDrag = Optional.empty();
    }
}
