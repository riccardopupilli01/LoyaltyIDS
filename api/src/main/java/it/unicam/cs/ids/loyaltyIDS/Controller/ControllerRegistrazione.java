package it.unicam.cs.ids.loyaltyIDS.Controller;

import it.unicam.cs.ids.loyaltyIDS.DBMSController;
import it.unicam.cs.ids.loyaltyIDS.Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerRegistrazione {

    private List<Cliente> clienti;

    private List<TitolarePuntoVendita> titolariAttivita;

  private SistemaBanca sistemaBanca;

    public ControllerRegistrazione() {
        this.clienti= new ArrayList<>();
        this.titolariAttivita= new ArrayList<>();
        this.sistemaBanca= new SistemaBanca();
    }

    public void registrazioneTitolare(TitolarePuntoVendita t) throws SQLException {
        if(validazioneDati(t)){
            String query="INSERT INTO titolari (id_t, nome_t, cognome_t, indirizzo_t, email_t, username_t, password_t, abilitato, telefono_t) VALUES('" + t.getId()+ "','" + t.getNome()+ "','" + t.getCognome() +"','" + t.getIndirizzo()+ "','" + t.getEmail()+ "','" + t.getUsername()+ "' ,'" + t.getPassword()+ "' ,'" + t.isAbilitato()+ "', '" + t.getTelefono()+ "' )";
            DBMSController.insertQuery(query);
        }
    }

   public void addTitolare(TitolarePuntoVendita t ) throws SQLException, DateMistake {
            if (sistemaBanca.verificaPagamento(t)==StatoPagamento.PAGATO) {
                if(findById(t.getId())==null){
                String query = "UPDATE titolari SET abilitato = 'true' WHERE id_t = '"+t.getId()+"'";
                DBMSController.insertQuery(query);
                } else new DateMistake("Il titolare é gia presente nella tabella");

            } else {
                throw new DateMistake();
            }
    }

    public TitolarePuntoVendita findById(int id){
        TitolarePuntoVendita titolare=null;
        for (TitolarePuntoVendita t: this.titolariAttivita){
            if(t.getId()==id)
                titolare=t;
        }
        if(titolare==null){
            throw new NullPointerException();
        }
        return titolare;
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
        if (ug.getNome()==null  || ug.getEmail()==null  || ug.getUsername()==null || ug.getPassword()==null){
            return false;
        }
        return true;
    }

    public List<TitolarePuntoVendita> getAllEsercenti() throws SQLException {
        String t= "titolari";
        ResultSet resultset= DBMSController.selectAllFromTable(t);
        while (resultset.next()){
            ControllerPagamento conn= new ControllerPagamento();
            CartaDiCredito daAggiungere= conn.getByID(resultset.getInt("id_cc"));
            TitolarePuntoVendita titolare= new TitolarePuntoVendita(resultset.getString("nome_t"),
                    resultset.getString("cognome_t"), resultset.getString("indirizzo_t"),
                    resultset.getString("email_t"), resultset.getString("username_t"),
                    resultset.getString("password_t"), resultset.getInt("telefono_t"), daAggiungere);
            this.titolariAttivita.add(titolare);
            }
        return titolariAttivita;
    }

}