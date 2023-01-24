package it.unicam.cs.ids.loyaltyIDS.Model;

import it.unicam.cs.ids.loyaltyIDS.Controller.ControllerPagamento;

public class SistemaBanca {

    private StatoPagamento pagamento;

    private ControllerPagamento statoPagamento;

    public SistemaBanca() {
        statoPagamento= new ControllerPagamento();
    }

    public StatoPagamento getPagamento() {
        return pagamento;
    }

    public StatoPagamento verificaPagamento(TitolarePuntoVendita t){
        if (statoPagamento.payment(t)){
            pagamento=StatoPagamento.PAGATO;
        }else pagamento=StatoPagamento.In_Attesa;

        return pagamento;
    }
}
