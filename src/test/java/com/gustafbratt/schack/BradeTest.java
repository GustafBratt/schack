package com.gustafbratt.schack;

import org.junit.jupiter.api.Test;

public class BradeTest {
    @Test
    public void print() {
        Brade brade = new Brade(Brade.BRADE_INIT_TYP.START);
        brade.print();
    }
}
