package com.gustafbratt.schack;

import com.gustafbratt.schack.core.*;
import com.gustafbratt.schack.core.minimax.MinMax;
import com.gustafbratt.schack.core.pjas.Bonde;
import com.gustafbratt.schack.core.pjas.Dam;
import com.gustafbratt.schack.core.pjas.Kung;
import com.gustafbratt.schack.core.pjas.Pjas;

import java.util.List;
import java.util.Scanner;

public class Cli {

    Scanner scanner = new Scanner(System.in);
    Brade brade = new Brade(Brade.BRADE_INIT_TYP.BONDER_DAM_KUNG);

    public static void main(String[] args) throws UtanforBradetException {
        new Cli().start();
    }

    public void start() throws UtanforBradetException {
        System.out.println("Nu kör vi");
        Drag senasteDrag = null;
        while (true) {
            brade.print();

            if (brade.getAktuellFarg() == Farg.VIT) {
                Position start = valjPjas();
                Pjas valdPjas = null;
                switch (brade.charPa(start)) {
                    case Pjas.CONST_BONDE -> valdPjas = new Bonde(brade, start);
                    case Pjas.CONST_DAM -> valdPjas = new Dam(brade, start);
                    case Pjas.CONST_KUNG -> valdPjas = new Kung(brade, start);
                    default -> System.out.println("Ingen vettig pjäs på " + start);
                }
                if (valdPjas == null)
                    continue;
                List<Drag> giltigaDrag = valdPjas.getMojligaDrag();
                System.out.println("Giltiga drag: " + giltigaDrag);
                Position till = valjTill(giltigaDrag);
                Drag d = new Drag(brade, start, till);
                brade = brade.utforDrag(d);
                senasteDrag = d;
            } else {
                System.out.println("startar minmax");
                int startDjup = 5;
                var beraknat = new MinMax(startDjup).minimax(brade, startDjup,  false).getDrag();
                System.out.println("minmax klar: " + beraknat);
                brade = brade.utforDrag(beraknat);
            }
        }
    }

    private String inputPrompt(String s) {
        System.out.print(s + " >>");
        return scanner.nextLine();
    }

    private Position valjTill(List<Drag> giltigaDrag) {
        while (true) {
            String prompt = inputPrompt("Välj mål");
            try {
                var till = new Position(prompt);
                if (giltigaDrag.stream().map(Drag::getTill).anyMatch(p -> p.equals(till))) {
                    return till;
                }
                System.out.println("Inte ett giltigt drag.");
            } catch (UtanforBradetException e) {
                System.out.println("Inte en giltig position: " + prompt);
            }

        }
    }

    private Position valjPjas() {
        while (true) {
            String prompt = inputPrompt("Välj pjäs");
            try {
                return new Position(prompt);
            } catch (UtanforBradetException e) {
                System.out.println("Inte en giltig position: " + prompt);
            }
        }
    }
}
