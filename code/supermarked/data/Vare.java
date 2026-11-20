package supermarked.data;

import java.time.LocalDate;

public class Vare {

    private String ean;
    private String navn;
    private LocalDate prisDato;
    private double pris;
    private String maengde;
    private String enhed;

    public Vare(String ean, String navn, LocalDate prisDato, double pris, String maengde, String enhed) {
        this.ean = ean;
        this.navn = navn;
        this.prisDato = prisDato;
        this.pris = pris;
        this.maengde = maengde;
        this.enhed = enhed;
    }

    public String getEAN() {
        return ean;
    }

    public String getNavn() {
        return navn;
    }

    public LocalDate getPrisDato() {
        return prisDato;
    }

    public double getPris() {
        return pris;
    }

    public String getMaengde() {
        return maengde;
    }

    public String getEnhed() {
        return enhed;
    }
}