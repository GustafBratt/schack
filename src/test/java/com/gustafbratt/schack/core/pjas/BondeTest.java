package com.gustafbratt.schack.core.pjas;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.StartBraden;
import com.gustafbratt.schack.core.UtanforBradetException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.gustafbratt.schack.core.StartBraden.TORN_MOT_KUNG;
import static org.assertj.core.api.Assertions.assertThat;

class BondeTest {
    @Test
    void hogerKant() {
        List<Drag> mojligaDrag = skapaBrade("h2", "g3", 'l');
        assertThat(mojligaDrag).hasSize(3);
    }

    @Test
    void lopareFramfor() {
        List<Drag> mojligaDrag = skapaBrade("g2", "g3", 'l');
        assertThat(mojligaDrag).hasSize(0);
    }

    @Test
    void lopareTvaStegFramfor() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("g2", "g4", 'l');
        assertThat(mojligaDrag).hasSize(1);
    }

    @Test
    void vidMotstandarLinje() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("d6", "d6", 'B');
        assertThat(mojligaDrag).hasSize(2);
    }

    @Test
    void sammaFramfor() throws UtanforBradetException {
        List<Drag> mojligaDrag = skapaBrade("c2", "c3", 'D');
        assertThat(mojligaDrag).hasSize(0);
    }

    @Test
    void hogerKantSvart() throws OgiltigtDragException {
        Brade brade = new Brade(StartBraden.START);
        brade.print();
        var drag = brade.hittaDrag("a2", "a3");
        brade = new Brade(drag);
        brade.print();
        Bonde bonde = new Bonde(brade, "g7");
        List<Drag> mojligaDrag = bonde.getMojligaDrag();
        System.out.println(mojligaDrag);
        assertThat(mojligaDrag).hasSize(2);
        assertThat(mojligaDrag.stream().map(Drag::toString)).contains("Bg7-g6", "Bg7-g5");
    }

    @Test
    public void langtFram() {
        Brade brade = new Brade(StartBraden.TOMT);
        brade.setPjas("a8", 'B');
        brade.setPjas("e8", 'B');
        brade.setPjas("h8", 'B');
        brade.print();
        assertThat(brade.beraknaMojligaDrag()).isEmpty();
    }

    @Test
    public void enPassantSvart() throws OgiltigtDragException {
        Brade brade = new Brade(TORN_MOT_KUNG);
        brade.setPjas("c2", 'B');
        brade.setPjas("d4", 'b');
        brade.print();
        var allaDrag = brade.beraknaMojligaDrag();
        System.out.println(allaDrag);
        Drag forstaBonden = brade.hittaDrag("c2", "c4");
        brade = forstaBonden.utfor();
        brade.print();
        assertThat(brade.getEnPassantKolumn()).isEqualTo('c');
        allaDrag = brade.beraknaMojligaDrag();
        System.out.println(allaDrag);
        Drag andraBonden = brade.hittaDrag("d4", "c3");
        brade = andraBonden.utfor();
        brade.print();
        assertThat(brade.charPa("c4")).isEqualTo('.');
        assertThat(brade.charPa("c3")).isEqualTo('b');
    }

    @Test
    public void enPassantVIT() throws OgiltigtDragException {
        Brade brade = new Brade(TORN_MOT_KUNG);
        brade = brade.klonaOchFlippa();
        brade.setPjas("c7", 'B');
        brade.setPjas("d5", 'b');
        brade.print();
        brade = brade.hittaDrag("c7", "c5").utfor();
        brade.print();
        List<Drag> mojligaDrag = brade.beraknaMojligaDrag();
        System.out.println(mojligaDrag);
        brade = brade.hittaDrag("d5", "c6").utfor();
        brade.print();
        assertThat(brade.charPa("c5")).isEqualTo('.');
        assertThat(brade.charPa("c6")).isEqualTo('b');
    }

    @Test
    public void inteEnPassant() throws OgiltigtDragException {
        Brade brade = new Brade(TORN_MOT_KUNG);
        brade.setPjas("c2", 'B');
        brade.setPjas("d4", 'b');
        brade.print();
        var allaDrag = brade.beraknaMojligaDrag();
        System.out.println(allaDrag);
        Drag forstaBonden = brade.hittaDrag("c2", "c3");
        brade = forstaBonden.utfor();
        brade.print();
        assertThat(brade.getEnPassantKolumn()).isNull();

    }


    private List<Drag> skapaBrade(String bondePos, String extraPjasPos, char extraPjasTyp) {
        Brade brade = new Brade(StartBraden.START);
        brade.setPjas(extraPjasPos, extraPjasTyp);
        brade.print();
        Bonde bonde = new Bonde(brade, bondePos);
        List<Drag> mojligaDrag = bonde.getMojligaDrag();
        System.out.println("Möjliga drag:" + mojligaDrag);
        return mojligaDrag;
    }

}