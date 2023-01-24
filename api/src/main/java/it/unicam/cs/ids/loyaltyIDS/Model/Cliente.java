package it.unicam.cs.ids.loyaltyIDS.Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends UtenteGenerico {

    private List<CartaFedelta> carteFedelta;


    public Cliente(String nome, String cognome, String indirizzo,String email, String username, String password, int telefono) {
        super(nome, cognome, indirizzo, email, username, password, telefono);
        this.carteFedelta = new ArrayList<>();
    }

    public List<CartaFedelta> getCarteFedelta() {
        return carteFedelta;
    }
}
