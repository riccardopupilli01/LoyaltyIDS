package it.unicam.cs.ids.loyaltyIDS.Model;

import it.unicam.cs.ids.loyaltyIDS.Controller.ControllerProgrammaFedelta;
import it.unicam.cs.ids.loyaltyIDS.Controller.ControllerRegistrazione;

import java.sql.SQLException;

public class TitolarePuntoVendita extends UtenteGenerico{


    private ControllerRegistrazione effettuaRegistrazione;

    private ControllerProgrammaFedelta creaProgrammaFedelta;

    private boolean abilitato;
    private CartaDiCredito carta;

    public TitolarePuntoVendita(String nome, String cognome, String indirizzo, String emailBusiness, String username, String password, int telefono, CartaDiCredito carta) {
        super(nome, cognome, indirizzo, emailBusiness, username, password, telefono);
        this.carta = carta;
        this.abilitato=false;
        this.effettuaRegistrazione= new ControllerRegistrazione();
        this.creaProgrammaFedelta= new ControllerProgrammaFedelta();
    }
    public CartaDiCredito getCarta() {
        return carta;
    }

    public boolean isAbilitato() {
        return abilitato;
    }

    public void effettuaPagamento() throws DateMistake, SQLException {
        effettuaRegistrazione.addTitolare(this);
        abilitato=true;
    }

    public void aggiungiProgrammaFedeltaPuntoVendita(int id) throws AbilitationException, SQLException, DateMistake {
        if(abilitato){
            this.creaProgrammaFedelta.addProgrammiTitolari(this, id);
        }else{
            throw new AbilitationException("Esercente non abilitato alla piattaforma");
        }
    }





}
