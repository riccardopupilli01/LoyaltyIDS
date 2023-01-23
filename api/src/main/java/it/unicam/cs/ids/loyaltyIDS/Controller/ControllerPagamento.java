package it.unicam.cs.ids.loyaltyIDS.Controller;

import it.unicam.cs.ids.loyaltyIDS.DBMSController;
import it.unicam.cs.ids.loyaltyIDS.Model.GestorePiattaforma;
import it.unicam.cs.ids.loyaltyIDS.Model.StatoPagamento;
import it.unicam.cs.ids.loyaltyIDS.Model.TitolarePuntoVendita;

public class ControllerPagamento {

    private TitolarePuntoVendita titolarePuntoVendita;

    private DBMSController db_controller;


    /**
     * se il pagamento è stato effettuato correttamente
     *
     * @return true , altrimenti false;
     */

    public boolean payment() {
        if (titolarePuntoVendita.getCarta().getSaldoCarta()>GestorePiattaforma.getCostoAdesionePiattaforma()){
            titolarePuntoVendita.getCarta().decrementaSaldo(GestorePiattaforma.getCostoAdesionePiattaforma());
            return true;
        }
        return false;
    }


}