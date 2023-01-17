package com.gustafbratt.schack;

import com.gustafbratt.schack.core.Brade;
import com.gustafbratt.schack.core.Farg;
import com.gustafbratt.schack.core.StartBraden;
import com.gustafbratt.schack.core.pjas.Drag;
import com.gustafbratt.schack.core.pjas.OgiltigtDragException;
import com.gustafbratt.schack.sokning.IterativeDeepening;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cli {

    Scanner scanner = new Scanner(System.in);
    Brade brade = new Brade(StartBraden.START);

    public static void main(String[] args) throws OgiltigtDragException {
        new Cli().start();
    }

    public void start() throws OgiltigtDragException {
        System.out.println("Nu kör vi");
        int timeout = Integer.parseInt(inputPrompt("Välj timeout"));
        while (true) {
            if (brade.poang() > 3_000) {
                brade.print();
                System.out.println("Vit vinner. Tack för en god match.");
                System.exit(0);
            }
            if (brade.poang() < -3_000) {
                brade.print();
                System.out.println("Svart vinner. Tack för en god match.");
                System.exit(0);
            }
            brade.print();
            if (brade.getAktuellFarg() == Farg.VIT) {
                Drag d = promtaOmDrag();
                System.out.println("Valt drag: " + d);
                brade = new Brade(d);
            } else {
                System.out.println("startar minmax");
                var beraknat = IterativeDeepening.hittaBastaDrag(brade, Farg.SVART, timeout);
                System.out.println("minmax klar: " + beraknat);
                brade = new Brade(beraknat);
            }
        }
    }

    private Drag promtaOmDrag() {
        String till = inputPrompt("Välj mål");
        List<Drag> mojligaDrag = brade.beraknaMojligaDrag().stream().filter(d -> d.getTill().equals(till)).collect(Collectors.toList());
        if (mojligaDrag.size() == 1) {
            return mojligaDrag.get(0);
        }
        if (mojligaDrag.size() == 0) {
            System.out.println("Det går inte att flytta till " + till);
            return promtaOmDrag();
        }
        System.out.println("Det går att komma till " + till + " från " + mojligaDrag.stream().map(Drag::getFran).collect(Collectors.joining(", ")));
        String fran = inputPrompt("Välj från: ");
        Optional<Drag> drag = mojligaDrag.stream().filter(d -> d.getFran().equals(fran)).findFirst();
        if (drag.isPresent()) {
            return drag.get();
        }
        System.out.println("Nej, det går inget vidare. Vi börjar om.");
        return promtaOmDrag();
    }

    private String inputPrompt(String s) {
        System.out.print(s + " >> ");
        return scanner.nextLine();
    }

}
