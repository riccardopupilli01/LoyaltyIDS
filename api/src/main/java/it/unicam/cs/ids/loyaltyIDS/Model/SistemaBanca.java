package it.unicam.cs.ids.loyaltyIDS.Model;

import it.unicam.cs.ids.loyaltyIDS.Controller.ControllerPagamento;

public class SistemaBanca {

    private StatoPagamento pagamento;

    private ControllerPagamento statoPagamento;

    public SistemaBanca() {
        if (statoPagamento.payment()){
            pagamento=StatoPagamento.PAGATO;
        }
        pagamento=StatoPagamento.In_Attesa;
    }

    public StatoPagamento getPagamento() {
        return pagamento;
    }
}
