package com.gustafbratt.schack.core;

import com.gustafbratt.schack.minimax.MinMax;
import org.junit.jupiter.api.Test;

import static com.gustafbratt.schack.core.Farg.VIT;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;
import static org.assertj.core.api.Assertions.assertThat;

class MinMaxTest {
    @Test
    public void minmax1() throws UtanforBradetException {
        Brade b = new Brade(Brade.BRADE_INIT_TYP.INGEN_INIT);
        b.rutor[0] = new char[]{'.', '.', '.', 'D', '.', 'k', '.', '.'};
        b.rutor[1] = new char[]{'.', 'b', 'b', '.', 'b', 'b', '.', '.'};
        b.rutor[2] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[3] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[4] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[5] = new char[]{'.', '.', '.', '.', '.', '.', '.', '.'};
        b.rutor[6] = new char[]{'.', '.', '.', '.', 'K', '.', '.', '.'};
        b.rutor[7] = new char[]{'.', '.', '.', 'T', '.', '.', '.', '.'};
        var mm = new MinMax(1000);
        var res = mm.minimax(b, 1, true, 0, 0);
        b.print();
        System.out.println(res.getPoang());
        System.out.println(res.getDrag());
    }

    @Test
    public void spel() throws UtanforBradetException {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        int poang = 0;
        MinMax minMax4 = new MinMax(3);
        MinMax minMax2 = new MinMax(2);
        Drag drag = null;
        while (poang < 4000 && poang > -4000) {
            if (brade.getAktuellFarg() == VIT) {
                drag = minMax4.minimax(brade, 3, true, MIN_VALUE, MAX_VALUE).getDrag();
            } else {
                drag = minMax4.minimax(brade, 2, false, MIN_VALUE, MAX_VALUE).getDrag();
            }
            brade = new Brade(drag);
            brade.print();
            poang = brade.poang();
        }
        System.out.println(brade.getDraghistorik());
        assertThat(brade.getDraghistorik().toString()).isEqualTo("[Be2-e4, Sg8-f6, Dd1-f3, Sf6-e4, Df3-e4, Bh7-h5, Lf1-b5, Th8-h6, Bd2-d4, Th6-b6, Sb1-a3, Tb6-b5, Sa3-b5, Bf7-f5, De4-f5, Be7-e5, Df5-h5, Bg7-g6, Dh5-g6, Ke8-e7, Lc1-g5, Lf8-h6, Lg5-e7]");
    }
}