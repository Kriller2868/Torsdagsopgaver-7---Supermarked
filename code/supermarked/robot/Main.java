package supermarked.robot;

import supermarked.data.Vare;
import supermarked.data.VareIndlaeser;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Vare> alleVarer = VareIndlaeser.indlaes("data/varer.csv");
        Map<String, Vare> tilbudsVarer = VareIndlaeser.indlaes("data/tilbud.csv");

        System.out.println("Antal varer: " + alleVarer.size());
        System.out.println("Antal tilbud: " + tilbudsVarer.size());

        Robot robot = new Robot();
        Collection<Vare> kurv = robot.fyldIKurv(alleVarer.values());

        System.out.println();
        System.out.println("Kurven har " + kurv.size() + " varer:");
        for (Vare vare : kurv) {
            System.out.println(vare.getNavn() + " - " + vare.getPris() + " kr.");
        }

        //AI 32-33 tæller hvor mange af det samme EAN kommer ind i robot call
        Map<String, Integer> antal = new HashMap<>();
        for (Vare vare : kurv) {
            int antalHidtil = antal.getOrDefault(vare.getEAN(), 0);
            antal.put(vare.getEAN(), antalHidtil + 1);
        }

        System.out.println();
        System.out.println("Samlet:");

        double total = 0;

        for (String ean : antal.keySet()) {
            Vare vare = alleVarer.get(ean);
            int stk = antal.get(ean);
            double linjePris = stk * vare.getPris();

            System.out.println(vare.getNavn());
            System.out.printf("  %d × %.2f kr. = %.2f kr.%n", stk, vare.getPris(), linjePris);
            total += linjePris;

            Vare tilbud = tilbudsVarer.get(ean);
            if (tilbud != null) {
                double rabat = stk * (vare.getPris() - tilbud.getPris());
                System.out.printf("  Tilbud: -%.2f kr.%n", rabat);
                total -= rabat;
            }
        }
        System.out.printf("Total: %.2f kr.%n", total);

        double moms = total * 0.20;

        System.out.printf("Heraf moms (25%%): %.2f kr.%n", moms);
    }
}