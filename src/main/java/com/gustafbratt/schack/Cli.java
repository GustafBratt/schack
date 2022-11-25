package com.gustafbratt.schack;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Drag;
import com.gustafbratt.schack.core.Farg;
import com.gustafbratt.schack.core.UtanforBradetException;
import com.gustafbratt.schack.core.pjas.Pjas;
import com.gustafbratt.schack.core.pjas.PositionUtils;
import com.gustafbratt.schack.minimax.MinMax;

import java.util.List;
import java.util.Scanner;

public class Cli {

    Scanner scanner = new Scanner(System.in);
    Brade brade = new Brade(Brade.BRADE_INIT_TYP.TORN_MOT_KUNG);

    public static void main(String[] args) throws UtanforBradetException {
        new Cli().start();
    }

    public void start() throws UtanforBradetException {
        System.out.println("Nu kör vi");
        while (true) {
            if (brade.poang() > 3_000) {
                brade.print();
                System.out.println("Vit vinner. Tack för en god martch.");
                System.exit(0);
            }
            if (brade.poang() < -3_000) {
                brade.print();
                System.out.println("Svart vinner. Tack för en god martch.");
                System.exit(0);
            }
            brade.print();
            if (brade.getAktuellFarg() == Farg.VIT) {
                String start = valjPjas();
                Pjas valdPjas = null;
                if (brade.getPjas(start).isEmpty())
                    continue;
                valdPjas = brade.getPjas(start).get();
                List<Drag> giltigaDrag = valdPjas.getMojligaDrag();
                System.out.println("Giltiga drag: " + giltigaDrag);
                String  till = valjTill(giltigaDrag);
                Drag d = new Drag(brade, start, till);
                brade = brade.utforDrag(d);
            } else {
                System.out.println("startar minmax");
                int startDjup = 8;
                var resultat = new MinMax(startDjup).minimax(brade, startDjup, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                var beraknat = resultat.getDrag();
                System.out.println("minmax klar: " + beraknat);
                System.out.println("Möjlig poäng: " + resultat.getPoang());
                brade = brade.utforDrag(beraknat);
            }
        }
    }

    private String inputPrompt(String s) {
        System.out.print(s + " >>");
        return scanner.nextLine();
    }

    private String valjTill(List<Drag> giltigaDrag) {
        while (true) {
            String prompt = inputPrompt("Välj mål");
            try {
                PositionUtils.validera(prompt);
                if (giltigaDrag.stream().map(Drag::getTill).anyMatch(p -> p.equals(prompt))) {
                    return prompt;
                }
                System.out.println("Inte ett giltigt drag.");
            } catch (UtanforBradetException e) {
                System.out.println("Inte en giltig position: " + prompt);
            }

        }
    }

    private String valjPjas() {
        while (true) {
            String prompt = inputPrompt("Välj pjäs");
            try {
                PositionUtils.validera(prompt);
                return prompt;
            } catch (UtanforBradetException e) {
                System.out.println("Inte en giltig position: " + prompt);
            }
        }
    }
}
