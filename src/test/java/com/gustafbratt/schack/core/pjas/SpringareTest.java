package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.UtanforBradetException;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class SpringareTest {

    @Test
    public void test1() throws UtanforBradetException {
        Brade b = new Brade(Brade.BRADE_INIT_TYP.START);
        b.print();

        {
            Pjas p = b.getPjas("b1").get();
            var drag = p.getMojligaDrag();
            System.out.println(drag);
            assertThat(drag.stream().map(Drag::toString)).containsExactlyInAnyOrder("Sb1-c3", "Sb1-a3");
            drag.sort(Comparator.comparing(Object::toString));
            b = new Brade(drag.get(1));
        }
        {
            b.print();
            Pjas p = b.getPjas("b8").get();
            var drag = p.getMojligaDrag();
            System.out.println(drag);
            assertThat(drag.stream().map(Drag::toString)).containsExactlyInAnyOrder("Sb8-c6", "Sb8-a6");
            drag.sort(Comparator.comparing(Object::toString));
            b = new Brade(drag.get(1));
        }
        {
            b.print();
            Pjas p = b.getPjas("c3").get();
            var drag = p.getMojligaDrag();
            System.out.println(drag);
            assertThat(drag.stream().map(Drag::toString)).containsExactlyInAnyOrder("Sc3-d5", "Sc3-b5", "Sc3-b1", "Sc3-e4", "Sc3-a4");
            drag.sort(Comparator.comparing(Object::toString));
            b = new Brade(drag.get(1));
        }
    }

    @Test
    public void test2() throws UtanforBradetException {
        Brade b = new Brade(Brade.BRADE_INIT_TYP.START);
        b.setPjas("d3", 'S');
        b.print();
        var spring = b.getPjas("d3").get();
        var drag = spring.getMojligaDrag();
        System.out.println(drag);

    }
}