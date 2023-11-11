package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Farg;
import com.gustafbratt.schack.core.UtanforBradetException;

public class Bonde extends Pjas {

    static int[][] varde = new int[8][];

    static {
        varde[0] = new int[]{20, 20, 20, 20, 20, 20, 20, 20};
        varde[1] = new int[]{25, 25, 25, 25, 25, 25, 25, 25};
        varde[2] = new int[]{20, 20, 20, 24, 24, 20, 20, 20};
        varde[3] = new int[]{18, 18, 18, 20, 20, 18, 18, 18};
        varde[4] = new int[]{15, 15, 15, 18, 18, 15, 15, 15};
        varde[5] = new int[]{15, 15, 15, 15, 15, 15, 15, 15};
        varde[6] = new int[]{10, 10, 10, 10, 10, 10, 10, 10};
        varde[7] = new int[]{10, 10, 10, 10, 10, 10, 10, 10};
    }

    public Bonde(Brade brade, String position) {
        super(brade, position);
        char pjasKod = brade.charPa(position);
        if (pjasKod != CONST_BONDE) {
            throw new IllegalStateException("Inte en bonde på position " + position + ". Det är en " + pjasKod);
        }
        this.beraknaMojligaDrag();
    }

    @Override
    public char getChar() {
        return CONST_BONDE;
    }

    public static int getVarde(int rad, int kolumn) {
        return varde[rad][kolumn];
    }

    private void beraknaMojligaDrag() {
        //Flytta ett steg framåt
        try {
            if (brade.charPa(brade.framfor(position)) == '.') {
                Drag drag = new Drag(brade, position, brade.framfor(position), DragTyp.FLYTT);
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
        //Flytta två steg framåt
        if ((brade.getAktuellFarg() == Farg.VIT && getRad(position) == 2) || (brade.getAktuellFarg() == Farg.SVART && getRad(position) == 7)) {
            try {
                if (brade.charPa(brade.framfor(position)) == '.' && brade.charPa(brade.framfor(brade.framfor(position))) == '.') {
                    Drag drag = new Drag(brade, position, brade.framfor(brade.framfor(position)), DragTyp.FLYTT);
                    mojligaDrag.add(drag);
                }
            } catch (UtanforBradetException ignored) {
            }
        }
        //Ta pjäs snett framåt vänster
        try {
            String framforVanster = brade.framforVanster(this.position);
            PositionUtils.validera(framforVanster);
            if (Character.isLowerCase(brade.charPa(framforVanster))) {
                Drag drag = new Drag(brade, this.position, framforVanster, DragTyp.TAGNING);
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
        //Ta pjäs snett framåt höger
        try {
            String framforHoger = brade.framforHoger(this.position);
            PositionUtils.validera(framforHoger);
            if (Character.isLowerCase(brade.charPa(framforHoger))) {
                Drag drag = new Drag(brade, this.position, framforHoger, DragTyp.TAGNING);
                mojligaDrag.add(drag);
            }
        } catch (UtanforBradetException ignored) {
        }
        //En passant
        /*The conditions for a pawn to capture an enemy pawn en passant are as follows:
            the enemy pawn advanced two squares on the previous move;
            the capturing pawn attacks the square that the enemy pawn passed over.
        */
        //Om jag är (vit och på rad 5) eller (svart och på rad 4) och och motståndare just gjorde
        //två steg med en bonde på en av kolumnerna intill min
        //int kolumnRaw = position.charAt(0) - 'a';
        char kolumn = position.charAt(0);
        if (brade.getEnPassantKolumn() != null && (brade.getAktuellFarg() == Farg.VIT && getRad(position) == 5 || (brade.getAktuellFarg() == Farg.SVART && getRad(position) == 4))) {
            String till;
            if (kolumn == brade.getEnPassantKolumn() + 1) {
                till = brade.getAktuellFarg() == Farg.VIT ? brade.framforHoger(position) : brade.framforVanster(position);
                Drag drag = new Drag(brade, position, till, DragTyp.ENPASSANT);
                mojligaDrag.add(drag);
            }
            if (kolumn == brade.getEnPassantKolumn() - 1) {
                till = brade.getAktuellFarg() == Farg.SVART ? brade.framforHoger(position) : brade.framforVanster(position);
                Drag drag = new Drag(brade, position, till, DragTyp.ENPASSANT);
                mojligaDrag.add(drag);
            }
        }
    }

    int getRad(String position) {
        return Integer.parseInt("" + position.charAt(1)); //TODO: wtf?
    }
}
