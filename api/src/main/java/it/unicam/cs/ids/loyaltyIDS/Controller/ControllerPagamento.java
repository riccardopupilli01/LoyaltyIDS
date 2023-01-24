package it.unicam.cs.ids.loyaltyIDS.Controller;

import it.unicam.cs.ids.loyaltyIDS.DBMSController;
import it.unicam.cs.ids.loyaltyIDS.Model.CartaDiCredito;
import it.unicam.cs.ids.loyaltyIDS.Model.GestorePiattaforma;
import it.unicam.cs.ids.loyaltyIDS.Model.StatoPagamento;
import it.unicam.cs.ids.loyaltyIDS.Model.TitolarePuntoVendita;

public class ControllerPagamento {

    private TitolarePuntoVendita titolarePuntoVendita;

    private DBMSController db_controller;

    public ControllerPagamento() {
    }

    /**
     * se il pagamento è stato effettuato correttamente
     *
     * @return true , altrimenti false;
     */

    public boolean payment(TitolarePuntoVendita t) {
        if (t.getCarta().getSaldoCarta()>GestorePiattaforma.getCostoAdesionePiattaforma()){
            t.getCarta().decrementaSaldo(GestorePiattaforma.getCostoAdesionePiattaforma());
            return true;
        }
        return false;
    }

    public CartaDiCredito getByID(int id){
        if(id==titolarePuntoVendita.getCarta().getNumeroCarta()){
            return titolarePuntoVendita.getCarta();
        }
        return null;
    }



}