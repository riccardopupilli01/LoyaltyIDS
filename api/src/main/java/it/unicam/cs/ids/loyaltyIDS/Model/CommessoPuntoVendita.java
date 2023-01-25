package it.unicam.cs.ids.loyaltyIDS.Model;


public class CommessoPuntoVendita extends UtenteGenerico {

   private PuntoVendita pv;

    public CommessoPuntoVendita(String nome, String cognome, String indirizzo, String emailBusiness, String username, String password, int telefono, PuntoVendita pv) {
        super(nome, cognome, indirizzo,emailBusiness, username, password, telefono);
        this.pv=pv;
    }

    public PuntoVendita getPv() {
        return pv;
    }
}