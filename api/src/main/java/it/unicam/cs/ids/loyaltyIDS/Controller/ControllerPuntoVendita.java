package it.unicam.cs.ids.loyaltyIDS.Controller;

import it.unicam.cs.ids.loyaltyIDS.Services.DBMSController;
import it.unicam.cs.ids.loyaltyIDS.Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ControllerPuntoVendita {

    private Set<ProgrammaFedelta> listaProgrammi;
    private List<Cliente> listaClienti;
    private Set<PuntoVendita> listaPuntoVendita;

    private int countPunti;
    private int countLivelli;

    public ControllerPuntoVendita() {
        this.listaProgrammi= new HashSet<>();
        this.listaClienti= new ArrayList<>();
        this.listaPuntoVendita=new HashSet<>();
        countPunti=0;
        countLivelli=0;
    }

    public Set<ProgrammaFedelta> getListaProgrammi() {
        return listaProgrammi;
    }

    public int getCountPunti() {
        return countPunti;
    }

    public int getCountLivelli() {
        return countLivelli;
    }

    /**
     * Metodo che visualizza il programma a
     * punti del titolare della proprio punto vendita
     * @return il set dei programmi a punti
     * @throws SQLException
     */
    public Set<ProgrammaFedelta> visualizzaProgrammiPuntiTitolare(PuntoVendita pv) throws SQLException {
        String table="programmipuntititolari";
        ResultSet resultset= DBMSController.selectAllFromTable(table);
        while (resultset.next()){
            int id_titolare=resultset.getInt("titolari_id");
            if(id_titolare==pv.getTitolare().getId()) {
                ProgrammaFedelta pp = new ProgrammaPunti(resultset.getString("nome_pt"),
                        resultset.getString("descrizione_pt"),
                        resultset.getInt("valorexpunto"), resultset.getInt("totpunti"));
                this.listaProgrammi.add(pp);
                countPunti++;
            }
        }
        return this.listaProgrammi;
    }

    /**
     * Metodo che visualizza il programma a
     * livelli del titolare della proprio punto vendita
     * @return il set dei programmi a punti
     * @throws SQLException
     */
    public Set<ProgrammaFedelta> visualizzaProgrammiLivelliTitolare(PuntoVendita pv) throws SQLException {
        String table="programmilivellititolari";
        ResultSet resultset= DBMSController.selectAllFromTable(table);
        while (resultset.next()){
            int id_titolare=resultset.getInt("titolari_id");
            if(id_titolare==pv.getTitolare().getId()) {
                ProgrammaFedelta pp = new ProgrammaLivelli(resultset.getString("nome_pt"),
                        resultset.getString("descrizione_pt"),
                        resultset.getInt("livellomax"), resultset.getInt("puntitot"),
                        resultset.getInt("livellovip"));
                this.listaProgrammi.add(pp);
                countLivelli++;
            }
        }
        return this.listaProgrammi;
    }

    public void addPuntoVendita(PuntoVendita pv) throws DateMistake, AbilitationException, SQLException {
        for(PuntoVendita p: this.listaPuntoVendita){
            if(findById(pv.getNomePuntoVendita()).equals(p.getNomePuntoVendita())){
                throw new DateMistake("Il punto vendita é gia esistente");
            }
        }
        //if(pv.getTitolare().isAbilitato()){
        String query="INSERT INTO puntovendita (nome_pv, indirizzo_pv, titolariid_t ) VALUES('" + pv.getNomePuntoVendita() + "','" + pv.getIndirizzo() + "','" + pv.getTitolare().getId() + "')";
        DBMSController.insertQuery(query);
        // }
        // else throw new AbilitationException("Il titolare di questo punto vendita non é abilitato");
    }

    public PuntoVendita findById(String nome){
        PuntoVendita puntoVendita = null;
        for (PuntoVendita pv : this.listaPuntoVendita) {
            if (pv.getNomePuntoVendita().equals(nome))
                puntoVendita=pv;
        }
        if (puntoVendita == null) {
            throw new NullPointerException();
        }
        return puntoVendita;
    }

    public Set<PuntoVendita> visualizzaPuntoVendita() throws SQLException {
        String table = "puntovendita";
        ResultSet resultset = DBMSController.selectAllFromTable(table);
        while (resultset.next()) {
            ControllerRegistrazione conn = new ControllerRegistrazione();
            TitolarePuntoVendita titolareDaAggiungere = conn.findById(resultset.getInt("titolariid_t"));
            PuntoVendita puntoVendita = new PuntoVendita(resultset.getString("nome_pv"),
                    resultset.getString("indirizzo_pv"), titolareDaAggiungere);
            this.listaPuntoVendita.add(puntoVendita);
        }
        return this.listaPuntoVendita;
    }

    public int incrementaPuntiCarta(int spesaEffettuata, ProgrammaPunti pp, CartaFedelta cf) throws SQLException {
        int puntiTotalizzati=cf.getPuntiCorrenti()+(spesaEffettuata/pp.getValoreXPunto());
        String query="UPDATE cartafedelta SET punticorrenti ='"+puntiTotalizzati+"'WHERE id_cf= '"+cf.getId()+"'";
        if(puntiTotalizzati>=pp.getTotPunti()){
            //sblocca coupon da ritirare
        }
        DBMSController.insertQuery(query);
        return puntiTotalizzati;
    }

    public int incrementaLivelloCarta(int spesaEffettuata, ProgrammaLivelli pl, CartaFedelta cf) throws SQLException {
        int percentualeDaIncrementare=cf.getPercentualeLivello()+(spesaEffettuata/pl.getValoreXPercentualeLivello());
        if(percentualeDaIncrementare>=pl.getPuntiTot()){
            if(cf.getLivelliCorrenti()<pl.getLivelloMax()){
                int differenza=percentualeDaIncrementare-pl.getPuntiTot();
                int incrementaLivello=cf.getLivelliCorrenti()+1;
                String query="UPDATE cartafedelta SET livellocorrente ='"+incrementaLivello+"', percentualelivello= '"+differenza+"'WHERE id_cf= '"+cf.getId()+"'";
                DBMSController.insertQuery(query);
            }
        }
        return percentualeDaIncrementare;
    }

}