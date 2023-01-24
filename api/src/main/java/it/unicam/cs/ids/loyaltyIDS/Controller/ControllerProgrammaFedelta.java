package it.unicam.cs.ids.loyaltyIDS.Controller;

import it.unicam.cs.ids.loyaltyIDS.DBMSController;
import it.unicam.cs.ids.loyaltyIDS.Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ControllerProgrammaFedelta {

    private List<ProgrammaFedelta> listaProgrammi;

    public ControllerProgrammaFedelta() {
        listaProgrammi= new ArrayList<>();
    }

    public void addProgrammaFedelta(ProgrammaFedelta programFel) throws SQLException {
        listaProgrammi.add(programFel);
        String query="";
        if(programFel instanceof ProgrammaPunti progPunti){
            query = "INSERT INTO programpunti (id_pp, nome_pp, descrizione_pp) VALUES('" + progPunti.getId()+ "','" + progPunti.getNome()+ "','" + progPunti.getDescrizione() +"')";
        }
        if (programFel instanceof ProgrammaLivelli progLivelli){
            query = "INSERT INTO programlivelli (id_pl, nome_pl, descrizione_pl) VALUES('" + progLivelli.getId()+ "','" + progLivelli.getNome()+ "','" + progLivelli.getDescrizione() +"')";
        }
        DBMSController.insertQuery(query);
    }

    public List<ProgrammaFedelta> visualizzaProgrammiPunti() throws SQLException {
        ResultSet resultset1= DBMSController.selectAllFromTable("programpunti");
        while (resultset1.next()){
            ProgrammaFedelta progfel= new ProgrammaPunti(resultset1.getString("nome_pp"),
                    resultset1.getInt("id_pp"));
            if(progfel instanceof ProgrammaFedelta){
                this.listaProgrammi.add(progfel);
            }
        }
        return this.listaProgrammi;
    }

    public List<ProgrammaFedelta> visualizzaProgrammiLivelli() throws SQLException {
         ResultSet resultset = DBMSController.selectAllFromTable("programlivelli");
        while (resultset.next()){
            ProgrammaFedelta proglev= new ProgrammaLivelli(resultset.getString("nome_pl"),
                    resultset.getInt("id_pl"));
            if(proglev instanceof ProgrammaFedelta ){
                this.listaProgrammi.add(proglev);
            }
        }
        return this.listaProgrammi;
    }

    public ProgrammaFedelta findById(int id){
        ProgrammaFedelta programFel=null;
        for (ProgrammaFedelta p: this.listaProgrammi){
            if(p.getId()==id)
                programFel=p;
        }
        if(programFel==null){
            throw new NullPointerException();
        }
        return programFel;
    }

    public boolean deleteById(int id) throws SQLException {
        if(findById(id)==null){
            throw new NullPointerException("programma fedelta non esistente");
        }
        for (ProgrammaFedelta p: this.listaProgrammi){
            if (id== p.getId())
                this.listaProgrammi.remove(p);
            String quary="";
            if(p instanceof ProgrammaPunti pp) {
                quary = "DELETE FROM programpunti WHERE nome_pp='" + pp.getNome() + "'";
            }
            else if(p instanceof ProgrammaLivelli pl) {
                quary = "DELETE FROM programlivelli WHERE nome_pl='" + pl.getNome() + "';";
            }
            DBMSController.removeQuery(quary);
            return true;
        }
        return false;
    }

    public void addProgrammiTitolari(TitolarePuntoVendita t) throws SQLException, DateMistake {
        String query="SELECT nomePuntoVendita FROM programmiTitolari WHERE nomePuntoVendita="+t.getNome()+"";
        if(DBMSController.getNumberRows(query)<listaProgrammi.size()){
            String addQuery="INSERT INTO programmiTitolari()";
            DBMSController.insertQuery(addQuery);
        }else{
            throw new DateMistake("Raggiunto limite massimo dei programmi che puoi aggiungere");
        }
    }


}
