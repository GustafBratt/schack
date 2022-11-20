package com.gustafbratt.schack.core;

import com.gustafbratt.schack.minimax.MinMax;
import org.junit.jupiter.api.Test;

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

    
}