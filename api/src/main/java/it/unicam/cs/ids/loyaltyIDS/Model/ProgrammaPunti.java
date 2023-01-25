package it.unicam.cs.ids.loyaltyIDS.Model;



public class ProgrammaPunti extends ProgrammaFedelta {

    private int valoreXPunto;    //Valore in euro per singolo punto
    private int totPunti;       //Punti da totalizzare per ottenere coupon
    private Coupon coupon;      //vantaggio ottenibile al raggiungimento di una certa soglia di punti

    public ProgrammaPunti(String nome, String descrizione,  int valoreXPunto, int totPunti, Coupon coupon) {
        super(nome, descrizione);
        this.valoreXPunto= valoreXPunto;
        this.totPunti= totPunti;
        this.coupon= coupon;
    }

    public ProgrammaPunti(String nome, String descrizione) {
        super(nome, descrizione);
        this.valoreXPunto=0;
        this.totPunti=0;
    }

    public ProgrammaPunti(String nome, int id) {
        super(nome, id);
        this.valoreXPunto=0;
        this.totPunti=0;
        this.coupon=null;
    }

    public ProgrammaPunti(String nome) {
        super(nome);
    }

    public int getValoreXPunto() {
        return valoreXPunto;
    }

    public int getTotPunti() {
        return totPunti;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setValoreXPunto(int valoreXPunto) {
        this.valoreXPunto = valoreXPunto;
    }

    public void setTotPunti(int totPunti) {
        this.totPunti = totPunti;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }
}