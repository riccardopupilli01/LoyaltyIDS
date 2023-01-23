package it.unicam.cs.ids.loyaltyIDS.Controller;

import it.unicam.cs.ids.loyaltyIDS.DBMSController;
import it.unicam.cs.ids.loyaltyIDS.Model.*;

import java.util.ArrayList;
import java.util.List;

public class ControllerCarta {

    private DBMSController db_controller;

    private List<CartaFedelta> cartaFedeltaList;

    private CommessoPuntoVendita puntoVendita;

    public ControllerCarta(CommessoPuntoVendita puntoVendita) {
        this.cartaFedeltaList = new ArrayList<>();
        this.puntoVendita=puntoVendita;
    }

    public void addCarta(Cliente cliente) throws DateMistake {
        for (CartaFedelta cf : cliente.getCarteFedelta()) {
            if (cf.getCartaPuntoVendita()==puntoVendita){
                throw new DateMistake();
            }
        }
        CartaFedelta cartaFedelta = new CartaFedelta(this.puntoVendita.getNome(), this.puntoVendita, cliente);
        cliente.getCarteFedelta().add(cartaFedelta);

    }

    public CartaFedelta findById(int id) {
        CartaFedelta cartaFedelta=null;
        for (CartaFedelta p: this.cartaFedeltaList){
            if(p.getId()==id)
                cartaFedelta=p;
        }
        if(cartaFedelta==null){
            throw new NullPointerException();
        }
        return cartaFedelta;
    }

    /**
     * aggiungi database (scelte)
     public void confermaScelte(){
     }
     */
}