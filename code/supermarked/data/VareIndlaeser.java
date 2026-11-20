package supermarked.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VareIndlaeser {

    public static Map<String, Vare> indlaes(String filnavn) throws IOException {
        Map<String, Vare> varer = new HashMap<>();

        // læser alle linjer i filen
        List<String> line = Files.readAllLines(Path.of(filnavn));

        // starter ved 1 så man springer overskriftslinjen over
        for (int i = 1; i < line.size(); i++) {
            String[] felter = line.get(i).split(";");

            String ean = felter[0];
            String navn = felter[1];
            LocalDate prisDato = LocalDate.parse(felter[2]);
            double pris = Double.parseDouble(felter[3]);
            String maengde = felter[5];
            String enhed = felter[6];

            Vare vare = new Vare(ean, navn, prisDato, pris, maengde, enhed);
            varer.put(ean, vare);
        }

        return varer;
    }
}
