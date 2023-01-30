package it.unicam.cs.ids.loyaltyIDS.Controller;

import it.unicam.cs.ids.loyaltyIDS.Services.DBMSController;
import it.unicam.cs.ids.loyaltyIDS.Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ControllerProgrammaFedelta {

    private List<ProgrammaFedelta> listaProgrammi;

    public ControllerProgrammaFedelta() {
        listaProgrammi = new ArrayList<>();
    }

    public List<ProgrammaFedelta> getListaProgrammi() {
        return listaProgrammi;
    }

    public void addProgrammaFedelta(ProgrammaFedelta programFel) throws SQLException {
        listaProgrammi.add(programFel);
        String query = "";
        if (programFel instanceof ProgrammaPunti progPunti) {
            query = "INSERT INTO programpunti (id_pp, nome_pp, descrizione_pp, valorexpunto, totpunti ) VALUES('" + progPunti.getId() + "','" + progPunti.getNome() + "','" + progPunti.getDescrizione() + "', '" + progPunti.getValoreXPunto() + "', '" + progPunti.getTotPunti() + "')";
        }
        if (programFel instanceof ProgrammaLivelli progLivelli) {
            query = "INSERT INTO programlivelli (id_pl, nome_pl, descrizione_pl, livellomax, puntitot_pl, valorexpercentualelivello ) VALUES('" + progLivelli.getId() + "','" + progLivelli.getNome() + "','" + progLivelli.getDescrizione() + "', '" + progLivelli.getLivelloMax() + "', '" + progLivelli.getPuntiTot() + "', '" + progLivelli.getValoreXPercentualeLivello() + "')";
        }
        DBMSController.insertQuery(query);
    }

    public List<ProgrammaFedelta> visualizzaProgrammiPunti() throws SQLException {
        ResultSet resultset1 = DBMSController.selectAllFromTable("programpunti");
        while (resultset1.next()) {
            ProgrammaFedelta progfel = new ProgrammaPunti(resultset1.getString("nome_pp"),
                    resultset1.getInt("id_pp"));

            this.listaProgrammi.add(progfel);

        }
        return this.listaProgrammi;
    }

    public List<ProgrammaFedelta> visualizzaProgrammiLivelli() throws SQLException {
        ResultSet resultset = DBMSController.selectAllFromTable("programlivelli");
        while (resultset.next()) {
            ProgrammaFedelta proglev = new ProgrammaLivelli(resultset.getString("nome_pl"),
                    resultset.getInt("id_pl"));

            this.listaProgrammi.add(proglev);

        }
        return this.listaProgrammi;
    }

    public ProgrammaFedelta findById(int id) {
        ProgrammaFedelta programFel = null;
        for (ProgrammaFedelta p : this.listaProgrammi) {
            if (p.getId() == id)
                programFel = p;
        }
        if (programFel == null) {
            throw new NullPointerException();
        }
        return programFel;
    }

    public boolean deleteById(int id) throws SQLException {
        if (findById(id) == null) {
            throw new NullPointerException("programma fedelta non esistente");
        }
        for (ProgrammaFedelta p : this.listaProgrammi) {
            if (id == p.getId())
                this.listaProgrammi.remove(p);
            String query = "";
            if (p instanceof ProgrammaPunti pp) {
                query = "DELETE FROM programpunti WHERE nome_pp='" + pp.getNome() + "'";
            } else if (p instanceof ProgrammaLivelli pl) {
                query = "DELETE FROM programlivelli WHERE nome_pl='" + pl.getNome() + "';";
            }
            DBMSController.removeQuery(query);
            return true;
        }
        return false;
    }

    public void addProgrammiTitolari(TitolarePuntoVendita t, int id) throws SQLException, DateMistake {
        if (findById(id) != null) {
            if (findById(id) instanceof ProgrammaPunti pp) {
                String query = "UPDATE programpunti SET valoreXPunto = '" + pp.getValoreXPunto() + "',TotPunti = '" + pp.getTotPunti() + "', titolari_id ='" + t.getId() + "' WHERE id_pp = '" + pp.getId() + "'";
                DBMSController.insertQuery(query);
            } else if (findById(id) instanceof ProgrammaLivelli pl) {
                String query = "UPDATE programlivelli SET livelloMax = '" + pl.getLivelloMax() + "',puntiTot = '" + pl.getPuntiTot() + "', titolari_id ='" + t.getId() + "' WHERE id_pl = '" + pl.getId() + "'";
                DBMSController.insertQuery(query);
            }
        } throw new DateMistake();
    }

    @Override
    public String toString() {
        String string ="";
        for (ProgrammaFedelta pf : listaProgrammi ){
            string+= "id: "+pf.getId()+" /n" +
                    "nome: "+pf.getNome()+" /n" +
                    "descrizione"+pf.getDescrizione()+"";
        }
        return string;
    }

}