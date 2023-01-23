package it.unicam.cs.ids.loyaltyIDS.Model;

import java.util.Date;

public class CartaFedelta {

    private int id;

    private String nomeCarta;

    private Date scadenza;

    private final CommessoPuntoVendita cartaPuntoVendita;

    private Cliente cliente;



    public CartaFedelta(String nomeCarta, Date scadenza, CommessoPuntoVendita cartaPuntoVendita, Cliente cliente) {
        this.cartaPuntoVendita = cartaPuntoVendita;
        this.cliente=cliente;
        this.id=randomInt();
        this.nomeCarta = nomeCarta;
        this.scadenza = scadenza;
    }

    public CartaFedelta(String nomeCarta, CommessoPuntoVendita cartaPuntoVendita, Cliente cliente) {
        this.id=randomInt();
        this.nomeCarta = nomeCarta;
        this.cartaPuntoVendita = cartaPuntoVendita;
        this.cliente = cliente;
    }

    private int randomInt(){
        double doubleRandom=Math.random()*6000;
        int intRandom= (int) doubleRandom;
        return intRandom;
    }

    public int  getId() {
        return id;
    }

    public String getNomeCarta() {
        return nomeCarta;
    }

    public void setNomeCarta(String nomeCarta) {
        this.nomeCarta = nomeCarta;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public CommessoPuntoVendita getCartaPuntoVendita() {
        return cartaPuntoVendita;
    }
}