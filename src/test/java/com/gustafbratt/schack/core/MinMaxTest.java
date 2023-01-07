package com.gustafbratt.schack.core;

import com.gustafbratt.schack.core.pjas.Drag;
import com.gustafbratt.schack.minimax.MinMax;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.gustafbratt.schack.core.Farg.SVART;
import static com.gustafbratt.schack.core.Farg.VIT;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

class MinMaxTest {
    @Test
    public void minmax1() {
        Brade b = new Brade(StartBraden.INGEN_INIT);
        b.rutor[0] = new char[]{'.', '.', '.', 'D', '.', 'k', '.', '.'};
        b.rutor[1] = new char[]{'.', 'b', 'b', '.', 'b', 'b', '.', '.'};
        b.rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[6] = new char[]{'.', '.', '.', '.', 'K', '.', '.', '.'};
        b.rutor[7] = new char[]{'.', '.', '.', 'T', '.', '.', '.', '.'};
        b.print();
        System.out.println(b.beraknaMojligaDrag());
        var mm = new MinMax();
        var res = MinMax.negaMax(b, 1,-99999, 99999, Farg.VIT);
        b.print();
        System.out.println(res.getPoang());
        System.out.println(res.getDrag());
    }

    @Test
    //Antal poängberäkningar: 312 565 utan ab
    //Antal poängberäkningar: 72 055 med ab utan sortering
    //Antal poängberäkningar: 31 075 med ab med sortering
    public void heltSpel() {
        Brade brade = new Brade(StartBraden.START);
        int poang = 0;
        Drag drag;
        while (poang < 4000 && poang > -4000) {
            if (brade.getAktuellFarg() == VIT) {
                drag = MinMax.negaMax(brade, 3, -99999, 99999, VIT).getDrag();
            } else {
                drag = MinMax.negaMax(brade, 1,-99999, 99999, SVART).getDrag();
            }
            brade = new Brade(drag);
            System.out.println(drag);
            brade.print();
            poang = brade.poang();
        }
        System.out.println(brade.getDraghistorik());
        assertThat(brade.getDraghistorik().toString()).isEqualTo("[Be2-e3, Be7-e5, Dd1-h5, Dd8-h4, Dh5-h4xD, Bd7-d5, Sb1-c3, Lc8-g4, Dh4-g4xL, Bd5-d4, Dg4-c8, Bd4-c3xS, Dc8-e8xK]");
        System.out.println("Antal poängberäkningar: " + Brade.getBerakningar());
    }


    @Test
    @Disabled
    /* Vid start 17.5 sekunder. 30 sekunder i energisparläge. 5 sekunder om UtanforBradetException inte spara stack traces
    * Utan a/b-pruning i översta lagret: 37 sekunder
    */
    public void langTestForProfilering() {
        var start = System.currentTimeMillis();
        Brade brade = new Brade(StartBraden.START);
        int poang = 0;
        Drag drag;
        while (poang < 4000 && poang > -4000) {
            if (brade.getAktuellFarg() == VIT) {
                drag = new MinMax().hittaBastaDrag(brade, VIT, 4);
                //var r = new MinMax().minimax(brade, 4, true, MIN_VALUE, MAX_VALUE);
                //drag = r.getDrag();
            } else {
                drag = new MinMax().hittaBastaDrag(brade, SVART, 3);
                //var r = new MinMax().minimax(brade, 3, false, MIN_VALUE, MAX_VALUE);
                //drag = r.getDrag();
            }
            System.out.println(brade.getAktuellFarg() + " " + drag);
            brade = new Brade(drag);
            brade.print();
            poang = brade.poang();
        }
        System.out.println(brade.getDraghistorik());
        var end = System.currentTimeMillis();

        long tidTagen = (end - start);
        System.out.println("Millisekunder tagna: " + String.format("%,d", tidTagen));
        assertThat(brade.getDraghistorik().toString()).isEqualTo("[Bd2-d4, Be7-e6, Be2-e4, Lf8-b4, Bc2-c3, Lb4-a5, Bb2-b4, Bc7-c5, Bd4-c5, Bh7-h5, Lf1-b5, Sg8-f6, Be4-e5, Sf6-e4, Dd1-d4, Bf7-f5, Lc1-f4, Sb8-c6, Dd4-e3, Dd8-h4, Bg2-g3, Se4-g3, Bf2-g3, Dh4-f4, Bg3-f4, La5-b4, Bc3-b4, Sc6-b4, De3-d2, Sb4-d5, Sb1-c3, Th8-h6, Sc3-d5, Be6-d5, Dd2-d5, Ba7-a6, Dd5-g8, Ke8-e7, Dg8-g7, Ke7-e6, Dg7-h6, Ke6-d5, Lb5-d3, Bd7-d6, Dh6-d6, Bh5-h4, Dd6-d5]");
    }

    @Disabled //För att mäta tid
    @Test
    //Tid före sortering: 17 sekunder
    //Tid efter sortering: 6 sekunder
    public void annuLangreTest() {
        var start = System.currentTimeMillis();
        Brade brade = new Brade(StartBraden.START);
        int poang = 0;
        Drag drag;
        while (poang < 4000 && poang > -4000) {
            if (brade.getAktuellFarg() == VIT) {
                drag = new MinMax().hittaBastaDrag(brade, VIT, 2);
            } else {
                drag = new MinMax().hittaBastaDrag(brade, SVART, 5);
            }
            System.out.println(brade.getAktuellFarg() + " " + drag);
            brade = new Brade(drag);
            brade.print();
            poang = brade.poang();
        }
        System.out.println(brade.getDraghistorik());
        var end = System.currentTimeMillis();

        long tidTagen = (end - start);
        System.out.println("Millisekunder tagna: " + String.format("%,d", tidTagen));
        assertThat(brade.getDraghistorik().toString()).isEqualTo("[Bh2-h4, Be7-e5, Bh4-h5, Dd8-f6, Th1-h3, Lf8-c5, Bg2-g4, Lc5-f2, Th3-h1, Lf2-e1]");
    }

    @Test
    //Djup 4: 1 sekund. 9361 drag
    //Djup 5: 1 sekund. 20 413 drag
    void profilering() {
        Brade b = new Brade(StartBraden.START);
        MinMax.hittaBastaDrag(b, VIT, 4);
        System.out.println("Antal berakningar: " + Brade.getBerakningar());
    }
}