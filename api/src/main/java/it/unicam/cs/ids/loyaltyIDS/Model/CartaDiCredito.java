package it.unicam.cs.ids.loyaltyIDS.Model;

import java.util.Date;

public class CartaDiCredito {

    private int numeroCarta;

    private Date dateScadenza;

    private String CVV;

    private String pin;


    private double saldoCarta;

    public CartaDiCredito(int numeroCarta, Date dateScadenza, String CVV, String pin) throws DateMistake {
        if (pin.length()==5 && CVV.length()==3) {
            this.numeroCarta = numeroCarta;
            this.dateScadenza = dateScadenza;
            this.CVV = CVV;
            this.pin = pin;
            this.saldoCarta=0;
        }
    }


    public void incrementaSaldo(int aggiungiDenaro){
        this.saldoCarta+=aggiungiDenaro;
    }

    public void decrementaSaldo(int togliDenaro){
        this.saldoCarta-=togliDenaro;
    }


    public double getSaldoCarta() {
        return saldoCarta;
    }

    public int getNumeroCarta() {
        return numeroCarta;
    }

    public Date getDateScadenza() {
        return dateScadenza;
    }

    public String getCVV() {
        return CVV;
    }

    public String getPin() {
        return pin;
    }
}