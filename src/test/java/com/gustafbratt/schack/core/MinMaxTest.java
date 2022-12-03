package com.gustafbratt.schack.core;

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
        Brade b = new Brade(Brade.BRADE_INIT_TYP.INGEN_INIT);
        b.rutor[0] = new char[]{'.', '.', '.', 'D', '.', 'k', '.', '.'};
        b.rutor[1] = new char[]{'.', 'b', 'b', '.', 'b', 'b', '.', '.'};
        b.rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[6] = new char[]{'.', '.', '.', '.', 'K', '.', '.', '.'};
        b.rutor[7] = new char[]{'.', '.', '.', 'T', '.', '.', '.', '.'};
        var mm = new MinMax();
        var res = mm.minimax(b, 1, true, 0, 0);
        b.print();
        System.out.println(res.getPoang());
        System.out.println(res.getDrag());
    }

    @Test
    public void spel() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        int poang = 0;
        MinMax minMax4 = new MinMax();
        MinMax minMax2 = new MinMax();
        Drag drag;
        while (poang < 4000 && poang > -4000) {
            if (brade.getAktuellFarg() == VIT) {
                drag = minMax4.minimax(brade, 3, true, MIN_VALUE, MAX_VALUE).getDrag();
            } else {
                drag = minMax2.minimax(brade, 2, false, MIN_VALUE, MAX_VALUE).getDrag();
            }
            brade = new Brade(drag);
            brade.print();
            poang = brade.poang();
        }
        System.out.println(brade.getDraghistorik());
        assertThat(brade.getDraghistorik().toString()).isEqualTo("[Be2-e4, Sg8-f6, Dd1-f3, Sf6-e4, Df3-e4, Bh7-h5, Lf1-b5, Th8-h6, Bd2-d4, Th6-b6, Sb1-a3, Tb6-b5, Sa3-b5, Bf7-f5, De4-f5, Be7-e5, Df5-h5, Bg7-g6, Dh5-g6, Ke8-e7, Lc1-g5, Lf8-h6, Lg5-e7]");
    }


    @Test
    @Disabled
    /* Vid start 17.5 sekunder. 30 sekunder i energisparläge. 5 sekunder om UtanforBradetException inte spara stack traces
    * Utan a/b-pruning i översta lagret: 37 sekunder
    */
    public void langTestForProfilering() {
        var start = System.currentTimeMillis();
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
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
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
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

}