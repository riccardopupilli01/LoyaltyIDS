package it.unicam.cs.ids.loyaltyIDS.Controller;

import it.unicam.cs.ids.loyaltyIDS.DBMSController;
import it.unicam.cs.ids.loyaltyIDS.Model.*;

import java.sql.SQLException;
import java.util.List;

public class ControllerRegistrazione {

    private List<Cliente> clienti;

    private List<TitolarePuntoVendita> titolariAttivita;

    private SistemaBanca sistemaBanca;

    private DBMSController db_controller;



    public void registrazioneTitolari(TitolarePuntoVendita t ) throws SQLException, DateMistake {
        if (this.validazioneDati(t)) {
            if (sistemaBanca.getPagamento()==StatoPagamento.PAGATO) {

                this.titolariAttivita.add(t);
                String query = "INSERT INTO: TITOLARE()";
                DBMSController.insertQuery(query);
            } else {
                throw new DateMistake();
            }
        }else {
            throw new NullPointerException();
        }
    }

    /**
     * metodo per controllare se i dati inseriti sono corretti
     *
     * @param ug
     *
     * @return true se i dati sono corretti, false altrimenti.
     *
     */
    private boolean validazioneDati(UtenteGenerico ug){
        if (ug.getNome()==null || ug.getIndirizzo()==null || ug.getEmail()==null || ug.getTelefono()==null || ug.getUsername()==null || ug.getPassword()==null){
            return false;
        }
        return true;
    }

    public void visualizzaTitolari() throws SQLException {
        String t= "titolari";
        DBMSController.selectAllFromTable(t);
    }

}