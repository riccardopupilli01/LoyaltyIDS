package it.unicam.cs.ids.loyaltyIDS.Model;

import java.util.*;

public class CommessoPuntoVendita extends UtenteGenerico {

    private Set<ProgrammaFedelta> programmiFedelta;
    private List<Cliente> listaClienti;

    public CommessoPuntoVendita(String nome, String cognome, String indirizzo, String emailBusiness, String username, String password, int telefono) {
        super(nome, cognome, indirizzo,emailBusiness, username, password, telefono);
        this.programmiFedelta= new HashSet<>();
        this.listaClienti=new ArrayList<>();
    }


}